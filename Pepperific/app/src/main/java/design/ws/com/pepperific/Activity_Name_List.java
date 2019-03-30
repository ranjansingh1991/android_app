package design.ws.com.pepperific;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import Adapters.RecycleAdapter_NameList;
import BeanClasses.Bean_ClassFor_Listname;

public class Activity_Name_List extends AppCompatActivity {

    private String Name[]= {"Main Activity","Filter Activity","Category Type Activity"};


    private ArrayList<Bean_ClassFor_Listname> bean_classFor_listnames;

    private RecyclerView recyclerView;
    private RecycleAdapter_NameList mAdapter;

    TextView follow_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__name__list);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        bean_classFor_listnames = new ArrayList<>();



        for (int i = 0; i < Name.length; i++) {
            Bean_ClassFor_Listname beanClassForRecyclerView_contacts = new Bean_ClassFor_Listname(Name[i]);

            bean_classFor_listnames.add(beanClassForRecyclerView_contacts);
        }


        mAdapter = new RecycleAdapter_NameList(Activity_Name_List.this,bean_classFor_listnames);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Activity_Name_List.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}
