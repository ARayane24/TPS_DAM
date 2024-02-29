package com.example.tp1.TP1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp1.Fragments.JAVA;
import com.example.tp1.Fragments.Notes;
import com.example.tp1.Fragments.XML;
import com.example.tp1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Exo3 extends AppCompatActivity {
    TextView a, b, c;
    private MenuItem solution, xml , java , notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tp1_activity_exo3);
        initBottomNav();
        try {
            String message = getIntent().getStringExtra("Exo");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            getSupportActionBar().setTitle("TP1 " + message);

        }catch (Exception e){
            Toast.makeText(this, "no intent !!", Toast.LENGTH_SHORT).show();
        }

        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);

        Button reset = findViewById(R.id.reset);
        Button cal = findViewById(R.id.solve);
        TextView solution = findViewById(R.id.solution);
        reset.setOnClickListener(e->{a.setText("");b.setText("");c.setText("");});
        cal.setOnClickListener(e->{
            double aa,bb,cc;
            try {
                aa = Double.parseDouble(a.getText().toString());
                bb = Double.parseDouble(b.getText().toString());
                cc = Double.parseDouble(c.getText().toString());
            }catch (Exception ex){
                return;
            }

            double del = bb*bb - 4 *aa*cc;
            if (del>0){
                solution.setText("X1 : " + String.valueOf((-bb+Math.sqrt(del))/(2*aa)) + " X2 : " + String.valueOf((-bb-Math.sqrt(del))/(2*aa)));
            } else if (del == 0) {
                solution.setText("X1,x2 : " + String.valueOf((-bb+Math.sqrt(del))/(2*aa)));
            } else {
                solution.setText("No solution !!");
            }
        });
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
            navigate("xml code" , currentFrag = new XML("TP1_exo3/xml_tp_exo3.html"));

        else if (menuItem == java)
            navigate("Java code" , currentFrag = new JAVA("TP1_exo3/code_tp_exo3.html"));

        else if (menuItem == notes)
            navigate("notes" , currentFrag = new Notes("TP1_exo3_notes"));

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