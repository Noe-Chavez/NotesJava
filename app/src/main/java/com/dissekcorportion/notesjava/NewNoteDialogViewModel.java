package com.dissekcorportion.notesjava;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dissekcorportion.notesjava.models.Note;
import com.dissekcorportion.notesjava.viewmodel.NoteRepository;

import java.util.List;

public class NewNoteDialogViewModel extends AndroidViewModel {
    // Datos que queremos trasferir.
    private LiveData<List<Note>> allNotes;
    private NoteRepository noteRepository;

    public NewNoteDialogViewModel(Application application) {
        super(application);

        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAll();

    }

    // El framento que necesita recibir la nueva lista de datos.
    public LiveData<List<Note>> getAllNotes() { return allNotes; }

    // El Fragment que inserte una nueva nota, deberá comunicarlo a este ViewModel.
    public void insertNote(Note note) { noteRepository.insert(note); }

}