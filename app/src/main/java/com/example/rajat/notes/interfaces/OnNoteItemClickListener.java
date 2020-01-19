package com.example.rajat.notes.interfaces;

import android.view.View;

import com.example.rajat.notes.db.Note;

/**
 * Created by Rajat Sangrame on 18/1/20.
 * http://github.com/rajatsangrame
 */
public interface OnNoteItemClickListener {

    void onItemClick(Note note, View view);

}
