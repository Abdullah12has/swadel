package com.ctis487.motherzilla;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ctis487.adapters.Profile;

public class HProfileActivity extends AppCompatActivity {

    Profile p = null;
    TextView tvname, tvspec,tvdesc;
    ImageView img;
    Button book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hprofile);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tvname = findViewById(R.id.tvnamehp);
        tvspec  = findViewById(R.id.specialitytv);
        tvdesc = findViewById(R.id.descriptiontv);
        img = findViewById(R.id.img4);
        Intent receivedIntent = getIntent();
        Bundle b = receivedIntent.getExtras();
        p = b.getParcelable("profile");

        tvname.setText(p.getName());
        img.setImageBitmap(BitmapFactory.decodeByteArray(p.getImage(), 0, p.getImage().length));
        tvspec.setText(p.getSpeciality());
        tvdesc.setText(p.getDescription());

        book = findViewById(R.id.bookButton);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeAndShowDialog("Reservation Complete with "+ p.getName());

            }
        });

    }


    private void makeAndShowDialog(String message) {
        AlertDialog.Builder box = new AlertDialog.Builder(this);

        box.setIcon(R.drawable.logo2); // find a good icon
        box.setTitle("Check Email for Appointment Details");
        box.setMessage(message);
        box.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(HProfileActivity.this, MainActivity.class));
            }
        });
        box.create();
        box.show();
    }
}