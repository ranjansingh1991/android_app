package Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import BeanClasses.Bean_ClassFor_Listname;
import design.ws.com.pepperific.Activity_Name_List;
import design.ws.com.pepperific.Booking_Table_Activity;
import design.ws.com.pepperific.Category_Type_Activity;
import design.ws.com.pepperific.Filter_Activity;
import design.ws.com.pepperific.MainActivity;
import design.ws.com.pepperific.R;


/**
 * Created by Rp on 6/14/2016.
 */
public class RecycleAdapter_Time extends RecyclerView.Adapter<RecycleAdapter_Time.MyViewHolder> {
Context context;

    int pos = -1;

    private List<Bean_ClassFor_time> moviesList;


    ImageView NormalImageView;
    Bitmap ImageBit;
    float ImageRadius = 40.0f;




    public class MyViewHolder extends RecyclerView.ViewHolder {





        TextView time;
        LinearLayout linearLayout;



        public MyViewHolder(View view) {
            super(view);


            time = (TextView) view.findViewById(R.id.time);
            linearLayout = (LinearLayout) view.findViewById(R.id.linear);

        }

    }



    public RecycleAdapter_Time(Booking_Table_Activity mainActivityContacts, List<Bean_ClassFor_time> moviesList) {
        this.moviesList = moviesList;
       this.context = mainActivityContacts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_booking_time_list, parent, false);



        return new MyViewHolder(itemView);


    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Bean_ClassFor_time movie = moviesList.get(position);
        holder.time.setText(movie.getTime());

        if (pos == position){

            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.time.setTextColor(Color.parseColor("#373737"));


        }
        else  {
            holder.linearLayout.setVisibility(View.GONE);
            holder.time.setTextColor(Color.parseColor("#a5a5a5"));


        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pos = position;
                notifyDataSetChanged();

            }
        });

    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }






}


