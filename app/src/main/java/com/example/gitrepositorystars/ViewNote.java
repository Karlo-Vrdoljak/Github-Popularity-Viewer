package com.example.gitrepositorystars;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.room.Room;

import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;

public class ViewNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NoteDatabase db = Room.databaseBuilder(getApplicationContext(),
                NoteDatabase.class, "note_db").build();

        Note note = (Note)getIntent().getSerializableExtra("note");

        ((TextView)findViewById(R.id.label_name_Add)).setText("Note Name");
        ((TextView)findViewById(R.id.note_add_name)).setText(note.getName());
        ((TextView) findViewById(R.id.label_text_add)).setText("Edit your note text below");
        ((TextView)findViewById(R.id.note_add_text)).setText(note.getText());

        ((View)findViewById(R.id.deleteFab)).setOnClickListener((View v) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ViewNote.this);
            builder.setTitle("Attention");
            builder.setMessage("Are you sure you want to delete this note?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    Runnable task = () -> {
                        Looper.prepare();
                        db.noteDao().delete(note);
                        Toast.makeText(ViewNote.this, "Deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ViewNote.this, MainActivity.class);
                        startActivity(intent);
                    };
                    new Thread(task).start();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        });


        ((View)findViewById(R.id.saveFab)).setOnClickListener((View v) -> {
            TextView name = findViewById(R.id.note_add_name);
            TextView text = findViewById(R.id.note_add_text);


            if(name.getText().length() > 0) {
                Runnable task = () -> {
                    note.setName(name.getText().toString());
                    note.setText(text.getText().toString());
                    db.noteDao().update(note);
                    Looper.prepare();
                    Toast.makeText(ViewNote.this, "Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ViewNote.this, MainActivity.class);
                    startActivity(intent);
                };

                new Thread(task).start();
            } else {
                Toast.makeText(this, "Atleast enter your note's name", Toast.LENGTH_SHORT).show();
            }
        });

    }
}