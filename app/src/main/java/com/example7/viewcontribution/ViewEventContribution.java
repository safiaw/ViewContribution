package com.example7.viewcontribution;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewEventContribution extends AppCompatActivity{

    private ListView mListView;
    private
    String[] maintitle={"Title 1","Title 2","Title 3","Title 4","Title 5"};
    String[] subtitle={"Sub Title 1","Sub Title 2","Sub Title 3","Sub Title 4","Sub Title 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyListAdapter adapter = new MyListAdapter(this, maintitle, subtitle);
        mListView = (ListView) findViewById(R.id.list_event);
        mListView.setAdapter((ListAdapter) adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Code specific to first list item
                if(position==0)
                {
                    Toast.makeText(getApplicationContext(),"First List Item",Toast.LENGTH_SHORT).show();
                }
            }
        });


        //list_events_and_show();
    }

    //Generate DB request to get event list and then render it
  /**  private void list_events_and_show() {
       // Get list of events in which the volunteer has participated from DB corresponding to Volunteers ID



        // Populate the list with the events
        // On click of each item in the list should show contribution details



        mListView = (ListView) findViewById(R.id.listevents);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
        display_event_contribution();
    }

    private void display_event_contribution() {
    }**/

}
