package com.example.gitrepositorystars;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.room.Room;

import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class AddNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((TextView)findViewById(R.id.label_name_Add)).setText("Note Name");
        ((TextView) findViewById(R.id.label_text_add)).setText("Write anything down below");

        ((View)findViewById(R.id.saveFab)).setOnClickListener((View v) -> {
            System.out.println("alo");
            TextView name = findViewById(R.id.note_add_name);
            TextView text = findViewById(R.id.note_add_text);
            NoteDatabase db = Room.databaseBuilder(getApplicationContext(),
                    NoteDatabase.class, "note_db").build();

            if(name.getText().length() > 0) {
                Runnable task = () -> {
                    long pk = db.noteDao().insert(new Note().withText(text.getText().toString()).withName(name.getText().toString()));
                    if(pk > 0){
                        Looper.prepare();
                        Toast.makeText(AddNote.this, "Saved", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddNote.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Looper.prepare();
                        Toast.makeText(AddNote.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                };

                new Thread(task).start();
            } else {
                    Toast.makeText(this, "Atleast enter your new note's name", Toast.LENGTH_SHORT).show();
            }
        });

    }
}