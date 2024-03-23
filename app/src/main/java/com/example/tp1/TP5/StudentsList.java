package com.example.tp1.TP5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.Toast;

import com.example.tp1.R;
import com.example.tp1.databinding.Tp5ActivityStudentsListBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class StudentsList extends AppCompatActivity {

    Tp5ActivityStudentsListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Tp5ActivityStudentsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        if (intent == null) return;

        ArrayList<Etudiant> all = (ArrayList<Etudiant>) intent.getSerializableExtra("com.example.tp1.TP5.all");
        ArrayAdapter<Etudiant> adapter = new TP5Adapter(getApplicationContext(),all);
        binding.list.setAdapter(adapter);
        try {

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }


    }
}