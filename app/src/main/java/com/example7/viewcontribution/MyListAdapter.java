package com.example7.viewcontribution;

import android.app.Activity;
import android.app.DownloadManager;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


public class MyListAdapter extends ArrayAdapter<String>
{
    private Activity Context;
    private String[] maintitle;
    private Integer[] subtitle;


    public MyListAdapter(Activity context, String[] maintitle, Integer[] subtitle) {
        super(context,R.layout.list_row_item, maintitle);
        this.Context = context;
        this.maintitle = maintitle;
        this.subtitle = subtitle;
    }
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = Context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_row_item, null, true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        titleText.setText(maintitle[position]);
        subtitleText.setText(subtitle[position]);

        return rowView;

    }

}
