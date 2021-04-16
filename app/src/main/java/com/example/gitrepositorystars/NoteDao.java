package com.example.gitrepositorystars;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public abstract class NoteDao extends AbstractBaseEntityDao<Note> {

    @Query("select * from notes")
    public abstract List<Note> getAllNotes();

    @Query("select * from notes where noteId=:id")
    public abstract Note getOneNote(int id);
}