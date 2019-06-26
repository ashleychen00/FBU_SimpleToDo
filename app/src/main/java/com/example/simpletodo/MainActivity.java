package com.example.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // numeric code to identify edit activity
    public static final int EDIT_REQUEST_CODE = 20;

    // keys used for passing data between activities
    public static final String ITEM_TEXT = "itemText";
    public static final String ITEM_POSITION = "itemPosition";

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView listView_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readItems();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView_items = findViewById(R.id.listview_items);

        listView_items.setAdapter(itemsAdapter);

        // mock data
//        items.add("First");
//        items.add("second");
//        items.add("thirrrd");

        // set up long click listener
        setupListViewListener();

    }

    private void setupListViewListener() {
        // set listView's itemLongClickListener
        listView_items.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // remove item in list at given position
                items.remove(position);
                // notify adapter of change
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                Toast.makeText(getApplicationContext(), "Good job!", Toast.LENGTH_SHORT).show();
                // return true to indicate long click occurred
                Log.i("Main activity", "Removed item" + position);
                return true;
            }
        });

        // stretch: to edit
        listView_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // create new activity
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);

                // pass data being edited
                i.putExtra(ITEM_TEXT, items.get(position));
                i.putExtra(ITEM_POSITION, position);

                // display activity
                startActivityForResult(i, EDIT_REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if edit activity completed ok
        if (resultCode == RESULT_OK && requestCode == EDIT_REQUEST_CODE) {
            // extract updated item
            String updated = data.getExtras().getString(ITEM_TEXT);
            // extract original position
            int position = data.getExtras().getInt(ITEM_POSITION);

            // update model
            items.set(position, updated);
            // tell adapter
            itemsAdapter.notifyDataSetChanged();
            // persists
            writeItems();
            // notify user w/ toast
            Toast.makeText(this, "Item edited successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // FOR BUTTON

    public void onAddItem(View v) {
        // get text from editText view: first find editText, then get text
        EditText inputted_text =  (EditText) findViewById(R.id.editText_newItem);
        String input = inputted_text.getText().toString();
        // add item to list via the adapter
        if (input.length() != 0) {
            itemsAdapter.add(input);
            // clear editText
            inputted_text.setText("");
            // notify user action completed with Toast
            Toast.makeText(getApplicationContext(), "Item added to list", Toast.LENGTH_SHORT).show();
            writeItems();
        }
        else {
            Toast.makeText(getApplicationContext(), "Item can't be empty", Toast.LENGTH_SHORT).show();
        }
    }

    // FOR PERSISTENCE

    // returns file in which data is stored
    private File getDataFile() {
        return new File(getFilesDir(), "todo.txt");
    }

    // read items from file system
    private void readItems() {
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e("Main Activity", "Error reading file", e);
            items = new ArrayList<>();
        }
    }

    // write items to filesystem
    private void writeItems() {
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e) {
            Log.e("Main Activity", "Error writing file", e);
        }
    }
}
