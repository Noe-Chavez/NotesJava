package com.dissekcorportion.notesjava;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dissekcorportion.notesjava.models.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class NotasFragment extends Fragment {

    private List<Note> notes;
    private MyNotasRecyclerViewAdapter adapterNotes;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NotasFragment() {
        notes = new ArrayList<>();
        notes.add(new Note("Prueba Titulo", "Contenido Prueba", true, 45));
    }

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

            adapterNotes = new MyNotasRecyclerViewAdapter(getContext(), notes);
            recyclerView.setAdapter(adapterNotes);
        }
        return view;
    }
}