package in.semicolonindia.pepperificdemo.Adapters;

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

import in.semicolonindia.pepperificdemo.BeanClasses.BeanClassForCusine;
import in.semicolonindia.pepperificdemo.MainActivity;
import in.semicolonindia.pepperificdemo.R;

/**
 * Created by RANJAN SINGH on 9/25/2018.
 */
@SuppressWarnings("ALL")
public class RecycleAdapter_Cusine extends RecyclerView.Adapter<RecycleAdapter_Cusine.MyViewHolder> {
    Context context;

    boolean showingFirst = true;

    private List<BeanClassForCusine> moviesList;


    ImageView NormalImageView;
    Bitmap ImageBit;
    float ImageRadius = 40.0f;




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



    public RecycleAdapter_Cusine(MainActivity mainActivityContacts, List<BeanClassForCusine> moviesList) {
        this.moviesList = moviesList;
        this.context = mainActivityContacts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cusine_list, null);



        return new MyViewHolder(itemView);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {

        BeanClassForCusine movie = moviesList.get(position);
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

}


