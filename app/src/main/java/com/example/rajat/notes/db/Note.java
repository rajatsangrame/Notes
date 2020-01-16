package com.example.rajat.notes.db;

import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Locale;

/***
 * Model class for Notes.
 *
 * Simple variable names.
 * Only additional method is {@link this.getTimeStampString} to convert timestamp to string
 *
 */
@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "desc")
    private String desc;

    @NonNull
    @ColumnInfo(name = "timestamp")
    private Long timestamp;

    public Note(@NonNull String title, @NonNull String desc, @NonNull Long timestamp) {
        this.title = title;
        this.desc = desc;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDesc() {
        return desc;
    }

    public void setDesc(@NonNull String desc) {
        this.desc = desc;
    }

    @NonNull
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * get current timestamp and convert to string
     *
     * @return String date for ex Thursday, 15 Jan 2020
     */
    public String getTimeStampString() {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTimeInMillis(this.timestamp * 1000);
        return DateFormat.format("EEEE, dd-MM-yyyy", cal).toString();
    }
}
