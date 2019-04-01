package com.mike.realnote.viewmodel;

import java.util.List;

import com.mike.realnote.model.AppRepository;
import com.mike.realnote.model.NoteEntity;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MainViewModel extends AndroidViewModel {

    public List<NoteEntity> mNotes;
    private AppRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance();
        mNotes = mRepository.mNotes;
    }
}
