package com.example.gitrepositorystars;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "notes")
public class Note implements Serializable
{

    @PrimaryKey(autoGenerate = true)
    private int noteId;


    @SerializedName("name")
    @Expose
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("text")
    @Expose
    @ColumnInfo(name = "text")
    private String text;
    private final static long serialVersionUID = -3609242066476436161L;

    @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP")
    @TypeConverters(DateConverter.class)
    private Date createdAt;

    @ColumnInfo(name = "updated_at", defaultValue = "CURRENT_TIMESTAMP")
    @TypeConverters(DateConverter.class)
    private Date updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public Note() {
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public Note withName(String name) {
        this.name = name;
        return this;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Note withText(String text) {
        this.text = text;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Note{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
