package com.mike.realnote;

import java.util.ArrayList;
import java.util.List;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mike.realnote.model.NoteEntity;
import com.mike.realnote.ui.NotesAdapter;
import com.mike.realnote.viewmodel.MainViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    @BindView(R.id.recycler_view)
    RecyclerView mRecycleView;

    private List<NoteEntity> notesData = new ArrayList<>();
    private NotesAdapter mAdapter;
    private MainViewModel mMainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initViewModel();
        initRecycleView();

        FloatingActionButton fab = findViewById(R.id.main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        notesData.addAll(mMainViewModel.mNotes);

        for (NoteEntity note: notesData) {
            Log.i(TAG, note.toString());
        }

    }

    private void initViewModel() {
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    private void initRecycleView() {
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);

        mAdapter = new NotesAdapter(notesData, this);
        mRecycleView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
