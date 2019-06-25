package com.example.simpletodo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
                // return true to indicate long click occurred
                Log.i("Main activity", "Removed item" + position);
                return true;
            }
        });
    }

    public void onAddItem(View v) {
        // get text from editText view: first find editText, then get text
        EditText inputted_text =  (EditText) findViewById(R.id.editText_newItem);
        String input = inputted_text.getText().toString();
        // add item to list via the adapter
        itemsAdapter.add(input);
        // clear editText
        inputted_text.setText("");
        // notify user action completed with Toast
        Toast.makeText(getApplicationContext(), "Item added to list", Toast.LENGTH_SHORT).show();
        writeItems();
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
