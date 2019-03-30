package Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import Fragments.Fragment_Banner;

/**
 * Created by wolfsoft on 10/11/2015.
 */
public class PagerAdapterForBanner extends FragmentStatePagerAdapter {



    public PagerAdapterForBanner(FragmentManager fm) {
        super(fm);

    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Fragment_Banner tab1 = new Fragment_Banner();
                return tab1;

            case 1:
                Fragment_Banner tab6 = new Fragment_Banner();
                return tab6;


            case 2:
                Fragment_Banner tab2 = new Fragment_Banner();
                return tab2;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}