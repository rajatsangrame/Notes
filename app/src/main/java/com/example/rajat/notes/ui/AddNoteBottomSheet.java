package com.example.rajat.notes.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.rajat.notes.R;
import com.example.rajat.notes.databinding.FragmentBottomSheetBinding;
import com.example.rajat.notes.db.Note;
import com.example.rajat.notes.interfaces.BottomSheetResult;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class AddNoteBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener {
    private static final String TAG = "AddNoteBottomSheet";
    private BottomSheetResult sheetResult;
    private FragmentBottomSheetBinding mBinding;

    public AddNoteBottomSheet() {
    }

    AddNoteBottomSheet(BottomSheetResult sheetResult) {
        this.sheetResult = sheetResult;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_sheet, container, false);
        mBinding.btnSave.setOnClickListener(this);
        mBinding.btnClose.setOnClickListener(this);
        disableScrollingWritingNote();
        return mBinding.getRoot();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
                setupBottomSheet(bottomSheetDialog);
            }
        });
        return dialog;
    }

    private void setupBottomSheet(BottomSheetDialog bottomSheetDialog) {
        FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();

        int windowHeight = getWindowHeight();
        if (layoutParams != null) {
            layoutParams.height = windowHeight;
        }
        bottomSheet.setLayoutParams(layoutParams);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    dismiss();
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void disableScrollingWritingNote() {

        mBinding.etNote.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {

                view.getParent().requestDisallowInterceptTouchEvent(true);
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                }

                return false;
            }
        });
    }

    private int getWindowHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_save) {
            if (mBinding.etTitle.getText().toString().isEmpty()) {
                sheetResult.onError(getString(R.string.title_empty_error));
                return;
            } else if (mBinding.etNote.getText().toString().isEmpty()) {
                sheetResult.onError(getString(R.string.note_empty_error));
                return;
            } else {
                Note note = new Note(mBinding.etTitle.getText().toString(),
                        mBinding.etNote.getText().toString(),
                        System.currentTimeMillis());
                sheetResult.onSave(note);
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(getString(R.string.note), note.toJsonString());
                startActivity(intent);
            }
            dismiss();

        } else if (v.getId() == R.id.btn_close) {
            dismiss();
        }
    }

}
