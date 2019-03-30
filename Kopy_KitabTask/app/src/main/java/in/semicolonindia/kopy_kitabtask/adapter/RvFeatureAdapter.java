package in.semicolonindia.kopy_kitabtask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.semicolonindia.kopy_kitabtask.R;
import in.semicolonindia.kopy_kitabtask.model.EbookModel;
import in.semicolonindia.kopy_kitabtask.model.FeatureModel;

/**
 * Created by RANJAN SINGH on 9/18/2018.
 */
@SuppressWarnings("ALL")
public class RvFeatureAdapter extends RecyclerView.Adapter<RvFeatureAdapter.ViewHolder> {
    List<FeatureModel> itemList;
    Context context;

    public RvFeatureAdapter(Context context, List<FeatureModel> itemList) {
        this.context = context;
        this.itemList = itemList;

    }

    @Override
    public RvFeatureAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feature_row, null);
        return new RvFeatureAdapter.ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RvFeatureAdapter.ViewHolder holder, int position) {
        holder.tvFeature.setText(itemList.get(position).getFeatureName());
        holder.ivFeatureImage.setImageResource(itemList.get(position).getFetureImage());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFeature;
        ImageView ivFeatureImage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvFeature = itemView.findViewById(R.id.tvFeature);
            ivFeatureImage = itemView.findViewById(R.id.ivFeatureImage);
        }
    }
}




