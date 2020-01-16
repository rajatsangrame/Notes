package com.example.rajat.notes.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Note note);

    @Query("DELETE FROM note_table")
    void deleteAll();

    @Query("SELECT * from note_table ORDER BY timestamp DESC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * from note_table WHERE id=:id")
    Note getNote(int id);

}
