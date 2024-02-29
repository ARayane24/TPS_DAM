package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.tp1.Models.Notes.Note;

public class NoteDetails extends AppCompatActivity {

    private final Note note;
    public NoteDetails(Note note) {
        this.note = note;
    }

    EditText title , text ;
    Button Update , Delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);


    }
}