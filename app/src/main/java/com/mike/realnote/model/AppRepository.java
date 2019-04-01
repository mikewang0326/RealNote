package com.mike.realnote.model;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.mike.realnote.util.SampleData;

import android.content.Context;
import androidx.lifecycle.LiveData;

public class AppRepository {

    public LiveData<List<NoteEntity>> mNotes;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    private static AppRepository ourInstance;

    public static AppRepository getInstance(Context context) {
        if (null == ourInstance) {
            ourInstance = new AppRepository(context);
        }

        return ourInstance;
    }

    private AppRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
        mNotes = getAllNotes();
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().insertAllNote(SampleData.getNotes());
            }
        });
    }

    private LiveData<List<NoteEntity>> getAllNotes() {
        return mDb.noteDao().getAllNotes();
    }
}
