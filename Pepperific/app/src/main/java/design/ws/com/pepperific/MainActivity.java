package design.ws.com.pepperific;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

import Adapters.PagerAdapterForBanner;
import Adapters.RecycleAdapter_Cusine;
import Adapters.RecycleAdapter_Dish;
import BeanClasses.BeanClassForCusine;
import BeanClasses.BeanClassForDish;

public class MainActivity extends AppCompatActivity {



    private PagerAdapterForBanner pagerAdapterForBanner;

    private ViewPager viewPager;



    private ArrayList<BeanClassForDish> beanClassForDashboards;

    private RecyclerView recyclerView_dish;
    private RecycleAdapter_Dish mAdapter_dish;

    Integer image[] = {R.drawable.white_img,R.drawable.white_img,R.drawable.white_img,R.drawable.white_img,R.drawable.white_img};
    String dish_name[] = {"Paratha","Cheese Butter","Paneer Handi","Paneer Kopta","Chiken"};
    String dish_type[] = {"Punjabi","Maxican","Punjabi","Punjabi","Non Veg"};
    String price[] = {"Rs 500 / person (app.)","Rs 800 / person (app.)","Rs 400 / person (app.)","Rs 200 / person (app.)","Rs 500 / person (app.)"};



    private ArrayList<BeanClassForCusine> beanClassForCusines;

    private RecyclerView recyclerView_cusine;
    private RecycleAdapter_Cusine mAdapter_cusine;


    Integer image1[] = {R.drawable.white_img,R.drawable.white_img,R.drawable.white_img,R.drawable.white_img,R.drawable.white_img};
    String price1[] ={"Rs 350","Rs 200","Rs 550","Rs 400","Rs 250"};
    String cusine_name[] = {"Thai Cusine","Maxican","Desert","South Indian","Italian"};
    String city[] = {"Vadodara","Vadodara","Vadodara","Vadodara","Vadodara"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        Dish Recyclerview Code

        recyclerView_dish = (RecyclerView) findViewById(R.id.recyclerview_dish);
        beanClassForDashboards = new ArrayList<>();



        for (int i = 0; i < image.length; i++) {
            BeanClassForDish beanClassForRecyclerView_contacts = new BeanClassForDish(image[i],dish_name[i],dish_type[i],price[i]);


            beanClassForDashboards.add(beanClassForRecyclerView_contacts);
        }


        mAdapter_dish = new RecycleAdapter_Dish(MainActivity.this,beanClassForDashboards);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_dish.setLayoutManager(mLayoutManager);
        recyclerView_dish.setItemAnimator(new DefaultItemAnimator());
        recyclerView_dish.setAdapter(mAdapter_dish);




        //        Cusine Recyclerview Code

        recyclerView_cusine = (RecyclerView) findViewById(R.id.recyclerview_cusine);
        beanClassForCusines = new ArrayList<>();



        for (int i = 0; i < image1.length; i++) {
            BeanClassForCusine beanClassForCusine = new BeanClassForCusine(image1[i],price1[i],cusine_name[i],city[i]);


            beanClassForCusines.add(beanClassForCusine);
        }


        mAdapter_cusine = new RecycleAdapter_Cusine(MainActivity.this,beanClassForCusines);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_cusine.setLayoutManager(mLayoutManager1);
        recyclerView_cusine.setItemAnimator(new DefaultItemAnimator());
        recyclerView_cusine.setAdapter(mAdapter_cusine);





        /*Banner ViewPager Code*/



        viewPager = (ViewPager) findViewById(R.id.viewpager);



        pagerAdapterForBanner = new PagerAdapterForBanner(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapterForBanner);










    }
}
