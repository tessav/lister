package com.example.tessav.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Task> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;
    ArrayList<String> textItems;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<Task>(getTasks());
        textItems = new ArrayList<String>();
        for (Task it : items) {
            textItems.add(it.task_name);
        }
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, textItems);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    private void setupListViewListener() {
        // edit task or show more details
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View item,
                                                   int pos, long id) {
                        Intent openEdit = new Intent(MainActivity.this, EditItemActivity.class);
                        openEdit.putExtra("pos", pos);
                        openEdit.putExtra("task", textItems.get(pos));
                        startActivityForResult(openEdit, REQUEST_CODE);
                    }
                }
        );
        // delete task
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item,
                                                   int pos, long id) {
                        deleteTask(pos);
                        return true;
                    }
                }
        );
    }

    // add task to list
    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        addTask(itemText);
        etNewItem.setText("");
    }

    // edit task and save
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 20) {
            String task = data.getExtras().getString("task");
            int pos = data.getExtras().getInt("pos", 0);
            editTask(pos, task);
            //Toast.makeText(this, "Updated task: " + task, Toast.LENGTH_SHORT).show();
        }
    }

    private List<Task> getTasks() {
        List<Task> tasks = SQLite.select().from(Task.class).queryList();
        return tasks;
    }

    private void addTask(String item_text) {
        Task newTask = new Task();
        newTask.task_name = item_text;
        newTask.save();
        items.add(newTask);
        textItems.add(newTask.task_name);
        itemsAdapter.notifyDataSetChanged();
    }

    private void deleteTask(int pos) {
        Task itemToDelete = items.get(pos);
        items.remove(pos);
        textItems.remove(pos);
        itemToDelete.delete();
        itemsAdapter.notifyDataSetChanged();
    }

    private void editTask(int pos, String task_name) {
        Task task = items.get(pos);
        task.task_name = task_name;
        task.save();
        textItems.set(pos, task_name);
        itemsAdapter.notifyDataSetChanged();
    }

}
