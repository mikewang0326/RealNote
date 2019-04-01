package com.mike.realnote.viewmodel;

import java.util.List;

import com.mike.realnote.model.NoteEntity;
import com.mike.realnote.util.SampleData;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MainViewModel extends AndroidViewModel {

    public List<NoteEntity> mNotes = SampleData.getNotes();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
