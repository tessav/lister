package com.example.tessav.simpletodo;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {
    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_listview, parent, false);
        }

        TextView taskName = (TextView) convertView.findViewById(R.id.taskName);
        ImageButton taskPriority = (ImageButton) convertView.findViewById(R.id.taskPriority);
        TextView dueDate = (TextView) convertView.findViewById(R.id.dueDate);

        taskName.setText(task.task_name);
        dueDate.setText(task.duedate);

        if (task.priority == 0) {
            taskPriority.setColorFilter(Color.rgb(255,68,68));
        } else if (task.priority == 1) {
            taskPriority.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent));
        } else {
            taskPriority.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        }

        return convertView;
    }
}