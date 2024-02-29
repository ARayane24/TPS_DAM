package com.example.tp1.TP3;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.tp1.Fragments.JAVA;
import com.example.tp1.Fragments.Notes;
import com.example.tp1.Fragments.XML;
import com.example.tp1.MainActivity;
import com.example.tp1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;


public class TP3 extends Fragment {

    private TextView title, text;
    private MenuItem style1 , style2 , style3 ,default_style;
    Menu menu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_tp3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initBottomNav();

        title = view.findViewById(R.id.title);
        text = view.findViewById(R.id.text);

        RadioButton EN = view.findViewById(R.id.EN);
        RadioButton FR = view.findViewById(R.id.FR);
        RadioButton AR = view.findViewById(R.id.AR);

        EN.setOnClickListener(e->{changeLanguage("EN");});
        FR.setOnClickListener(e->{changeLanguage("FR");});
        AR.setOnClickListener(e->{changeLanguage("AR");});

        androidx.appcompat.widget.Toolbar toolbar = ((MainActivity)getActivity())
                .findViewById(R.id.toolbar);
        menu = toolbar.getMenu();

        menu.setGroupVisible(R.id.theme_menu,true);
        default_style = menu.findItem(R.id.default_style);
        style1 = menu.findItem(R.id.style1);
        style2 = menu.findItem(R.id.style2);
        style3 = menu.findItem(R.id.style3);

        TextView option = view.findViewById(R.id.wiki);

        option.setOnClickListener(e->{
            startActivity(new Intent(getContext(),WikiActivity.class));
        });

        default_style.setOnMenuItemClickListener(e->{changeTextsAppearance(android.R.style.TextAppearance);
            return false;
        });

        style1.setOnMenuItemClickListener(e->{changeTextsAppearance(R.style.Style1);
            return false;
        });

        style2.setOnMenuItemClickListener(e->{changeTextsAppearance(R.style.Style2);
            return false;
        });

        style3.setOnMenuItemClickListener(e->{changeTextsAppearance(R.style.Style3);
            return false;
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (menu != null)
            menu.setGroupVisible(R.id.theme_menu,false);
    }

    private void changeLanguage(String ISOLanguageName){

        Locale myLocale = new Locale(ISOLanguageName);//créer un locale(lang est le code de la langue)
        //Locale.setDefault(myLocale);// choisir locale par défaut

        Configuration config = new Configuration();// récupérer la configuration
        config.setLocale(myLocale);   // choisir la configuration locale
        // Mettre à jour la configuration
        Resources ressources =this.getResources();
        ressources.updateConfiguration(config, ressources.getDisplayMetrics()); // Mettre à jour le texte selon la nouvelle option

        //refresh view
        title.setText(R.string.ben_m_hidi);// avec : view est élément de l’interface  //        R.string.text est une ressource string
        text.setText(R.string.desc);// avec : view est élément de l’interface  //        R.string.text est une ressource string

    }

    private void changeTextsAppearance(int style){
        //refresh view
        TextViewCompat.setTextAppearance(title,style);
        TextViewCompat.setTextAppearance(text,style);
    }



    //bottom nav
    private MenuItem solution, xml , java , notes;

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
            navigate("xml code" , currentFrag = new XML("TP3/xml_tp.html"));

        else if (menuItem == java)
            navigate("Java code" , currentFrag = new JAVA("TP3/code_tp.html"));

        else if (menuItem == notes)
            navigate("notes" , currentFrag = new Notes("TP3_notes"));

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
        fragmentTransaction.replace(R.id.main_view,fragment);
        fragmentTransaction.commit();
    }
    private void navigate(String msg , Fragment to){
        Toast.makeText(getActivity().getApplicationContext(),msg + " Selected !!",Toast.LENGTH_SHORT).show();
        replaceFrag(to);
    }
}