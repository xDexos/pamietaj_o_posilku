package com.example.application_remake.database.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "daily_mood")
public class DailyMood {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "samopoczucie")
    private String samopoczucie;

    @ColumnInfo(name = "nastroj")
    private String nastroj;

    @ColumnInfo(name = "poziom_energii")
    private String poziom_energii;

    public DailyMood() {
    }

    @Ignore
    public DailyMood(int id, String samopoczucie, String nastroj, String poziom_energii) {
        this.id = id;
        this.samopoczucie = samopoczucie;
        this.nastroj = nastroj;
        this.poziom_energii = poziom_energii;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSamopoczucie() {
        return samopoczucie;
    }

    public void setSamopoczucie(String samopoczucie) {
        this.samopoczucie = samopoczucie;
    }

    public String getNastroj() {
        return nastroj;
    }

    public void setNastroj(String nastroj) {
        this.nastroj = nastroj;
    }

    public String getPoziom_energii() {
        return poziom_energii;
    }

    public void setPoziom_energii(String poziom_energii) {
        this.poziom_energii = poziom_energii;
    }
}
