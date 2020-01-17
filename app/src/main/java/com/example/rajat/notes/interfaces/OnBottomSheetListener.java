package com.example.rajat.notes.interfaces;

/**
 * Created by Rajat Sangrame on 18/1/20.
 * http://github.com/rajatsangrame
 */
public interface OnBottomSheetListener {

    void onSave(String title, String desc);

    void onError(String message);
}
