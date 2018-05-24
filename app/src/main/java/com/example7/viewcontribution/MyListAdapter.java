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
    void access_participant_event_data_and_show()
    {
        // create JSON object POST request
        StringRequest participantEventListRequest = new StringRequest(Request.Method.POST, AppConfig.URL_LIST_PARTICIPANT_EVENTS_AND_CONTRBS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                json_query_result = response;
                display_participant_event_along_with_their_contr();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                error.printStackTrace();
            }
        })
        {
            @Override
            public Map<String, String> getParams(){
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();
                params.put("loginToken", sessionManager.getLoginToken());
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map <String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        // Insert the created POST request to Volley request queue
        AppController.getInstance().addToRequestQueue(participantEventListRequest);
    }

    public void display_participant_event_along_with_their_contr()
    {
        // extract information from json result
        final ArrayList<Map<String, Integer>> eventscontrb = new ArrayList<Map<String, Integer>>();
        try {
            JSONObject json_response = new JSONObject(json_query_result);
            JSONArray json_main_node = json_response.optJSONArray("EventListAndContribution");

            // extract event and Initialize Event array List

            for(int j=0; j< json_main_node.length();j++) {
                Map<String, Integer> eacheventcontrb = new HashMap<String, Integer>();
                JSONObject json_child_node = json_main_node.getJSONObject(j);
                eacheventcontrb.set
            }
        }




    }
}
