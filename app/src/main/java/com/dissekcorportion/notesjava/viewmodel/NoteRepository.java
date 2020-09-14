package com.dissekcorportion.notesjava.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dissekcorportion.notesjava.daos.NoteDao;
import com.dissekcorportion.notesjava.db.NoteRoomDataBase;
import com.dissekcorportion.notesjava.models.Note;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;
    private LiveData<List<Note>> allNotesFavorites;

    // Application tiene el contexto.
    public NoteRepository(Application application) {
        // Obtenemos la instancia a la base de datos.
        NoteRoomDataBase db = NoteRoomDataBase.getDataBase(application);
        noteDao = db.noteDao();
        allNotes = noteDao.getAllNotes();
        allNotesFavorites = noteDao.getAllFavorite();
    }
    // Declarar Operaciones que el repository puede hacer.
    public LiveData<List<Note>> getAll() {
        return allNotes;
    }

    public LiveData<List<Note>> getAllFavorites() {
        return allNotesFavorites;
    }

    // Para poder hacer estas opereacionbes se deben de realizar en segundo plano.
    public void insert(Note note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDaoAsyncTask;

        public InsertAsyncTask(NoteDao noteDao) {
            noteDaoAsyncTask = noteDao;
        }

        // Ejecutar la tarea en segundo plano.
        @Override
        protected Void doInBackground(Note... notes) {    // uso de vars atgs
            noteDaoAsyncTask.insertNote(notes[0]);
            return null;
        }
    }

}
