package com.ctis487.motherzilla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.ctis487.adapters.Commons;
import com.ctis487.adapters.DatabaseHelper;
import com.ctis487.adapters.MyRecyclerViewAdapter;
import com.ctis487.adapters.Profile;
import com.ctis487.adapters.ProfileTable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity {


    DatabaseHelper dbHelper;
    MyRecyclerViewAdapter adapter;
    Intent intent;
    RecyclerView profileRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_help);




//navigation implementation
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.page_1);


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.page_5:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        overridePendingTransition(0,0);
                        return true;
                    case R.id.page_3:
                        startActivity(new Intent(getApplicationContext(), BabyHelpActivity.class));

                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }

        });
// endnavigation implementation

//        database

        try {
            String fileToDatabase = "/data/data/" + getPackageName() + "/databases/"+DatabaseHelper.DATABASE_NAME;
            File file = new File(fileToDatabase);
            File pathToDatabasesFolder = new File("/data/data/" + getPackageName() + "/databases/");
            if (!file.exists()) {
                pathToDatabasesFolder.mkdirs();

                CopyDB( getResources().getAssets().open(DatabaseHelper.DATABASE_NAME),
                        new FileOutputStream(fileToDatabase));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        database end

        profileRecycler = findViewById(R.id.rv1);
        LinearLayoutManager layoutManager  = new LinearLayoutManager(this);
        intent = new Intent(this, HProfileActivity.class);

        dbHelper = new DatabaseHelper(this);

        Commons.setPdata( (ArrayList<Profile>) ProfileTable.getAll(dbHelper));


        intent = new Intent(this, HProfileActivity.class);
        adapter = new MyRecyclerViewAdapter(this, intent);
        profileRecycler.setAdapter(adapter);
        profileRecycler.setLayoutManager(layoutManager);

    }




    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
        // Copy 1K bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        Log.d("BURDA", "BURDA2");
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
            Log.d("BURDA", "BURDA3");
        }
        inputStream.close();
        outputStream.close();
    }
}