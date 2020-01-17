package com.example.rajat.notes.interfaces;

import com.example.rajat.notes.db.Note;

/**
 * Created by Rajat Sangrame on 18/1/20.
 * http://github.com/rajatsangrame
 */
public interface OnBottomSheetListener {

    void onSave(Note note);

    void onError(String message);
}
