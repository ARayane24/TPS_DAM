package com.example.tp1.TP1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp1.Fragments.JAVA;
import com.example.tp1.Fragments.Notes;
import com.example.tp1.Fragments.XML;
import com.example.tp1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class Exo3_v2 extends AppCompatActivity {

    Button Calculate , Init , exit;
    RadioButton choice1 , choice2 , choice3;
    TextInputEditText Unit , Quant , Pet , Pat;
    RadioGroup gpe;
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tp1_activity_exo3_v2);

        initBottomNav();


        Calculate = findViewById(R.id.cal);
        Init = findViewById(R.id.init);
        exit = findViewById(R.id.Exi);

        choice1 = findViewById(R.id.ch1);
        choice2 = findViewById(R.id.ch2);
        choice3 = findViewById(R.id.ch3);

        gpe= findViewById(R.id.gpe);

        Unit = findViewById(R.id.Unit);
        Quant = findViewById(R.id.Quantity);
        Pet = findViewById(R.id.PET);
        Pat = findViewById(R.id.PAT);


        menu = ((Toolbar)findViewById(R.id.exo3v2_menu)).getMenu();

        //toolbar menu : optionMenu
        MenuItem cal = menu.findItem(R.id.Calculate);
        MenuItem init = menu.findItem(R.id.Initialize);
        MenuItem Exit = menu.findItem(R.id.Exit);

        cal.setOnMenuItemClickListener(this::calculate);
        init.setOnMenuItemClickListener(this::initialize);
        Exit.setOnMenuItemClickListener(this::exit);

        Calculate.setOnClickListener(this::calculate);
        Init.setOnClickListener(this::initialize);
        exit.setOnClickListener(this::exit);



        //context menu
        Pet.setOnCreateContextMenuListener((pr1 , pr2 , pr3)->{
            super.onCreateContextMenu(pr1,pr2,pr3);
            MenuInflater Inflate = getMenuInflater();
            Inflate.inflate(R.menu.exo3v2_menu,pr1);
            MenuItem ccal = pr1.findItem(R.id.Calculate);
            MenuItem iinit = pr1.findItem(R.id.Initialize);
            MenuItem EExit = pr1.findItem(R.id.Exit);

            ccal.setOnMenuItemClickListener(this::calculate);
            iinit.setOnMenuItemClickListener(this::initialize);
            EExit.setOnMenuItemClickListener(this::exit);
        });
        Pat.setOnCreateContextMenuListener((pr1 , pr2 , pr3)->{
            super.onCreateContextMenu(pr1,pr2,pr3);
            MenuInflater Inflate = getMenuInflater();
            Inflate.inflate(R.menu.exo3v2_menu,pr1);
            MenuItem ccal = pr1.findItem(R.id.Calculate);
            MenuItem iinit = pr1.findItem(R.id.Initialize);
            MenuItem EExit = pr1.findItem(R.id.Exit);

            ccal.setOnMenuItemClickListener(this::calculate);
            iinit.setOnMenuItemClickListener(this::initialize);
            EExit.setOnMenuItemClickListener(this::exit);
        });

        //popup menu :
        gpe.setOnCheckedChangeListener((pr1 , pr2)->{
            PopupMenu pop = new PopupMenu(getApplicationContext(),pr1);
            pop.setOnMenuItemClickListener(e->{
                if (e.getItemId() == R.id.Exit)
                    exit(e);
                if (e.getItemId() == R.id.Calculate)
                    calculate(e);
                if (e.getItemId() == R.id.init)
                    initialize(e);
                return true;
            });
            pop.inflate(R.menu.exo3v2_menu);
            pop.show();
        });
    }

    private boolean exit(MenuItem menuItem) {
        exit(exit);
        return true;
    }
    private boolean initialize(MenuItem menuItem) {
        initialize(Init);
        return true;
    }
    private boolean calculate(MenuItem menuItem) {
        calculate(Calculate);
        return true;
    }

    private void initialize(View view) {
        choice1.setChecked(true);
        Unit.setText("");
        Quant.setText("");
        Pet.setText("");
        Pat.setText("");
    }

    private void exit(View view) {
        finish();
    }

    private void calculate(View view) {
        double unit , quantity;
        try {
            unit = Double.parseDouble(String.valueOf( Unit.getText()));
            quantity= Double.parseDouble(String.valueOf( Quant.getText()));
        }catch (Exception exception){
            quantity = unit = 0;
            Snackbar.make(view , "Error in input ",Snackbar.LENGTH_SHORT).show();
        }
        double pet = unit * quantity;

        Pet.setText(String.valueOf( pet ) );
        int radioButton = gpe.getCheckedRadioButtonId();
        double vat =0;
        if (radioButton == R.id.ch2)
            vat = 0.1;
        if (radioButton == R.id.ch3)
            vat = 0.2;

        double pat = pet + vat * pet;
        Pat.setText(String.valueOf(pat));
    }





    //bottom nav
    private MenuItem solution, xml , java , notes;
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
            navigate("xml code" , currentFrag = new XML("TP1_exo3_v2/xml_tp_exo3.html"));

        else if (menuItem == java)
            navigate("Java code" , currentFrag = new JAVA("TP1_exo3_v2/code_tp_exo3.html"));

        else if (menuItem == notes)
            navigate("notes" , currentFrag = new Notes("TP1_exo3_v2_notes"));

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