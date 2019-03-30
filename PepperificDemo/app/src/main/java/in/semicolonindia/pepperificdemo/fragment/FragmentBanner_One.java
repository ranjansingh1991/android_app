package in.semicolonindia.pepperificdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.semicolonindia.pepperificdemo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBanner_One extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner_one, container, false);
        return view;

    }
}

