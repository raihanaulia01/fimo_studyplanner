package com.example.fimostudyplanner.Flashcards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.fimostudyplanner.R;

public class FlashcardsSubject extends AppCompatActivity {

    EditText titleSubjectText;
    Button saveFlashcardBtn;
    ImageButton close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards_subject);

        titleSubjectText= findViewById(R.id.subjecttitle);
        saveFlashcardBtn= findViewById(R.id.buttonsubject);
        close= findViewById(R.id.closesubject);

        saveFlashcardBtn.setOnClickListener((v) -> );
    }
    void SaveFlashcard (){
        String SubjectTitle = titleSubjectText.getText().toString();
        if(SubjectTitle=null || SubjectTitle.isEmpty() ){
            titleSubjectText.setError("Subject is required");
            return;
        }
    }

    flashcardmodel = new ViewModelProvider(this).get(flashcardmodel.class);
}