package in.semicolonindia.pepperificdemo.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.semicolonindia.pepperificdemo.fragment.FragmentBanner_One;
import in.semicolonindia.pepperificdemo.fragment.FragmentBanner_Two;

/**
 * Created by RANJAN SINGH on 9/25/2018.
 */
@SuppressWarnings("ALL")
public class FirstvpPagerAdapter extends FragmentStatePagerAdapter {


    public FirstvpPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentBanner_One tab1 = new FragmentBanner_One();
                return tab1;

            case 1:
                FragmentBanner_Two tab2 = new FragmentBanner_Two();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}