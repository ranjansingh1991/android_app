package in.semicolonindia.gopreui_ex_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import in.semicolonindia.gopreui_ex_2.R;

@SuppressWarnings("ALL")
public class SpinnerCustomAdapter extends BaseAdapter {

    Context context;
    String[] sNames;
    LayoutInflater inflter;

    public SpinnerCustomAdapter(Context context, String[] sNames) {
        this.context = context;
        this.sNames = sNames;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return sNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflter.inflate(R.layout.custom_spinner_items, null);
        TextView names = (TextView) convertView.findViewById(R.id.textView);
        names.setText(sNames[position]);
        return convertView;
    }
}

