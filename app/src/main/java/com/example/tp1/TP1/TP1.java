package com.example.tp1.TP1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tp1.MainActivity;
import com.example.tp1.R;
import com.example.tp1.TP2.TP2;
import com.google.android.material.navigation.NavigationView;

public class TP1 extends Fragment {
    Button exo1,exo2,exo3,exo3_eng,exo4, binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        exo1 = view.findViewById(R.id.exo1);
        exo2 = view.findViewById(R.id.exo2);
        exo3 = view.findViewById(R.id.exo3);
        exo3_eng = view.findViewById(R.id.exo3_eng);
        exo4 = view.findViewById(R.id.exo4);
        binding = view.findViewById(R.id.binding);

        exo1.setOnClickListener(this::goToSolution);
        exo2.setOnClickListener(this::goToSolution);
        exo3.setOnClickListener(this::goToSolution);
        exo3_eng.setOnClickListener(this::goToSolution);
        exo4.setOnClickListener(this::goToSolution);
        binding.setOnClickListener(this::goToSolution);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_tp1, container, false);
    }

    private void goToSolution(View view) {
        Intent i;
        if (view == exo1){
            i = new Intent(getActivity().getApplicationContext(),Exo1.class);
            i.putExtra("Exo", "EXO : 1.1");
        } else if (view == exo2) {
            i = new Intent(getActivity().getApplicationContext(),Exo2.class);
            i.putExtra("Exo", "EXO : 1.2");
        } else if (view == exo3) {
            i = new Intent(getActivity().getApplicationContext(),Exo3.class);
            i.putExtra("Exo", "EXO : 1.3");
        }  else if (view == exo3_eng) {
            i = new Intent(getActivity().getApplicationContext(), Exo3_v2.class);
            i.putExtra("Exo", "EXO : 1.3v2");
        } else if (view == exo4) {
            i = new Intent(getActivity().getApplicationContext(),Exo4.class);
            i.putExtra("Exo", "EXO : 1.4");
        }else if (view == binding) {
            i = new Intent(getActivity().getApplicationContext(),BindingActivity.class);
            i.putExtra("Binding", "Binding");
        }else {
            Toast.makeText(getActivity().getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(i);
    }
}