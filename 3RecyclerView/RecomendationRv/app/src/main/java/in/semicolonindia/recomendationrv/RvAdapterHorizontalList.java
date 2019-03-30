package in.semicolonindia.recomendationrv;

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

@SuppressWarnings("ALL")

public class RvAdapterHorizontalList extends RecyclerView.Adapter<RvAdapterHorizontalList.MyViewHolder> {
    Context context;

    boolean showingFirst = true;

    private List<ModelHorizontalData> moviesList;


    ImageView NormalImageView;
    Bitmap ImageBit;
    float ImageRadius = 40.0f;


    public RvAdapterHorizontalList(MainActivity mainActivityContacts, List<ModelHorizontalData> moviesList) {
        this.moviesList = moviesList;
        this.context = mainActivityContacts;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_horizontal_list, null);

        return new MyViewHolder(itemView);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){

        ModelHorizontalData movie = moviesList.get(position);
        holder.rv_image.setImageResource(movie.getImage());
        holder.tv_priceOne.setText(movie.getPriceOne());
        holder.tv_priceTwo.setText(movie.getPriceTwo());



        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                // You can pass your own memory cache implementation
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .build();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(10)) //rounded corner bitmap
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
        imageLoader.displayImage("drawable://"+ movie.getImage(),holder.rv_image, options );

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView rv_image;
        TextView tv_priceOne;
        TextView tv_priceTwo;

        public MyViewHolder(View view) {
            super(view);

            rv_image = (ImageView) view.findViewById(R.id.rv_image);
            tv_priceOne = (TextView) view.findViewById(R.id.tv_priceOne);
            tv_priceTwo = (TextView) view.findViewById(R.id.tv_priceTwo);
        }

    }
}


