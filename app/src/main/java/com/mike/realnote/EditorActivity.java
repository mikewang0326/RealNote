package com.mike.realnote;

import com.mike.realnote.model.NoteEntity;
import com.mike.realnote.util.Constant;
import com.mike.realnote.viewmodel.EditorViewModel;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EditorActivity extends AppCompatActivity {
    @BindView(R.id.note_text)
    TextView mTextView;


    private EditorViewModel mEditorViewModel;
    private boolean mNewnote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        
        initViewModel();
    }

    private void initViewModel() {
        mEditorViewModel = ViewModelProviders.of(this).get(EditorViewModel.class);

        mEditorViewModel.mLiveNote.observe(this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(NoteEntity noteEntity) {
                mTextView.setText(noteEntity.getText());
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle(getString(R.string.new_note));
            mNewnote = true;
        } else {
            setTitle(getString(R.string.edit_note));
            int noteId = extras.getInt(Constant.NOTE_ID_KEY);
            mEditorViewModel.loadData(noteId);
            mNewnote = false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            mEditorViewModel.deleteNote();
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNewnote) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_editor, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        saveAndReturn();
        super.onBackPressed();
    }

    private void saveAndReturn() {
        mEditorViewModel.saveNote(mTextView.getText().toString());
        finish();
    }
}
