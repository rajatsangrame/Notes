package com.example.rajat.notes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.rajat.notes.R;
import com.example.rajat.notes.databinding.ActivityDetailBinding;
import com.example.rajat.notes.db.Note;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String json = bundle.getString(getString(R.string.note));
            Gson gson = new Gson();
            Note note = gson.fromJson(json, Note.class);

            binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
            binding.setNote(note);
        }
    }
}
