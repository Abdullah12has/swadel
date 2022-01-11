package com.ctis487.motherzilla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeworkHelpActivity extends AppCompatActivity implements  TopFragment.TopFragmentInterface{



    /*****************************************/
    //STEP 1
    //Spinner Event on TopFragment will change the image on BottomFragment
    //MainActivity will provide communication between them
    //So create objects from Both fragments
    TopFragment topFragment;
    BottomFragment bottomFragment;

    /*****************************************/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_help);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //navigation implementation
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.page_3);


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.page_5:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        overridePendingTransition(0,0);
                        return true;
                    case R.id.page_1:
                        startActivity(new Intent(getApplicationContext(), HelpActivity.class));

                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }

        });
// endnavigation implementation



        /*****************************************/
        //STEP 2
        //Create fragment objects
        topFragment = (TopFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentTop);
        /*****************************************/

        bottomFragment = new BottomFragment();
        Bundle b = new Bundle();
        b.putInt("position",0);

        bottomFragment.setArguments(b);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dynamicFragmentLayout, bottomFragment);
        fragmentTransaction.commit();


    }

    @Override
    public void changeImage(int position) {

        bottomFragment.changeCityImage(position);

    }
}