package com.example.tp1.CostumAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tp1.Models.Notes.Note;
import com.example.tp1.R;

import java.util.LinkedList;

public class NotesAdapter extends ArrayAdapter<Note> {
    private final LinkedList<Note> notes;
    private final Context context;
    public NotesAdapter(@NonNull Context context, @NonNull LinkedList<Note> objects) {
        super(context, R.layout.note_item, objects);
        this.context = context;
        this.notes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View rowView = inflater.inflate(R.layout.note_item, null,true);

        TextView title = rowView.findViewById(R.id.Title);
        TextView Created_at = rowView.findViewById(R.id.Created_at);
        TextView Last_modification = rowView.findViewById(R.id.Last_modification);

        title.setText(notes.get(position).getTitle());
        String createdAt = Created_at.getText() + " " + notes.get(position).getCreated_at();
        Created_at.setText(createdAt);
        String lastModification = Last_modification.getText() + " " + notes.get(position).getCreated_at();
        Last_modification.setText(lastModification);
        putTags(inflater, rowView.findViewById(R.id.Tags) , rowView, notes.get(position).getTags());

        return rowView;
    }

    private void putTags(LayoutInflater inflater, LinearLayout viewById,View view , LinkedList<String> tags) {
        for (String t :
                tags) {
            FrameLayout frameLayout =  new FrameLayout(view.getContext());
            inflater.inflate(R.layout.tag_view,frameLayout);

            TextView text = frameLayout.findViewById(R.id.txt);
            text.setText(t);

            viewById.addView(frameLayout);
        }
    }


}
