package com.loryleen.notebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // TODO #2: link ArrayList to listView. array list needs to be accessed from all parts of the app.
    ArrayList<String> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // TODO #1: add ListView
        ListView listView = (ListView) findViewById(R.id.listView);

        // TODO #3: add initial note
        notes.add("Example note");

        // TODO #4: set up ArrayAdapter
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, notes );

        // TODO #5: set ArrayAdapter to listView
        listView.setAdapter(arrayAdapter);
    }
}
