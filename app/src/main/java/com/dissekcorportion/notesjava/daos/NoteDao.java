package com.dissekcorportion.notesjava.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dissekcorportion.notesjava.models.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Query("DELETE FROM notes")
    void deleteAllNotes();

    @Query("DELETE FROM notes WHERE id_note = :idNote")
    void deleteNote(int idNote);

    @Query("SELECT * FROM notes ORDER BY title ASC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes WHERE favorite LIKE 'true'")
    LiveData<List<Note>> getAllFavorite();

}
