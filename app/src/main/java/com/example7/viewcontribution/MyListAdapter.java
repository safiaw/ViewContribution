package com.example7.viewcontribution;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ADMIN on 08-05-2018.
 */

public class MyListAdapter extends ArrayAdapter<String>
{
    private Activity Context;
    private String[] maintitle;
    private String[] subtitle;

    public MyListAdapter(Activity context, String[] maintitle, String[] subtitle) {
        super(context,R.layout.list_view, maintitle);
        this.Context = context;
        this.maintitle = maintitle;
        this.subtitle = subtitle;
    }
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater=Context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_view, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        titleText.setText(maintitle[position]);
        subtitleText.setText(subtitle[position]);

        return rowView;

    }
}
