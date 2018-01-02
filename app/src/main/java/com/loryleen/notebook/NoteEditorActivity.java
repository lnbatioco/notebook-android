package com.loryleen.notebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        // TODO #10: display text for the note user has selected in editText field
        EditText editText = (EditText) findViewById(R.id.editText);

        // TODO #11: get note id that was passed to this activity from MainActivity
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1); // listView ID starts at 0, so -1 is impossible to get

        // TODO #12: if noteId isn't -1, then we can SET the text content of the note from MainActivity
        if (noteId != -1){
            editText.setText(MainActivity.notes.get(noteId));
        } else {
            // TODO #20:
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size() -1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }




        // TODO #13: SAVE / UPDATE content in editText when editText is changed
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // before text is changed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // ON text is changed
                // TODO #14: UPDATE notes arrayList from the MainActivity
                    // noteId is the item or integer from the arrayList we want to UPDATE
                    // charSequence is the content of the editText
                    // we want to UPDATE the content of noteId to charSequence
                // TODO #15: charSequence is of the CharSequence type, so we need to convert its characters to a string.
                MainActivity.notes.set(noteId, String.valueOf(charSequence)); // charSequence is the content of the editText

                // TODO #16: update the listView itself via the arrayAdapter
                MainActivity.arrayAdapter.notifyDataSetChanged();

                // TODO #23: PERMANENT STORAGE
                    // private so only app can access this data
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.loryleen.notebook", Context.MODE_PRIVATE);

                // TODO #24: shared preferences needs a set to work with
                    // create a HashSet of strings from arrayList in MainActivity
                HashSet<String> set = new HashSet(MainActivity.notes);

                // TODO #25: save the set in shared preferences
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // after text is changed
            }
        });



    }
}
