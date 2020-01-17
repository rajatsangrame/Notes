package com.example.rajat.notes.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rajat.notes.databinding.ListItemsBinding;
import com.example.rajat.notes.db.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> mNotes;

    public NotesAdapter(List<Note> notes) {
        mNotes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemsBinding binding = ListItemsBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.setNote(mNotes.get(position));
    }


    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ListItemsBinding binding;

        private ViewHolder(@NonNull ListItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
