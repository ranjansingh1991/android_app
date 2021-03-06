package design.ws.com.pepperific;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import Adapters.CategoryPagerAdapter;

import static java.security.AccessController.getContext;

public class Category_Type_Activity  extends AppCompatActivity {


    private Toolbar toolbar;

    //   private DragTopLayout dragLayout;



    private ViewPager viewPager;
    Typeface mTypeface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category__type);


        setToolbar();

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.addTab(tabLayout.newTab().setText("Dining Room"));
        tabLayout.addTab(tabLayout.newTab().setText("Dessert"));
        tabLayout.addTab(tabLayout.newTab().setText("Wine"));
        tabLayout.addTab(tabLayout.newTab().setText("Food"));
        tabLayout.addTab(tabLayout.newTab().setText("Drink"));


//        tabLayout.addTab(tabLayout.newTab().setText("DESERT"));
//
//        tabLayout.addTab(tabLayout.newTab().setText("SOUP"));


        mTypeface = Typeface.createFromAsset(this.getAssets(), "fonts/Lato-Bold.ttf");
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
                }
            }
        }





        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        CategoryPagerAdapter adapter = new CategoryPagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);


        viewPager.setOffscreenPageLimit(4);



        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });







    }

    public void setCurrentTab(int i) {
        viewPager.setCurrentItem(i);
    }


    private void setToolbar(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(false);

        actionBar.setTitle("");

    }


}
