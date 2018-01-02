package com.loryleen.notebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class NoteEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        // TODO #10: display text for the note user has selected in editText field
        EditText editText = (EditText) findViewById(R.id.editText);

        // TODO #11: get note id that was passed to this activity from MainActivity
        Intent intent = getIntent();
        int noteId = intent.getIntExtra("noteId", -1); // listView ID starts at 0, so -1 is impossible to get

        // TODO #12: if noteId isn't -1, then we can SET the text content of the note from MainActivity
        if (noteId != -1){
            editText.setText(MainActivity.notes.get(noteId));
        }
    }
}
