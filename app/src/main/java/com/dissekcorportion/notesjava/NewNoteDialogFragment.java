package com.dissekcorportion.notesjava;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class NewNoteDialogFragment extends DialogFragment {

    private NewNoteDialogViewModel mViewModel;

    private View view;

    private EditText editTextTitleNote;
    private EditText editTextContentNote;
    private RadioGroup radioGroupColors;
    private Switch switchFavorite;


    public static NewNoteDialogFragment newInstance() {
        return new NewNoteDialogFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewNoteDialogViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.title_fragment_dialog_new_note);  // titulo
        builder.setMessage(R.string.instructions_fragment_dialog_new_note)  // mensaje de qué se debe ingresar.
                .setPositiveButton(R.string.action_save_note, new DialogInterface.OnClickListener() { // boton aceptar.
                    public void onClick(DialogInterface dialog, int id) {
                        String titleNote = editTextTitleNote.getText().toString();
                        String contentNote = editTextContentNote.getText().toString();
                        String color = "Azul";
                        switch (radioGroupColors.getCheckedRadioButtonId()) {
                            case R.id.radioButtonColorGold:
                                color = "Oro"; break;
                            case R.id.radioButtonColorRed:
                                color = "Rojo"; break;
                        }
                        boolean isFavorite = switchFavorite.isChecked();
                    }
                })
                .setNegativeButton(R.string.cancel_action_save_note, new DialogInterface.OnClickListener() { // botón cancelar
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        // establecer fragment dialog personalizado.
        LayoutInflater inflater = getActivity().getLayoutInflater(); // obtenemos el contexto con getActivity.
        view = inflater.inflate(R.layout.new_note_dialog_fragment, null);// especificamos el recurso personalizado y root es null, ya que no se utilizará en otro view diferente sino en esa misa activity.

        // instanciar objetos de la vista.
        editTextTitleNote = view.findViewById(R.id.editTextTitleNote);
        editTextContentNote = view.findViewById(R.id.editTextContentNote);
        radioGroupColors = view.findViewById(R.id.radioGroupColors);
        switchFavorite = view.findViewById(R.id.switchFavorite);

        // añadir vista personbalizada al fragmentdialog.
        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create(); // devolver el cuadro de dialogo.
    }

}