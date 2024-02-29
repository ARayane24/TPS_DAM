package com.example.tp1.TP1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tp1.Fragments.JAVA;
import com.example.tp1.Fragments.Notes;
import com.example.tp1.Fragments.XML;
import com.example.tp1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;

public class Exo2 extends AppCompatActivity {
    RadioButton op1 , op2;

    private MenuItem solution, xml , java , notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tp1_activity_exo2);

        initBottomNav();

        try {
            String message = getIntent().getStringExtra("Exo");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            getSupportActionBar().setTitle("TP1 " + message);
        }catch (Exception e){
            Toast.makeText(this, "no intent !!", Toast.LENGTH_SHORT).show();
        }

         op1 = findViewById(R.id.option1);
         op2 = findViewById(R.id.option2);

        Button convert = findViewById(R.id.convert);
        convert.setOnClickListener(this::choix);

        TextInputLayout layout = findViewById(R.id.input_layout);
        op1.setOnClickListener(e->{
            layout.setPrefixText("EU");
        });
        op2.setOnClickListener(e->{
            layout.setPrefixText("DZ");
        });

    }

    private void choix(View view) {
        TextView result = findViewById(R.id.result);
        TextView input = findViewById(R.id.input);
        double in;
        try {
            in = Double.parseDouble(input.getText().toString());
        }catch (Exception e){
            in = 0;
        }

        double resultFinal = 0;
        if (op1.isChecked()){
            resultFinal = in * 139.07 ;
        }else if (op2.isChecked()){
            resultFinal = in / 139.07 ;
        }
        result.setText(String.valueOf(resultFinal));

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
            navigate("xml code" , currentFrag = new XML("TP1_exo2/xml_tp_exo2.html"));

        else if (menuItem == java)
            navigate("Java code" , currentFrag = new JAVA("TP1_exo2/code_tp_exo2.html"));

        else if (menuItem == notes)
            navigate("notes" , currentFrag = new Notes("TP1_exo2_notes"));

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
