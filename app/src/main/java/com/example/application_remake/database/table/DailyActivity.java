package com.example.application_remake.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "DailyActivity")
public class DailyActivity {

    @ColumnInfo(name = "daily_activity_id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "activity_name")
    private String activity;

    public DailyActivity() {
    }

    @Ignore
    public DailyActivity(String activity) {
        this.activity = activity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
