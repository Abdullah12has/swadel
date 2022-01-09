package com.ctis487.motherzilla;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ctis487.adapters.Commons;
import com.ctis487.adapters.Quotes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    Handler handler;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    DatabaseHelper databaseHelper;


// the json part
private String jsonStr;
    private JSONArray quotes;
    private JSONObject allJSON;

    private ArrayList<Quotes> mArrayList = new ArrayList();
    public static final String TAG_QUOTE = "quote";
    public static final String TAG_TEXT = "quoteText";
    public static final String TAG_AUTHOR = "quoteAuthor";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.home_activity);

        //navigation implementation
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.page_5);


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.page_1:
                        startActivity(new Intent(getApplicationContext(), MentalHealthActivity.class));

                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }

        });
// endnavigation implementation

        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){
                    System.out.println("User logged in");
                }
                else{
                    System.out.println("User not logged in");
                }
            }
        };


    }


    public void logOut(MenuItem item) {
        mAuth.signOut();
//        Toast.makeText(MainActivity.this, "Logged out ", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        mAuth.addAuthStateListener(mAuthListener);
                if(user == null) {
//                    startActivity(new Intent(MainActivity.this, LoginActivity.class));  //activate this for redirect to login commented because not working on emulaotor
                    Toast.makeText(MainActivity.this, "User not found ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Login "+user.getEmail(), Toast.LENGTH_SHORT).show();
                }
    }

    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);

        }
    }



//    Getting data from json

    public void showw(View view) {

        // Reading the JSON file from the assets folder and storing it in a String
        jsonStr = loadFileFromAssets("quotes.json");
        Log.d("TAG", "\n" + jsonStr);
        // Call to AsyncTask
        new GetQuotesJSON().execute();
    }


    private class GetQuotesJSON extends AsyncTask<Void, Void, Void> {

        // Main job should be done here
        @Override
        protected Void doInBackground(Void... params) {
            //Log.d("TAG", "HERE.....");

            if (jsonStr != null) {
                try {
                    allJSON = new JSONObject(jsonStr);

                    quotes = allJSON.getJSONArray(TAG_QUOTE);

                    // looping through all students
                    for (int i = 0; i < quotes.length(); i++) {

                        JSONObject s = quotes.getJSONObject(i);

                        int id = i;
                        String text = s.getString(TAG_TEXT);
                        String author = s.getString(TAG_AUTHOR);


                        Quotes quo = new Quotes(id, text, author);

                        mArrayList.add(quo);
                    }
                } catch (JSONException ee) {
                    ee.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // What do you want to do after doInBackground() finishes
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Dismiss the progress dialog

            if (mArrayList != null) {
                Commons.setQdata(mArrayList);
                int min = 0;
                int max = mArrayList.size();

                int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
                Commons.setRandomno(55);

            }
            else{
                Toast.makeText(MainActivity.this, "JSON data not found", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private String loadFileFromAssets(String fileName) {
        String file = null;
        try {
            InputStream is = getBaseContext().getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            file = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return file;
    }

}