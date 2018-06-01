package com.example7.viewcontribution;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ViewEventContribution extends AppCompatActivity {

    private ListView mListView;
    private String[] maintitle1 = {"Title 1", "Title 2", "Title 3", "Title 4", "Title 5"};
    private String[] subtitle1 = {"Sub Title 1", "Sub Title 2", "Sub Title 3", "Sub Title 4", "Sub Title 5"};
    private String[] maintitle = {};
    private Integer[] subtitle = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contribution);

        // fetch the data from DB into maintitle and subtitle
        get_participant_event_data();


    }

    // Communicate with DB controller to get particpant event list along with their contribution
    void get_participant_event_data() {

        // creating a static list of event names and their contribution
        List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
        Map<String, Integer> row_map = new HashMap<String, Integer>();
        row_map.put("Event_name1",1200);
        list.add(row_map);
        row_map.put("Event_name2",1400);
        list.add(row_map);
        row_map.put("Event_name3",1600);
        list.add(row_map);
        row_map.put("Event_name4",1800);
        list.add(row_map);

        String PartID = "Hello_123";

        // create JSON object POST request
        StringRequest participantEventListRequest = new StringRequest(DownloadManager.Request.Method.POST, AppConfig.URL_LIST_PARTICIPANT_EVENTS_AND_CONTRBS, new Response.Listener<String>() {
            @Override
            public void onResponse(JSONObject response) {
                json_query_result = response;
                display_participant_event_along_with_their_contr(json_query_result);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();
                params.put("loginToken", sessionManager.getLoginToken());
                params.put("ParticipantID", PartID);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

    }
    public void display_participant_event_along_with_their_contr(JSONObject json_query_result)
    {
        try {
            JSONObject json_response = new JSONObject(json_query_result);
            JSONArray json_main_node = json_response.getJSONArray("EventListAndContribution");
            // extract event and initialize Event array list

            for (int j = 0; j < json_main_node.length(); j++) {
                JSONObject json_child_node = json_main_node.getJSONObject(j);
                maintitle[j]= json_child_node.getString("Event_name");
                subtitle[j]= json_child_node.getInt("Event_contr");
            }
        }
        catch (JSONException e){
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        // bind the obtained values to the adapter
        MyListAdapter adapter = new MyListAdapter(this, maintitle, subtitle);
        mListView = (ListView) findViewById(R.id.list_event);
        mListView.setAdapter((ListAdapter) adapter);

    }


}
