package Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
public class RecycleAdapter_NameList extends RecyclerView.Adapter<RecycleAdapter_NameList.MyViewHolder> {
Context context;

    boolean showingFirst = true;

    private List<Bean_ClassFor_Listname> moviesList;


    ImageView NormalImageView;
    Bitmap ImageBit;
    float ImageRadius = 40.0f;




    public class MyViewHolder extends RecyclerView.ViewHolder {





        TextView name;



        public MyViewHolder(View view) {
            super(view);


            name = (TextView) view.findViewById(R.id.text);

        }

    }



    public RecycleAdapter_NameList(Activity_Name_List mainActivityContacts, List<Bean_ClassFor_Listname> moviesList) {
        this.moviesList = moviesList;
       this.context = mainActivityContacts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.name_list, parent, false);



        return new MyViewHolder(itemView);


    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Bean_ClassFor_Listname movie = moviesList.get(position);
        holder.name.setText(movie.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position == 0)
                {
                    Intent i = new Intent(context, MainActivity.class);
                    context.startActivity(i);
                }
                else if(position == 1){

                    Intent i = new Intent(context, Filter_Activity.class);
                    context.startActivity(i);

                }
                else if(position == 2){

                    Intent i = new Intent(context, Category_Type_Activity.class);
                    context.startActivity(i);
                }


            }
        });
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }






}


