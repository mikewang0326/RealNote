package com.mike.realnote;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mike.realnote.model.AppDatabase;
import com.mike.realnote.model.NoteDao;
import com.mike.realnote.model.NoteEntity;
import com.mike.realnote.util.SampleData;

import android.content.Context;
import android.util.Log;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;



@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    public static final String TAG = "junit";
    private AppDatabase mDb;
    private NoteDao mDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mDao = mDb.noteDao();
        Log.i(TAG, "Create DB");
    }

    @After
    public void closeDb() {
        mDb.close();
        Log.i(TAG, "closeDb");
    }

    @Test
    public void createAndRetrieveNotes() {
        mDao.insertAllNote(SampleData.getNotes());
        int count = mDao.getCount();
        Log.i(TAG, "Create DB createAndRetrieveNotes() count=" + count);
        Assert.assertEquals(SampleData.getNotes().size(), count);
    }

    @Test
    public void compareStrings() {
        mDao.insertAllNote(SampleData.getNotes());

        NoteEntity original = SampleData.getNotes().get(0);
        NoteEntity fromDb = mDao.getNoteById(1);

        Assert.assertEquals(original.getText(), fromDb.getText());
        Assert.assertEquals(1, fromDb.getId());
    }
}
