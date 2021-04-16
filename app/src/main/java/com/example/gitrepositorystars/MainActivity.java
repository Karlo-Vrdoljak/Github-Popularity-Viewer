package com.example.gitrepositorystars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity implements RecViewAdapter.ItemClickListener {
    NoteDatabase db;
    RecViewAdapter recViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.db = Room.databaseBuilder(getApplicationContext(),
                NoteDatabase.class, "note_db").build();
      /*

        String jsonString = "[\n" +
                "    {\n" +
                "        \"name\": \"Opel Astra F\",\n" +
                "        \"text\": \"Kod boje: Z282\\nMotor: C16NZ\\nTrap: Diskovi Vectra A 256mm prednji i 260mm zadnji\\nOprema: šiber, djeljiva zadnja klupa itd.\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"Stagg gitara\",\n" +
                "        \"text\": \"Treba poravnat na bridgeu nagib žica i vidit oće li bit jači fret buzz.\\nŠtimat bridge pravilno, trebalo bi pomoć\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"name\": \"Poliranje\",\n" +
                "        \"text\": \"Brusevi: 1200, 1500, 2000\\nPolir pasta\\nAku bušilica\"\n" +
                "    }\n" +
                "]";

        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Note>>(){}.getType();

        List<Note> notes = gson.fromJson(jsonString, listType);

        for (Note note: notes) {
            System.out.println(note);
        }*/

// Lambda Runnable

        View addButton = findViewById(R.id.addFab);
        addButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, AddNote.class);
            startActivity(intent);
        });

        Runnable task = () -> {
            List<Note> allNotes = db.noteDao().getAllNotes();

            RecyclerView recyclerView = findViewById(R.id.rev_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recViewAdapter = new RecViewAdapter(MainActivity.this, allNotes);
            recViewAdapter.setOnclickListener(MainActivity.this::onItemClick);
            recyclerView.setAdapter(recViewAdapter);
        };

        new Thread(task).start();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getBaseContext(), ViewNote.class);
        Note note = recViewAdapter.getItem(position);
        intent.putExtra("note", note);
        startActivity(intent);
    }
}