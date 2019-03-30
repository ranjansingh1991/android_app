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
 * Created by RANJAN SINGH on 9/24/2018.
 */
@SuppressWarnings("ALL")
public class RvHorizontalAdapter extends RecyclerView.Adapter<RvHorizontalAdapter.ViewHolder> {
    List<SecondModel> itemList;
    Context context;

    public RvHorizontalAdapter(Context context, List<SecondModel> itemList) {
        this.context = context;
        this.itemList = itemList;

    }

    @Override
    public RvHorizontalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feature_row, null);
        return new RvHorizontalAdapter.ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RvHorizontalAdapter.ViewHolder holder, int position) {
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




