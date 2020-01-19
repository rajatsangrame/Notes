package com.example.rajat.notes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.example.rajat.notes.R;
import com.example.rajat.notes.adapter.NotesAdapter;
import com.example.rajat.notes.databinding.ActivityMainBinding;
import com.example.rajat.notes.db.Note;
import com.example.rajat.notes.interfaces.BottomSheetResult;
import com.example.rajat.notes.interfaces.OnNoteItemClickListener;
import com.example.rajat.notes.viewmodel.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomSheetResult, OnNoteItemClickListener {

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

                if (notes.size() > 0) {
                    binding.layoutNoNotes.setVisibility(View.GONE);
                }
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
                performMenuItemClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void performMenuItemClick() {
        bottomSheet = new AddNoteBottomSheet(this);
        bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
    }

    @Override
    public void onSave(Note note) {

        mViewModel.insert(note);
        Log.i(TAG, "onSave Note Added: " + note.getTitle());
    }

    @Override
    public void onError(String message) {

        showErrorMessage(message);
    }

    @Override
    public void onItemClick(Note note, View v) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(getString(R.string.note), note.toJsonString());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            View v1 = v.findViewById(R.id.tv_title);
            View v2 = v.findViewById(R.id.tv_desc);
            final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    Pair.create(v1, v1.getTransitionName()), Pair.create(v2, v2.getTransitionName()));

            startActivity(intent, options.toBundle());
            return;
        }

        startActivity(intent);
    }

    private void showErrorMessage(String message) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setNegativeButton(getString(R.string.close), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setMessage(message)
                .setCancelable(true)
                .show();
    }
}
