package com.example.tessav.simpletodo;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class EditItemActivity extends AppCompatActivity implements OnClickListener {
    private int pos;
    private EditText dateText;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        getSupportActionBar().setTitle("Edit Task");

        pos = getIntent().getIntExtra("pos", 0);
        int priority = getIntent().getIntExtra("priority", 1);
        String task = getIntent().getStringExtra("task_name");
        String duedate = getIntent().getStringExtra("duedate");
        int priority_id;
        switch(priority) {
            case 2:
                priority_id = R.id.low; break;
            case 0:
                priority_id = R.id.high; break;
            default:
                priority_id = R.id.medium;
        }
        RadioButton pButton = (RadioButton) findViewById(priority_id);
        EditText editItem = (EditText) findViewById(R.id.editItem);
        dateText = (EditText) findViewById(R.id.duedate_selector);

        pButton.setChecked(true);
        editItem.setText(task);
        editItem.setSelection(editItem.getText().length());

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        dateText.setText(duedate);
        setDateField();
    }

    private void setDateField() {
        dateText.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateText.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_done:
                EditText editItem = (EditText) findViewById(R.id.editItem);
                RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
                int index = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
                String itemText = editItem.getText().toString();
                String dueDate = dateText.getText().toString();
                Intent data = new Intent();
                data.putExtra("task_name", itemText);
                data.putExtra("priority", index);
                data.putExtra("pos", pos);
                data.putExtra("duedate", dueDate);
                data.putExtra("code", 200);
                setResult(RESULT_OK, data);
                finish();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        datePickerDialog.show();
    }
}
