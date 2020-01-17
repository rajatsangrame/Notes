package com.example.rajat.notes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.rajat.notes.R;
import com.example.rajat.notes.db.Note;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String json = bundle.getString(getString(R.string.note));
            Gson gson = new Gson();
            Note note = gson.fromJson(json, Note.class);

            Toast.makeText(this, note.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}
