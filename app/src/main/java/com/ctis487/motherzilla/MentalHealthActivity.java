package com.ctis487.motherzilla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.ctis487.adapters.MentalHealthActivityAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MentalHealthActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MentalHealthItems> dataholder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.mental_health_temporal);



        recyclerView = (RecyclerView)findViewById(R.id.rvMentalHealthTemporal);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Cursor cursor = new DatabaseHelper(this).readAllData();


        while(cursor.moveToNext()){
            MentalHealthItems obj = new MentalHealthItems(cursor.getInt(1), cursor.getInt(2), cursor.getString(2), cursor.getString(4), cursor.getString(5), cursor.getBlob(6));

            dataholder.add(obj);
        }

        MentalHealthActivityAdapter adapter = new MentalHealthActivityAdapter(dataholder);
        recyclerView.setAdapter(adapter);


    }
}