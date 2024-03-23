package com.example.tp1.TP5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tp1.R;
import com.example.tp1.databinding.Tp5FragmentBinding;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class TP5 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tp5_fragment, container, false);
    }

    Tp5FragmentBinding binding;
    BDDHelp bddHelp;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         binding= Tp5FragmentBinding.bind(view);
        binding.ajouter.setOnClickListener(this::add);
        binding.supprimer.setOnClickListener(this::supp);
        binding.Chercher.setOnClickListener(this::search);
        binding.modifier.setOnClickListener(this::Modify);
        binding.AfficherTous.setOnClickListener(this::showAll);

        bddHelp = new BDDHelp(getContext());
        binding.addFromCamera.setOnClickListener(this::addImage);
        binding.addFromGall.setOnClickListener(this::addImage);
    }

    private void addImage(View view) {
        if (view == binding.addFromCamera){
            openCamera();
        }else {
            openGall();
        }



        Bitmap bmp = ((BitmapDrawable)binding.img.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] image = stream.toByteArray();
    }

    public void openCamera() {
        Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult (takePicture, 0);
    }

    public void openGall() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult (pickPhoto, 1);
    }

    private void showAll(View view) {

        Intent intent = new Intent(getContext(), StudentsList.class);
        intent.putExtra("com.example.tp1.TP5.all",bddHelp.getAll());
        startActivity(intent);
    }


    private void Modify(View view) {
       boolean b =  bddHelp.update(new Etudiant(0, binding.mat.getText().toString() ,
                binding.nom.getText().toString(), binding.prenom.getText().toString()));

       if (b){
           Toast.makeText(getContext() , "Success !!!" , Toast.LENGTH_SHORT).show();
       }else {
           Toast.makeText(getContext() , "Error !!!" , Toast.LENGTH_SHORT).show();
       }
    }

    private void add(View view) {
        boolean b = bddHelp.insert(new Etudiant( binding.mat.getText().toString() ,
                binding.nom.getText().toString(), binding.prenom.getText().toString()));

        if (b){
            Toast.makeText(getContext() , "Success !!!" , Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext() , "Error !!!" , Toast.LENGTH_SHORT).show();
        }
    }

    private void supp(View view) {
        boolean b = bddHelp.delete(new Etudiant( binding.mat.getText().toString() ,
                binding.nom.getText().toString(), binding.prenom.getText().toString()));

        if (b){
            Toast.makeText(getContext() , "Success !!!" , Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext() , "Error !!!" , Toast.LENGTH_SHORT).show();
        }

    }

    private void search(View view) {


        Intent intent = new Intent(getContext(), StudentsList.class);
        ArrayList<Etudiant> value = new ArrayList<>();

        String searchString = binding.mat.getText().toString();
        if (searchString.isEmpty()) {
            Toast.makeText(getContext(),"Error : Mat empty !!",Toast.LENGTH_SHORT ).show();
            return;
        }

        value.add(bddHelp.getByMat(searchString));
        intent.putExtra("com.example.tp1.TP5.all", value);
        startActivity(intent);
    }
}