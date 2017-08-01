package com.example.tessav.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditItemActivity extends AppCompatActivity {
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        pos = getIntent().getIntExtra("pos", 0);
        int priority = getIntent().getIntExtra("priority", 2);
        int priority_id = R.id.low;
        if (priority == 0) {
            priority_id = R.id.high;
        } else if (priority == 1) {
            priority_id = R.id.medium;
        }
        RadioButton pButton = (RadioButton) findViewById(priority_id);
        pButton.setChecked(true);
        String task = getIntent().getStringExtra("task_name");

        EditText editItem = (EditText) findViewById(R.id.editItem);
        editItem.setText(task);
        editItem.setSelection(editItem.getText().length());
    }

    public void onEditItem(View v) {
        EditText editItem = (EditText) findViewById(R.id.editItem);
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        int index = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
        System.out.println(index);
        String itemText = editItem.getText().toString();
        Intent data = new Intent();
        data.putExtra("task_name", itemText);
        data.putExtra("priority", index);
        data.putExtra("pos", pos);
        data.putExtra("code", 200);
        setResult(RESULT_OK, data);
        finish();
    }
}
