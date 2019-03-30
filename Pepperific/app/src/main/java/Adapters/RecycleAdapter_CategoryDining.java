package Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

import BeanClasses.BeanClassForCategoryType;
import BeanClasses.BeanClassForDish;
import design.ws.com.pepperific.MainActivity;
import design.ws.com.pepperific.R;


/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapter_CategoryDining extends RecyclerView.Adapter<RecycleAdapter_CategoryDining.MyViewHolder> {

    boolean showingFirst = true;

    private List<BeanClassForCategoryType> moviesList;
    Context mContext;

    ImageView NormalImageView;
    Bitmap ImageBit;
    float ImageRadius = 40.0f;




    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView title;


        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.tv_title);

        }

    }



    public RecycleAdapter_CategoryDining(Context mContext, List<BeanClassForCategoryType> moviesList) {
        this.moviesList = moviesList;
       this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_type_list, parent, false);

        return new MyViewHolder(itemView);

    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {

        BeanClassForCategoryType movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }






}


