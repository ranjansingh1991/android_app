package in.semicolonindia.kopy_kitabtask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.semicolonindia.kopy_kitabtask.model.EbookModel;
import in.semicolonindia.kopy_kitabtask.R;

/**
 * Created by RANJAN SINGH on 9/18/2018.
 */
@SuppressWarnings("ALL")
public class RvEbookAdapter extends RecyclerView.Adapter<RvEbookAdapter.ViewHolder> {
    List<EbookModel> itemList;
    Context context;

    public RvEbookAdapter(Context context, List<EbookModel> itemList) {
        this.context = context;
        this.itemList = itemList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ebook_row, null);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_cardText.setText(itemList.get(position).getEbookName());
        holder.iv_cardImage.setImageResource(itemList.get(position).getEbookImage());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cardText;
        ImageView iv_cardImage;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_cardText = itemView.findViewById(R.id.tv_cardText);
            iv_cardImage = itemView.findViewById(R.id.iv_cardImage);
        }
    }
}




