package com.example.tp1.CostumAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp1.Fragments.Notes;
import com.example.tp1.Models.Notes.Note;
import com.example.tp1.NoteDetails;
import com.example.tp1.R;

import java.util.Date;
import java.util.LinkedList;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {
    private final LinkedList<Note> notes;
    private final Context context;
    public NotesRecyclerAdapter(@NonNull Context context, @NonNull LinkedList<Note> objects) {
        this.context = context;
        this.notes = objects;
    }


    private void putTags(LayoutInflater inflater, LinearLayout viewById, Context context , LinkedList<String> tags) {
        for (String t :
                tags) {
            FrameLayout frameLayout =  new FrameLayout(context);
            inflater.inflate(R.layout.tag_view,frameLayout);

            TextView text = frameLayout.findViewById(R.id.txt);
            text.setText(t);

            viewById.addView(frameLayout);
        }
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, Created_at,Last_modification;
        LinearLayout linearLayout;
        LayoutInflater inflater;
        View rowView;
        public ViewHolder(@NonNull View rowView , LayoutInflater inflater) {
            super(rowView);
            this.inflater = inflater;
             title = rowView.findViewById(R.id.Title);
             Created_at = rowView.findViewById(R.id.Created_at);
             Last_modification = rowView.findViewById(R.id.Last_modification);
             linearLayout = rowView.findViewById(R.id.Tags);
             this.rowView = rowView;
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.note_item, parent,false);
        return new ViewHolder(rowView,inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesRecyclerAdapter.ViewHolder holder, int position) {
        holder.title.setText(notes.get(position).getTitle());
        String createdAt = holder.Created_at.getText() + " " + notes.get(position).getCreated_at();
        holder.Created_at.setText(createdAt);
        String lastModification = holder.Last_modification.getText() + " " + notes.get(position).getCreated_at();
        holder.Last_modification.setText(lastModification);

        putTags(holder.inflater, holder.linearLayout , context, notes.get(position).getTags());

        //Handling click listeners :
        holder.rowView.setOnClickListener(e->{
            new NoteDetails(notes.get(position));
        });

    }



}
