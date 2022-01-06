package com.ctis487.motherzilla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private GestureDetector mDetector;
    FirebaseAuth mAuth;

    EditText etr1, etr2, etr3, etr4;
    Button registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        RegisterActivity.MyGestureDetector mGestureD = new RegisterActivity.MyGestureDetector();
        mDetector = new GestureDetector(this,mGestureD);


        etr1 = findViewById(R.id.etr1);
        etr2 = findViewById(R.id.etl1);
        etr3 = findViewById(R.id.etr3);
        etr4 = findViewById(R.id.etl2);
        registerbtn = findViewById(R.id.register_button);

        mAuth = FirebaseAuth.getInstance();

        registerbtn.setOnClickListener( view -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });



    }


    private void createUser(){
        String email = etr3.getText().toString();
        String password = etr4.getText().toString();
        if(TextUtils.isEmpty(email)){
            etr3.setError("Email can't be empty");
            etr3.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            etr4.setError("Password can't be empty");
            etr4.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Error"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    class MyGestureDetector extends  GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            return super.onDoubleTapEvent(e);
        }

    }

}