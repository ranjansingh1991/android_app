package design.ws.com.pepperific;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Spinner;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.util.ArrayList;

import Adapters.Bean_ClassFor_time;
import Adapters.ItemData;
import Adapters.RecycleAdapter_NameList;
import Adapters.RecycleAdapter_Time;
import Adapters.SpinnerDataAdapter;
import BeanClasses.Bean_ClassFor_Listname;

public class Booking_Table_Activity extends AppCompatActivity {

    private String time[]= {"11:30","12:30","01:30","02:30","03:30"};


    private ArrayList<Bean_ClassFor_time> bean_classFor_times;

    private RecyclerView recyclerView;
    private RecycleAdapter_Time mAdapter;

    private CompactCalendarView compactcalendar_view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking__table);



        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        compactcalendar_view= (CompactCalendarView)findViewById(R.id.compactcalendar_view);




        ArrayList<ItemData> list = new ArrayList<>();


        list.add(new ItemData("Select Person", R.drawable.ic_copuple));
        list.add(new ItemData("2 Person", R.drawable.ic_copuple));
        list.add(new ItemData("3 Person", R.drawable.ic_copuple));
        list.add(new ItemData("5 Person", R.drawable.ic_copuple));
        Spinner sp = (Spinner) findViewById(R.id.spinner);
        SpinnerDataAdapter adapter = new SpinnerDataAdapter(this, R.layout.spinner_list, R.id.data, list);
        sp.setAdapter(adapter);
        spinner.setAdapter(adapter);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        bean_classFor_times = new ArrayList<>();



        for (int i = 0; i < time.length; i++) {
            Bean_ClassFor_time beanClassForRecyclerView_contacts = new Bean_ClassFor_time(time[i]);

            bean_classFor_times.add(beanClassForRecyclerView_contacts);
        }


        mAdapter = new RecycleAdapter_Time(Booking_Table_Activity.this,bean_classFor_times);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Booking_Table_Activity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}
