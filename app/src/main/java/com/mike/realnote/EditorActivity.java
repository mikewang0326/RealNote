package com.mike.realnote;

import com.mike.realnote.model.NoteEntity;
import com.mike.realnote.viewmodel.EditorViewModel;

import android.os.Bundle;
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
    }
}
