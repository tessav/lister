package com.example.tessav.simpletodo;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

@Database(name = ListerDatabase.NAME, version = ListerDatabase.VERSION)
public class ListerDatabase {
    public static final String NAME = "ListerDataBase";

    public static final int VERSION = 2;

    @Migration(version = 2, database = ListerDatabase.class)
    public static class Migration2 extends AlterTableMigration<Task> {

        public Migration2(Class<Task> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.INTEGER, "priority");
        }
    }
}