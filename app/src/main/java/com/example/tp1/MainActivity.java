package com.example.tp1;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tp1.TP1.TP1;
import com.example.tp1.TP2.TP2;
import com.example.tp1.TP3.TP3;
import com.example.tp1.TP5.TP5;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    MenuItem Home , Tp1 , Tp2 , Tp3 , Tp4 , Tp5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkView();

        Home.setOnMenuItemClickListener(this::navigateTo);
        Tp1.setOnMenuItemClickListener(this::navigateTo);
        Tp2.setOnMenuItemClickListener(this::navigateTo);
        Tp3.setOnMenuItemClickListener(this::navigateTo);
        Tp4.setOnMenuItemClickListener(this::navigateTo);
        Tp5.setOnMenuItemClickListener(this::navigateTo);

        navigateTo(Home);
    }

    private boolean navigateTo(MenuItem menuItem){
        Intent i;
        menuItem.setChecked(true);
        if (menuItem == Home)
            navigate("Home" , new MainFrag());

        else if (menuItem == Tp1)
            navigate("Tp 1" , new TP1());

        else if (menuItem == Tp2)
            navigate("Tp 2" , new TP2());

        else if (menuItem == Tp3)
            navigate("Tp 3" , new TP3());


        else if (menuItem == Tp4) {

            Toast.makeText(this,"Tp 4 ",Toast.LENGTH_SHORT).show();
            return false;


        }
        else if (menuItem == Tp5)
            navigate("Tp 5" , new TP5());

        else
            Toast.makeText(this,"Error !! ",Toast.LENGTH_LONG).show();

        return true;
    }

    private void linkView(){
        ImageButton menu_button = findViewById(R.id.openDrawer);
        NavigationView nav = findViewById(R.id.nav_view);
        Menu menu = nav.getMenu();
        Home = menu.findItem(R.id.Home);
        Tp1 = menu.findItem(R.id.tp1);
        Tp2 = menu.findItem(R.id.tp2);
        Tp3 = menu.findItem(R.id.tp3);
        Tp4 = menu.findItem(R.id.tp4);
        Tp5 = menu.findItem(R.id.tp5);

        menu_button.setOnClickListener(e->{
            DrawerLayout layout = findViewById(R.id.drawer_layout);
            layout.openDrawer(GravityCompat.START,true);
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Back is pressed... Going back to home
                navigateTo(Home);
            }
        });

    }
    private void replaceFrag(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }
    private void navigate(String msg , Fragment to){

        Toast.makeText(this,msg + " Sellected !!",Toast.LENGTH_SHORT).show();

        TextView PageName = findViewById(R.id.PageName);
        PageName.setText(msg);

        replaceFrag(to);

        DrawerLayout layout = findViewById(R.id.drawer_layout);
        layout.closeDrawer(GravityCompat.START,true);
    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult( requestCode, resultCode, data);
        switch (requestCode ){
            case 0:
                if (resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    Bitmap bmp = (Bitmap) bundle.get("data");
                    Bitmap resized = Bitmap.createScaledBitmap(bmp, 100 ,100, true);
                    binding.img.setImageBitmap(resized);
                    break;
                }
            case 1:
                if (resultCode == RESULT_OK){
                    Uri selectedImage = data.getData() ;
                    binding.img.setImageURI (selectedImage);
                    break;
                }

        }
    }
}