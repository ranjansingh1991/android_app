package in.semicolonindia.pepperificdemo.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.semicolonindia.pepperificdemo.fragment.FragmentBanner_One;

/**
 * Created by RANJAN SINGH on 9/25/2018.
 */

@SuppressWarnings("ALL")
public class SecondvpPagerAdapter extends FragmentStatePagerAdapter {



    public SecondvpPagerAdapter(FragmentManager fm) {
        super(fm);

    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentBanner_One tab1 = new FragmentBanner_One();
                return tab1;

            case 1:
                FragmentBanner_One tab6 = new FragmentBanner_One();
                return tab6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}