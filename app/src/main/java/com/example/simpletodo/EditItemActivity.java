package com.example.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.simpletodo.MainActivity.ITEM_POSITION;
import static com.example.simpletodo.MainActivity.ITEM_TEXT;

public class EditItemActivity extends AppCompatActivity {

    // track edit text
    EditText etItemText;

    // track position
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        // find edit text in layout
        etItemText = findViewById(R.id.etItemText);

        // set text from intent
        etItemText.setText(getIntent().getStringExtra(ITEM_TEXT));

        // update position from intent
        position = getIntent().getIntExtra(ITEM_POSITION, 0);

        // update title bar
        getSupportActionBar().setTitle("Edit Item");
    }

    // need handler for save button
    public void onSaveItem(View v) {
        // prepare new intent for result
        Intent i = new Intent();

        // pass updated item text as intent extra
        i.putExtra(ITEM_TEXT, etItemText.getText().toString());

        // pass original position
        i.putExtra(ITEM_POSITION, position);

        // set intent as result of activity
        setResult(RESULT_OK, i);

        // close activity, redirect back to main
        finish();
    }
}
