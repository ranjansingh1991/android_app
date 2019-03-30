package in.semicolonindia.gopremimumtask_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RANJAN SINGH on 9/25/2018.
 */

@SuppressWarnings("ALL")
public class RvVerticalAdapter extends RecyclerView.Adapter<RvVerticalAdapter.ViewHolder> {
    List<FirstModel> itemList;
    Context context;

    public RvVerticalAdapter(Context context, List<FirstModel> itemList) {
        this.context = context;
        this.itemList = itemList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView;
        ViewHolder mViewHolder = null;
        switch (viewType) {
            case 0:
                rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ebook_one, parent, false);
                mViewHolder = new ViewHolder(rootView, viewType);
                break;

            case 1:
                rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ebook_two, parent, false);
                mViewHolder = new ViewHolder(rootView, viewType);
                break;

            case 2:
                rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ebook_one, parent, false);
                mViewHolder = new ViewHolder(rootView, viewType);
                break;

            case 3:
                rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ebook_two, parent, false);
                mViewHolder = new ViewHolder(rootView, viewType);
                break;
        }
        return mViewHolder;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position) {
            case 0:
                holder.tv_oneTitlet.setText(itemList.get(position).getEbookName());
                holder.iv_oneImage.setImageResource(itemList.get(position).getEbookImage());
                break;

            case 1:
                holder.tv_twoTitlet.setText(itemList.get(position).getEbookName());
                holder.iv_twoImage.setImageResource(itemList.get(position).getEbookImage());
                break;
            case 2:
                holder.tv_oneTitlet.setText(itemList.get(position).getEbookName());
                holder.iv_oneImage.setImageResource(itemList.get(position).getEbookImage());
                break;

            case 3:
                holder.tv_twoTitlet.setText(itemList.get(position).getEbookName());
                holder.iv_twoImage.setImageResource(itemList.get(position).getEbookImage());
                break;
        }
    }

    @Override
    public int getItemCount() {
        //return itemList.size();
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_oneTitlet, tv_twoTitlet;
        ImageView iv_oneImage, iv_twoImage;

        public ViewHolder(View itemView, int nPosition) {
            super(itemView);
            switch (nPosition) {
                case 0:
                    tv_oneTitlet = itemView.findViewById(R.id.tv_oneTitlet);
                    iv_oneImage = itemView.findViewById(R.id.iv_oneImage);
                    break;
                case 1:
                    tv_twoTitlet = itemView.findViewById(R.id.tv_twoTitlet);
                    iv_twoImage = itemView.findViewById(R.id.iv_twoImage);
                    break;
                case 2:
                    tv_oneTitlet = itemView.findViewById(R.id.tv_oneTitlet);
                    iv_oneImage = itemView.findViewById(R.id.iv_oneImage);
                    break;
                case 3:
                    tv_twoTitlet = itemView.findViewById(R.id.tv_twoTitlet);
                    iv_twoImage = itemView.findViewById(R.id.iv_twoImage);
                    break;
            }
        }
    }
}

