package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import Adapters.RecycleAdapter_CategoryDining;
import BeanClasses.BeanClassForCategoryType;
import design.ws.com.pepperific.R;

public class Category_DiningRoom_Fragment extends Fragment {



    private String title[] ={"Caviar Tartare","Caviar Tartare","Caviar Tartare","Caviar Tartare","Caviar Tartare"};

    private View view;


    private ArrayList<BeanClassForCategoryType> beanClassForCategoryTypes;

    private RecyclerView recyclerView;
    private RecycleAdapter_CategoryDining mAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dining, container, false);



        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview);

        beanClassForCategoryTypes = new ArrayList<>();


        for (int i = 0; i < title.length; i++) {
            BeanClassForCategoryType beanClassForListView = new BeanClassForCategoryType(title[i]);

            beanClassForCategoryTypes.add(beanClassForListView);
        }


        mAdapter = new RecycleAdapter_CategoryDining(getActivity(),beanClassForCategoryTypes);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        return view;


    }


}
