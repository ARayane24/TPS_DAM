package com.example.tp1;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp1.CostumAdapter.NotesRecyclerAdapter;
import com.example.tp1.Models.Notes.Note;
import com.google.android.material.textfield.TextInputEditText;

import java.util.LinkedList;
import java.util.Objects;

public class MainFrag extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinkedList<String> tags = new  LinkedList<String>();

        //dialog
        Dialog addNote = new Dialog(view.getContext());
        addNote.setContentView(R.layout.add_note_dialog);
        addNote.setTitle("Add note : ");
        addNote.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        Button saveNote = addNote.findViewById(R.id.add_note);
        Button cancel = addNote.findViewById(R.id.cancel_button);
        Button addTag = addNote.findViewById(R.id.add_tag);

        TextInputEditText title  = addNote.findViewById(R.id.input_title);
        TextInputEditText text  = addNote.findViewById(R.id.input_text);
        saveNote.setOnClickListener(e->{
            if (checkInputsNullOrEmpty(title,text)){
                Toast.makeText(getContext(), "Fill required inputs !!",Toast.LENGTH_SHORT).show();
                return;
            }

            Note note = new Note(getContext() , String.valueOf(title.getText()),String.valueOf(text.getText()),tags);
            note.setExoColumn("ALL");
            note.insertToDB();


            addNote.hide();
            init(addNote,title,text,tags);
        });

        cancel.setOnClickListener(e->{
            addNote.hide();
            init(addNote,title,text,tags);
        });

        addTag.setOnClickListener(e->{
            addTagsToNote(addNote,tags);
        });


        ImageButton add =view.findViewById(R.id.add);
        add.setOnClickListener(e->{
            addNote.show();
        });

        LinkedList<Note> allNotes = new Note(getContext()).getFromDB();
        NotesRecyclerAdapter arrayAdapter = new NotesRecyclerAdapter(view.getContext(),allNotes);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(arrayAdapter);

    }

    private void init(Dialog sender,TextInputEditText title , TextInputEditText text , LinkedList<String> Tags){
        title.setText("");
        text.setText("");
        Tags.clear();
        LinearLayout layout = sender.findViewById(R.id.Tags);
        layout.removeAllViews();
    }
    private boolean checkInputsNullOrEmpty(TextInputEditText title , TextInputEditText text){
        return  ( title == null || title.getText() == null || String.valueOf( title.getText() ).equals(""))
                || ( text == null || text.getText() == null || String.valueOf( text.getText() ).equals(""));
    }

    private void addTagsToNote(Dialog sender, LinkedList<String> tags){
        //dialog
        Dialog addTag = new Dialog(getView().getContext());
        addTag.setContentView(R.layout.add_tag_dialog);
        addTag.show();
        Objects.requireNonNull(addTag.getWindow())
                .setBackgroundDrawableResource(android.R.color.transparent);


        Button cancel = addTag.findViewById(R.id.cancel);
        Button add = addTag.findViewById(R.id.done);

        cancel.setOnClickListener(e->{addTag.hide();});

        add.setOnClickListener(e->{
            TextInputEditText input = addTag.findViewById(R.id.tag);
            if (input.getText() == null || String.valueOf( input.getText() ).isEmpty()){
                Toast.makeText(getContext(), "tag is empty !!", Toast.LENGTH_SHORT).show();
                return;
            }


            String tag = String.valueOf( input.getText() ).trim();
            LinearLayout layout = sender.findViewById(R.id.Tags);

            tags.add(tag);
            FrameLayout frameLayout =  createFrame(tag,layout,tags,sender);

            layout.addView(frameLayout);

            addTag.hide();
        });

    }

    MenuItem delete, edit;
    private FrameLayout createFrame(String tag , LinearLayout parent , LinkedList<String> tags , Dialog sender){
        FrameLayout frameLayout =  new FrameLayout(getView().getContext());
        getLayoutInflater().inflate(R.layout.tag_view,frameLayout);
        TextView text = frameLayout.findViewById(R.id.txt);
        text.setText(tag);

        frameLayout.setOnClickListener(e->{
            PopupMenu menu = new PopupMenu(getView().getContext(),text);
            menu.inflate(R.menu.tag_clicked_menu);
            delete = menu.getMenu().findItem(R.id.remove);
            edit = menu.getMenu().findItem(R.id.edit);
            delete.setOnMenuItemClickListener((menuItem)->{
                tags.remove(tag);
                parent.removeView(frameLayout);
                return true;
            });
            edit.setOnMenuItemClickListener((menuItem)->{
                if (menuItem == edit)
                    addTagsToNote(sender,tags);
                tags.remove(tag);
                parent.removeView(frameLayout);
                return true;
            });

            menu.show();
        });
        return frameLayout;
    }


}