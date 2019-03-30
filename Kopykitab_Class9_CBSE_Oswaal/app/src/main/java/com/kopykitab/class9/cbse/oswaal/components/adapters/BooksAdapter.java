package com.kopykitab.class9.cbse.oswaal.components.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.kopykitab.class9.cbse.oswaal.R;
import com.kopykitab.class9.cbse.oswaal.models.BookItem;
import com.kopykitab.class9.cbse.oswaal.settings.Utils;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> implements Filterable {

    private Context mContext;
    private List<BookItem> bookItems = new ArrayList<BookItem>();
    private List<BookItem> originalBooksList = new ArrayList<BookItem>();
    private int rowLayout;
    private String libraryView;
    private BooksAdapterListener bookItemListener;
    private LinearLayout noResultsFoundMessageLayout;
    private LinearLayout findMoreBookLayout;


    public BooksAdapter(Context mContext, int rowLayout, String libraryView, BooksAdapterListener bookItemListener) {
        this.mContext = mContext;
        this.rowLayout = rowLayout;
        this.libraryView = libraryView;
        this.bookItemListener = bookItemListener;

        initializeComponents();
    }

    public BooksAdapter(Context mContext, List<BookItem> bookItems, int rowLayout, String libraryView, BooksAdapterListener bookItemListener) {
        this.mContext = mContext;
        this.bookItems = bookItems;
        this.originalBooksList = bookItems;
        this.rowLayout = rowLayout;
        this.libraryView = libraryView;
        this.bookItemListener = bookItemListener;
        initializeComponents();
    }

    public BooksAdapter(Context mContext, List<BookItem> searchedBookItems, List<BookItem> bookItems, int rowLayout, String libraryView, BooksAdapterListener bookItemListener) {
        this.mContext = mContext;
        this.bookItems = searchedBookItems;
        this.originalBooksList = bookItems;
        this.rowLayout = rowLayout;
        this.libraryView = libraryView;
        this.bookItemListener = bookItemListener;

        initializeComponents();
    }

    public void initializeComponents() {
        try {
            noResultsFoundMessageLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.library_flipper).findViewById(R.id.search_results_no_books_found);
            findMoreBookLayout = (LinearLayout) ((Activity) mContext).findViewById(R.id.search_find_more_books_layout);

            if (libraryView.equals("ListView")) {
                ((TextView) noResultsFoundMessageLayout.findViewById(R.id.no_result_found_text)).setTextColor(Color.parseColor("#959393"));
                ((TextView) noResultsFoundMessageLayout.findViewById(R.id.no_result_found_sub_text)).setTextColor(Color.parseColor("#959393"));
            } else if (libraryView.equals("GridView")) {
                ((TextView) noResultsFoundMessageLayout.findViewById(R.id.no_result_found_text)).setTextColor(Color.WHITE);
                ((TextView) noResultsFoundMessageLayout.findViewById(R.id.no_result_found_sub_text)).setTextColor(Color.WHITE);
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BooksViewHolder holder, final int position) {

        BookItem bookItem = bookItems.get(position);
        Utils.getImageLoader(mContext).displayImage(bookItem.getImageURL().replaceAll(" ", "%20"), holder.bookImage);
        holder.name.setText(bookItem.getName());
        holder.details.setText(bookItem.getDescription());

        if (libraryView.equals("ListView")) {
            holder.descriptions.setVisibility(View.VISIBLE);
        }

        if (!bookItem.getProductType().equals("ebook")) {
            holder.bookDepiction.setVisibility(View.INVISIBLE);
        } else {
            holder.bookDepiction.setVisibility(View.VISIBLE);
            File pdfFile = new File(Utils.getFileDownloadPath(mContext, bookItem.getProductLink()));
            if (pdfFile.exists()) {
                holder.bookDepiction.setImageResource(R.drawable.success_icon);
            } else {
                holder.bookDepiction.setImageResource(R.drawable.cloud_icon);
            }
        }
    }

    public void setItem(BookItem bookItem) {
        bookItems.add(bookItem);
        notifyDataSetChanged();
    }

    public void removeItem(BookItem item) {
        String product_id = item.getProductId();
        List<BookItem> tempBooksList = new ArrayList<BookItem>(bookItems);
        for (BookItem product : tempBooksList) {
            if (product.getProductId().equals(product_id)) {
                bookItems.remove(product);
            }
        }
        notifyDataSetChanged();
    }

    public List<BookItem> getBookItems() {
        return bookItems;
    }

    @Override
    public int getItemCount() {
        return bookItems.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence bookSearchText) {
                FilterResults booksFilteredResult = new FilterResults();
                String bookSearchTextString = bookSearchText.toString();
                if (bookSearchTextString.isEmpty() || bookSearchText.length() <= 0) {
                    booksFilteredResult.values = originalBooksList;
                    booksFilteredResult.count = originalBooksList.size();
                } else {
                    List<BookItem> filteredList = new ArrayList<>();
                    for (BookItem row : originalBooksList) {
                        if (row.getName().toLowerCase().contains(bookSearchTextString.toLowerCase()) || row.getDescription().contains(bookSearchText)) {
                            filteredList.add(row);
                        }
                    }
                    booksFilteredResult.values = filteredList;
                    booksFilteredResult.count = filteredList.size();
                }

                return booksFilteredResult;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults booksFilteredResult) {
                if (booksFilteredResult.count <= 0 && originalBooksList.size() > 0) {
                    noResultsFoundMessageLayout.setVisibility(View.VISIBLE);
                    findMoreBookLayout.setVisibility(View.GONE);
                    Utils.setSearchTextForSearchInStore(charSequence.toString());
                } else if (booksFilteredResult.count > 0 && booksFilteredResult.count <= 2 && originalBooksList.size() > 0) {
                    findMoreBookLayout.setVisibility(View.VISIBLE);
                    noResultsFoundMessageLayout.setVisibility(View.GONE);
                    Utils.setSearchTextForSearchInStore(charSequence.toString());
                } else {
                    findMoreBookLayout.setVisibility(View.GONE);
                    noResultsFoundMessageLayout.setVisibility(View.GONE);
                    Utils.setSearchTextForSearchInStore("");
                }
                bookItems = (ArrayList<BookItem>) booksFilteredResult.values;
                notifyDataSetChanged();
            }
        };
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout descriptions;
        TextView details;
        ImageView bookImage, bookDepiction;

        public BooksViewHolder(final View v) {
            super(v);
            bookImage = (ImageView) v.findViewById(R.id.book_image);
            bookDepiction = (ImageView) v.findViewById(R.id.book_depiction);
            descriptions = (LinearLayout) v.findViewById(R.id.description);
            name = (TextView) v.findViewById(R.id.name);
            details = (TextView) v.findViewById(R.id.details);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bookItemListener.onBookClick(bookItems.get(getAdapterPosition()), v);

                }
            });

            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    bookItemListener.onBookLongClick(bookItems.get(getAdapterPosition()), v);
                    return true;
                }
            });
        }
    }

    public interface BooksAdapterListener {
        void onBookClick(BookItem item, View bookView);

        void onBookLongClick(BookItem item, View bookView);
    }
}