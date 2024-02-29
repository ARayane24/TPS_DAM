package com.example.tp1.TP1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp1.Fragments.JAVA;
import com.example.tp1.Fragments.Notes;
import com.example.tp1.Fragments.XML;
import com.example.tp1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.regex.Pattern;

public class Exo4 extends AppCompatActivity {
    private MenuItem solution, xml , java , notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tp1_activity_exo4);

        initBottomNav();

        try {
            String message = getIntent().getStringExtra("Exo");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            getSupportActionBar().setTitle("TP1 " + message);
        }catch (Exception e){
            Toast.makeText(this, "no intent !!", Toast.LENGTH_SHORT).show();
        }

        Button conjuguer = findViewById(R.id.conjuguer);
        conjuguer.setOnClickListener(e->{
               String verb = ((EditText) findViewById(R.id.verb)).getText().toString();
                TextView result = findViewById(R.id.resultConjug);

               if (isNullOrEmpty(verb) || !estPremierGroup(verb)){
                   Toast.makeText(this,"n'est pas verb de 1er group !!" , Toast.LENGTH_LONG).show();
                   if (!isNullOrEmpty(result.getText().toString()))
                       result.setText("");
               }else {
                   Toast.makeText(this,"est un verb de 1er group !!" , Toast.LENGTH_LONG).show();
                   result.setText(conjugeVerb( verb.replace(" ","") ));
               }
        });
    }

    private boolean estPremierGroup(String verb){
        return Pattern.matches(".*er",verb);
    }

    private String conjugeVerb(String verb){
        char arr[] = new char[verb.length()-2];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = verb.charAt(i);
        }

        String verbRoute = String.valueOf(arr);

        String result = "Je \t" + verbRoute+"e" + '\n' +
                "Tu \t" +verbRoute+"es" + '\n' +
                "Il/elle \t" +verbRoute+"e" + '\n' +
                "Nous \t" +verbRoute+"ons" + '\n' +
                "Vous \t" +verbRoute+"ez" + '\n' +
                "Ils/elles \t" +verbRoute+"ent" + '\n' ;

        return result;
    }

    private boolean isNullOrEmpty(String string){
        return string == null || string.isEmpty();
    }


    //bottom nav
    private void initBottomNav(){
        BottomNavigationView nav = findViewById(R.id.bottom);
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
            navigate("xml code" , currentFrag = new XML("TP1_exo4/xml_tp_exo4.html"));

        else if (menuItem == java)
            navigate("Java code" , currentFrag = new JAVA("TP1_exo4/code_tp_exo4.html"));

        else if (menuItem == notes)
            navigate("notes" , currentFrag = new Notes("TP1_exo4_notes"));

        else
            Toast.makeText(this,"Error !! ",Toast.LENGTH_LONG).show();

        return true;
    }
    private void home(Fragment currentFrag) {
        Toast.makeText(this, "Solution Selected !!",Toast.LENGTH_SHORT).show();

        if (currentFrag == null) return;

        //get manager of all fragments associated with this activity
        FragmentManager fragmentManager = getSupportFragmentManager();
        //start series of edits on the associated fragments
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(currentFrag);
        fragmentTransaction.commit();
    }
    private void replaceFrag(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_view,fragment);
        fragmentTransaction.commit();
    }
    private void navigate(String msg , Fragment to){
        Toast.makeText(this,msg + " Selected !!",Toast.LENGTH_SHORT).show();
        replaceFrag(to);
    }
}