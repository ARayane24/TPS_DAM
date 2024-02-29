package com.example.tp1.TP2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tp1.Fragments.JAVA;
import com.example.tp1.Fragments.Notes;
import com.example.tp1.Fragments.XML;
import com.example.tp1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TP2 extends Fragment {

    private MenuItem solution, xml , java , notes;
    Button celsius, kelvin , fahrenheit;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initBottomNav();

        celsius = view.findViewById(R.id.Celsius);
        kelvin = view.findViewById(R.id.Kelvin);
        fahrenheit = view.findViewById(R.id.Fahrenheit);

        celsius.setOnClickListener(this::calcule);
        kelvin.setOnClickListener(this::calcule);
        fahrenheit.setOnClickListener(this::calcule);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tp2_activity_exo1, container, false);
    }
    private void calcule(View view) {
        Intent i;
        if (view == celsius)
            i = new Intent(getActivity().getApplicationContext(),UniteCelsius.class);
         else if (view == kelvin)
            i = new Intent(getActivity().getApplicationContext(),UniteKalvin.class);
         else if (view == fahrenheit)
            i = new Intent(getActivity().getApplicationContext(),UniteFahrenheit.class);
        else{
            Toast.makeText(getActivity().getApplicationContext(), "error !!", Toast.LENGTH_SHORT).show();
            return;
        }

        startActivity(i);
    }



    //bottom nav
    private void initBottomNav(){
        BottomNavigationView nav = getView().findViewById(R.id.bottom);
        Menu bottom = nav.getMenu();
        solution = bottom.findItem(R.id.Solution);
        xml = bottom.findItem(R.id.XML);
        java = bottom.findItem(R.id.JAVA);
        notes = bottom.findItem(R.id.Notes);

        solution.setOnMenuItemClickListener(this::bottomNavClicked);
        xml.setOnMenuItemClickListener(this::bottomNavClicked);
        java.setOnMenuItemClickListener(this::bottomNavClicked);
        notes.setOnMenuItemClickListener(this::bottomNavClicked);
    }
    private Fragment currentFrag;
    private boolean bottomNavClicked(MenuItem menuItem) {
        menuItem.setChecked(true);
        if (menuItem == solution)
            home(currentFrag);

        else if (menuItem == xml)
            navigate("xml code" , currentFrag = new XML("TP2_part1/xml_tp_part1.html"));

        else if (menuItem == java)
            navigate("Java code" , currentFrag = new JAVA("TP2_part1/code_tp_part1.html"));

        else if (menuItem == notes)
            navigate("notes" , currentFrag = new Notes("TP2_part1_notes"));

        else
            Toast.makeText(getActivity().getApplicationContext(),"Error !! ",Toast.LENGTH_LONG).show();

        return true;
    }
    private void home(Fragment currentFrag) {
        Toast.makeText(getActivity().getApplicationContext(), "Solution Selected !!",Toast.LENGTH_SHORT).show();

        if (currentFrag == null) return;

        //get manager of all fragments associated with this activity
        FragmentManager fragmentManager = getParentFragmentManager();
        //start series of edits on the associated fragments
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(currentFrag);
        fragmentTransaction.commit();
    }
    private void replaceFrag(Fragment fragment){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_vieww,fragment);
        fragmentTransaction.commit();
    }
    private void navigate(String msg , Fragment to){
        Toast.makeText(getActivity().getApplicationContext(),msg + " Selected !!",Toast.LENGTH_SHORT).show();
        replaceFrag(to);
    }
}