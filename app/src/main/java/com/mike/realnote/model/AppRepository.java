package com.mike.realnote.model;

import java.util.List;

import com.mike.realnote.util.SampleData;

public class AppRepository {

    public List<NoteEntity> mNotes;

    private static final AppRepository ourInstance = new AppRepository();

    public static AppRepository getInstance() {
        return ourInstance;
    }

    private AppRepository() {
        mNotes = SampleData.getNotes();
    }
}
