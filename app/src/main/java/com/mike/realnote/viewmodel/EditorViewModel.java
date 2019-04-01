package com.mike.realnote.viewmodel;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.mike.realnote.model.AppRepository;
import com.mike.realnote.model.NoteEntity;

import android.app.Application;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class EditorViewModel extends AndroidViewModel {
    public MutableLiveData<NoteEntity> mLiveNote = new MutableLiveData<>();
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public EditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
    }

    public void loadData(final int noteId) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                NoteEntity note = mRepository.getNoteById(noteId);
                mLiveNote.postValue(note);
            }
        });
    }

    public void saveNote(String noteText) {
        NoteEntity note = mLiveNote.getValue();

        if (null != note) {
            note.setText(noteText.trim());
        } else {
            if (TextUtils.isEmpty(noteText.trim())) {
                return;
            }

            note = new NoteEntity(new Date(), noteText.trim());
        }

        mRepository.insertNote(note);
    }
}
