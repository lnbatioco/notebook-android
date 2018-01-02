package com.loryleen.notebook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    // TODO #2: link ArrayList to listView. array list needs to be accessed from all parts of the app.
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;




    // TODO #18: use MenuInflator to link our menu w/ menu we just created
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }




    // TODO #19: add onOptionsItemSelected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.add_note) {
            Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);

            startActivity(intent);

            return true;
        }
        return false;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO #1: add ListView
        ListView listView = (ListView) findViewById(R.id.listView);

        // TODO #27:
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.loryleen.notebook", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);

        if (set == null){
            notes.add("Example note");
        } else {
            notes = new ArrayList(set);
        }

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


        // TODO #17: ADDING a note
            // we want to add a menu item at the top right
                // add new directory inside res folder. res > new > directory > name: "menu"
                // menu > new > Menu resource file > name: "add_note_menu"
                    // add item to add_note_menu.xml
                // now we need to add it to this MainActivity




        // TODO #21: DELETE note with a long press
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int itemToDelete = i;

                // TODO #22: set up an alert so user may confirm if they want to delete a note
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                notes.remove(itemToDelete);
                                arrayAdapter.notifyDataSetChanged();

                                // TODO #26: copy and paste sharedPreferences from NoteEditorActivity for when a note is DELETED
                                // private so only app can access this data
                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.loryleen.notebook", Context.MODE_PRIVATE);

                                // create a HashSet of strings from arrayList in MainActivity
                                HashSet<String> set = new HashSet(MainActivity.notes);

                                // save the set in shared preferences
                                sharedPreferences.edit().putStringSet("notes", set).apply();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }
}
