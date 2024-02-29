package com.example.tp1.TP1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.tp1.databinding.MyView;

public class BindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView binding = MyView.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.slide.addOnChangeListener((pr1,pr2,pr3)->{
           binding.slide.setValue((int)pr2) ;
        });
    }


}