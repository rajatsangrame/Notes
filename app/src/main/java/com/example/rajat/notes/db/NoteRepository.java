package com.example.rajat.notes.db;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> allNotes;
    private static final String TAG = "NoteRepository";

    /**
     * Room Database is manged through this repository. Called form ViewModel
     *
     * @param application needed to create db instance
     */
    public NoteRepository(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        allNotes = mNoteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public Note getNoteById(int id) {
        return mNoteDao.getNote(id);
    }

    /**
     * Executes a background task to insert {@link Note} in database
     *
     * @param note instance of {@link Note}
     */
    public void insert(Note note) {
        new insertAsyncTask(mNoteDao).execute(note);
    }

    private static class insertAsyncTask extends AsyncTask<Note, Void, Long> {

        private NoteDao mAsyncTaskDao;

        insertAsyncTask(NoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final Note... params) {
            return mAsyncTaskDao.insert(params[0]);
        }

        /**
         * @param aLong id of {@link Note} column. If failed return -1
         */
        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            Log.i(TAG, "onPostExecute Result: " + aLong);
        }
    }
}
