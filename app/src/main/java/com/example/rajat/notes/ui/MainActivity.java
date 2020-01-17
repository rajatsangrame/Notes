package com.example.rajat.notes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.rajat.notes.R;
import com.example.rajat.notes.adapter.NotesAdapter;
import com.example.rajat.notes.databinding.ActivityMainBinding;
import com.example.rajat.notes.db.Note;
import com.example.rajat.notes.interfaces.OnBottomSheetListener;
import com.example.rajat.notes.interfaces.OnItemClickListener;
import com.example.rajat.notes.viewmodel.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnBottomSheetListener, OnItemClickListener {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    NoteViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        final NotesAdapter adapter = new NotesAdapter();
        adapter.setListener(this);
        mViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
                binding.recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.i(TAG, "onChanged: " + notes.size());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }

    AddNoteBottomSheet bottomSheet;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                bottomSheet = new AddNoteBottomSheet(this);
                bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSave(Note note) {

        mViewModel.insert(note);
        Log.i(TAG, "onSave Note Added: " + note.getTitle());
    }

    @Override
    public void onError(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(Note note) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(getString(R.string.note), note.toJsonString());
        startActivity(intent);
    }
}
