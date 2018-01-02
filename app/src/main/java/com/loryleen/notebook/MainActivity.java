package com.loryleen.notebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // TODO #2: link ArrayList to listView. array list needs to be accessed from all parts of the app.
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO #1: add ListView
        ListView listView = (ListView) findViewById(R.id.listView);

        // TODO #3: add initial note
        notes.add("Example note");

        // TODO #4: set up ArrayAdapter
        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, notes );

        // TODO #5: set ArrayAdapter to listView
        listView.setAdapter(arrayAdapter);




        // TODO #6: create new activity so CLICKING a note will jump to a second activity: the NoteEditorActivity
            // file > new > activity > gallery > empty activity > NoteEditorActivity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // this will be called when an item is clicked:
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO #7: create intent which is a "glue" between MainActivity to the location of NoteEditorActivity
                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);

                // TODO #8: let new activity know which item was clicked
                intent.putExtra("noteId", position); // position is the row number that was tapped

                // TODO #9: start the activity which is the intent
                startActivity(intent);
            }
        });
    }
}
