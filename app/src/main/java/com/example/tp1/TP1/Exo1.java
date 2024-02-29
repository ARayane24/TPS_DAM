package com.example.tp1.TP1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp1.Fragments.JAVA;
import com.example.tp1.Fragments.Notes;
import com.example.tp1.Fragments.XML;
import com.example.tp1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Exo1 extends AppCompatActivity {

    private MenuItem solution, xml , java , notes;
    private TextView op1 , op2 , result ;
    private Button add  , minus , div , multi ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tp1_activity_exo1);

        initBottomNav();

        try {
           String message = getIntent().getStringExtra("Exo");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            getSupportActionBar().setTitle("TP1 " + message);
        }catch (Exception e){
            Toast.makeText(this, "no intent !!", Toast.LENGTH_SHORT).show();
        }

         op1 = findViewById(R.id.textInputEditText);
         op2 = findViewById(R.id.textInputEditText2);
         result = findViewById(R.id.result);
         add  = findViewById(R.id.add);
         minus = findViewById(R.id.minus);
         div = findViewById(R.id.divide);
         multi = findViewById(R.id.multiply);

        add.setOnClickListener(this::buttonClick);
        minus.setOnClickListener(this::buttonClick);
        div.setOnClickListener(this::buttonClick);
        multi.setOnClickListener(this::buttonClick);
    }

    private void buttonClick(View view) {
        if (op1.getText().toString().equals("") || op2.getText().toString().equals("")) {
            Toast.makeText(this,"error : One input is messing !!",Toast.LENGTH_LONG).show();
            return;
        }

        try {
            double a = Double.parseDouble(op1.getText().toString());
            double b = Double.parseDouble(op2.getText().toString());
            if (view == add){
                result.setText(String.valueOf(a + b));
            } else if (view == minus) {
                result.setText(String.valueOf(a-b));
            } else if (view == multi) {
                result.setText(String.valueOf(a*b));
            }else {
                if (b==0){
                    result.setText("Can't divide by 0");
                }else {
                    result.setText(String.valueOf(a/b));
                }
            }

        }catch (Exception exception){
            Toast.makeText(this,"error : all inputs should be integers or doubles only !!",Toast.LENGTH_LONG).show();
        }
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
            navigate("xml code" , currentFrag = new XML("TP1_exo1/xml_tp_exo1.html"));

        else if (menuItem == java)
            navigate("Java code" , currentFrag = new JAVA("TP1_exo1/code_tp_exo1.html"));

        else if (menuItem == notes)
            navigate("notes" , currentFrag = new Notes("TP1_exo1_notes"));

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