package com.example.tessav.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pos = getIntent().getIntExtra("pos", 0);
        String task = getIntent().getStringExtra("task");
        setContentView(R.layout.activity_edit_item);
        EditText editItem = (EditText) findViewById(R.id.editItem);
        editItem.setText(task);
        editItem.setSelection(editItem.getText().length());
    }

    public void onEditItem(View v) {
        EditText editItem = (EditText) findViewById(R.id.editItem);
        String itemText = editItem.getText().toString();
        Intent data = new Intent();
        data.putExtra("task", itemText);
        data.putExtra("pos", pos);
        data.putExtra("code", 200); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
