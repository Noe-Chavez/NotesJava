package com.dissekcorportion.notesjava.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dissekcorportion.notesjava.NewNoteDialogFragment;
import com.dissekcorportion.notesjava.NewNoteDialogViewModel;
import com.dissekcorportion.notesjava.R;
import com.dissekcorportion.notesjava.models.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class NotasFragment extends Fragment {

    private List<Note> notes;
    private MyNotasRecyclerViewAdapter adapterNotes;
    private NewNoteDialogViewModel noteViewModel;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotasFragment newInstance(int columnCount) {
        NotasFragment fragment = new NotasFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        // indicamos que el fragmento tiene un menu deopciones propio.
        setHasOptionsMenu(true);
    }

    /**
     *
     * @param inflater: adopta tres argumentos.
     *                # El ID del recurso de diseño que quieres agrandar.
     *                # El ViewGroup que será el elemento principal del diseño agrandado (es importante pasar container para que el sistema aplique parámetros de diseño a la vista de raíz del diseño agrandado, especificada por la vista principal a la que se integra).
     *                # Un valor booleano que indica si se debe anexar el diseño aumentado al ViewGroup (el segundo parámetro) durante el agrandamiento (en este caso, es falso porque el sistema ya está insertando el diseño aumentado al container; al pasar "true", se crearía un grupo de vistas redundante en el diseño final).
     * @param container: es el ViewGroup principal (del diseño de la actividad) en el cual se insertará el diseño de tu fragmento.
     * @param savedInstanceState: es un Bundle que proporciona datos acerca de la instancia previa del fragmento si el fragmento se está reanudando.
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notas_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            notes = new ArrayList<>();

            adapterNotes = new MyNotasRecyclerViewAdapter(getContext(), notes);
            recyclerView.setAdapter(adapterNotes);

            lanzarViewModel();
        }
        return view;
    }

    private void lanzarViewModel() {
        noteViewModel = ViewModelProviders.of(getActivity()).get(NewNoteDialogViewModel.class);
        noteViewModel.getAllNotes().observe(getActivity(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapterNotes.setNewNotes(notes);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_note_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_note:
                showDialogNewNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialogNewNote() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        NewNoteDialogFragment newNoteDialogFragment = new NewNoteDialogFragment();
        newNoteDialogFragment.show(fragmentManager, "NewNoteDialogFragment");
    }
}