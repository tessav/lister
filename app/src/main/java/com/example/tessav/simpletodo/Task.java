package com.example.tessav.simpletodo;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = ListerDatabase.class)
public class Task extends BaseModel {
    @Column
    @NotNull
    @PrimaryKey(autoincrement = true)
    int task_id;

    @Column
    String task_name;

    @Column(defaultValue = "2")
    int priority;
}
