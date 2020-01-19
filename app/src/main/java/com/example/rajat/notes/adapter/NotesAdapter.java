package com.example.rajat.notes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rajat.notes.databinding.ListItemsBinding;
import com.example.rajat.notes.db.Note;
import com.example.rajat.notes.interfaces.OnNoteItemClickListener;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    /**
     * List of notes added in DB
     */
    private List<Note> mNotes;
    /**
     * Used for handling adapter click events
     */
    private OnNoteItemClickListener listener;

    public NotesAdapter() {
    }

    public void setNotes(List<Note> notes) {
        mNotes = notes;
    }

    public void setListener(OnNoteItemClickListener listener) {
        this.listener = listener;
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

        /**
         * DataBinding ViewModel. Check respective xml {@link ListItemsBinding}
         */
        holder.binding.setNote(mNotes.get(position));
    }


    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ListItemsBinding binding;

        private ViewHolder(@NonNull ListItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            listener.onItemClick(mNotes.get(getAdapterPosition()), view);
        }
    }
}
