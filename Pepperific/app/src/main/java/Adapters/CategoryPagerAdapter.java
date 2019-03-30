package Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import Fragments.Category_DiningRoom_Fragment;

/**
 * Created by wolfsoft on 10/11/2015.
 */
public class
CategoryPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public CategoryPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Category_DiningRoom_Fragment tab1 = new Category_DiningRoom_Fragment();
                return tab1;

            case 1:
                Category_DiningRoom_Fragment tab2 = new Category_DiningRoom_Fragment();
                return tab2;


            case 2:
                Category_DiningRoom_Fragment tab3 = new Category_DiningRoom_Fragment();
                return tab3;

            case 3:
                Category_DiningRoom_Fragment tab4 = new Category_DiningRoom_Fragment();
                return tab4;

            case 4:
                Category_DiningRoom_Fragment tab5 = new Category_DiningRoom_Fragment();
                return tab5;



            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}