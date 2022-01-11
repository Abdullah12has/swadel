package com.ctis487.motherzilla;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.ctis487.adapters.Commons;
import com.ctis487.adapters.MyIntentService;
import com.ctis487.adapters.Quotes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.rpc.Help;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

// the json part
private String jsonStr;
    private JSONArray quotes;
    private JSONObject allJSON;

    private ArrayList<Quotes> mArrayList = new ArrayList();
    public static final String TAG_QUOTE = "quote";
    public static final String TAG_TEXT = "quoteText";
    public static final String TAG_AUTHOR = "quoteAuthor";

    TextView quotetv, authortv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.home_activity);

        quotetv = findViewById(R.id.tvQuote);
        authortv = findViewById(R.id.tvauthor);

        //getting the quotes intent
        IntentFilter filter = new IntentFilter();
        filter.addAction("JSON_PARSE_COMPLETED_ACTION");
        registerReceiver(mbroadcastreciver, filter);

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.page_5);


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.page_1:
                        startActivity(new Intent(getApplicationContext(), HelpActivity.class));

                        overridePendingTransition(0,0);
                        return true;
                    case R.id.page_3:
                        startActivity(new Intent(getApplicationContext(), HomeworkHelpActivity.class));

                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

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
//                    Toast.makeText(MainActivity.this, "User not found ", Toast.LENGTH_SHORT).show();
                }
                else{
//                    Toast.makeText(MainActivity.this, "Login "+user.getEmail(), Toast.LENGTH_SHORT).show();

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
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);

        Toast.makeText(MainActivity.this, "Intent service started ", Toast.LENGTH_SHORT).show();
        try{
            final int random = new Random().nextInt(4999) + 0;
            Quotes quoteTemp = Commons.getQdata().get(random);
            quotetv.setText(quoteTemp.getQuoteText().toString());
            authortv.setText("—"+quoteTemp.getQuoteAuthor().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    private BroadcastReceiver mbroadcastreciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("result");
            if(result.contains("NOT")){
                Toast.makeText(MainActivity.this, "ERROR JSON CANNOT BE PARSED",Toast.LENGTH_SHORT).show();
            }
            else {
                Bundle b = intent.getExtras();
                Commons.setQdata(b.getParcelableArrayList("quotes"));
                Toast.makeText(MainActivity.this, "JSON PARSED",Toast.LENGTH_SHORT).show();
            }
        }
    };



}