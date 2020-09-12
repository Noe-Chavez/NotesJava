package com.dissekcorportion.notesjava;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dissekcorportion.notesjava.models.Note;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Note}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyNotasRecyclerViewAdapter extends RecyclerView.Adapter<MyNotasRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;
    private Context context;

    public MyNotasRecyclerViewAdapter(Context context, List<Note> items) {
        mValues = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_notas, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Se invoca por cada elemento de la lista.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewTitleNote.setText(holder.mItem.getTitle());
        holder.textViewContentNote.setText(holder.mItem.getContent());

        // si es favorita se cambia el ícono.
        if (holder.mItem.isFavorite())
            holder.imageViewFavorite.setImageResource(R.drawable.ic_baseline_star_24);

        holder.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Se hizo click", Toast.LENGTH_LONG);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewTitleNote;
        public final TextView textViewContentNote;
        public final ImageView imageViewFavorite;
        public Note mItem; // cada elemento será de tipo Note.

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewTitleNote = view.findViewById(R.id.textViewTitleNote);
            textViewContentNote = view.findViewById(R.id.textViewContentNote);
            imageViewFavorite = view.findViewById(R.id.imageViewFavorite);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewTitleNote.getText() + "'";
        }
    }
}