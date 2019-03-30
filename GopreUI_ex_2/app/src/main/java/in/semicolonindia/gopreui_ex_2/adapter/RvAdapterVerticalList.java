package in.semicolonindia.gopreui_ex_2.adapter;

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
import in.semicolonindia.gopreui_ex_2.BeanClasses.ModelVerticalData;
import in.semicolonindia.gopreui_ex_2.MainActivity;
import in.semicolonindia.gopreui_ex_2.R;

@SuppressWarnings("ALL")
public class RvAdapterVerticalList extends RecyclerView.Adapter<RvAdapterVerticalList.MyViewHolder> {
    Context context;

    boolean showingFirst = true;

    private List<ModelVerticalData> modelVerticalData;


    ImageView NormalImageView;
    Bitmap ImageBit;
    float ImageRadius = 40.0f;


    public RvAdapterVerticalList(MainActivity mainActivityContacts, List<ModelVerticalData> modelVerticalData) {
        this.modelVerticalData = modelVerticalData;
        this.context = mainActivityContacts;
    }

    @Override
    public int getItemCount() {
        return modelVerticalData.size();
    }

    @Override
    public RvAdapterVerticalList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_vertical_list, null);



        return new RvAdapterVerticalList.MyViewHolder(itemView);


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(RvAdapterVerticalList.MyViewHolder holder, int position)
    {

        ModelVerticalData movie = modelVerticalData.get(position);
        holder.rv_image.setImageResource(movie.getImage());
        holder.tv_Title.setText(movie.getTitle());
        holder.tv_Des.setText(movie.getDes());





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


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView rv_image;
        TextView tv_Title;
        TextView tv_Des;

        public MyViewHolder(View view) {
            super(view);

            rv_image = (ImageView) view.findViewById(R.id.rv_image);
            tv_Title = (TextView) view.findViewById(R.id.tv_Title);
            tv_Des = (TextView) view.findViewById(R.id.tv_Des);
        }

    }
}


