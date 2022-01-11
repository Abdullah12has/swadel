package com.ctis487.motherzilla;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class BottomFragment extends Fragment {

    ImageView frgBottomImg;
    int[] imgIds = new int[]{ R.drawable.breathing,R.drawable.motherdiet, R.drawable.babydiet, R.drawable.diaper};
    String[] youtubeLinks = new String[]{"https://www.youtube.com/watch?v=LCqxcKNImYw",
                                        "https://www.youtube.com/watch?v=R9mYuYH1t8M",
                                        "https://www.youtube.com/watch?v=Zu-0WnjRzA8",
                                        "https://www.youtube.com/watch?v=QF6_xBd8oMY"};
    public BottomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frgBottomImg = view.findViewById(R.id.frgBottomImg);

        int pos = getArguments().getInt("position");
        System.out.println(pos);
        System.out.println("SHIUABSIUAJBSIJKN");
        frgBottomImg.setImageResource(imgIds[pos]);
        frgBottomImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(youtubeLinks[pos]));
                startActivity(viewIntent);
            }
        });
    }

    //STEP 7
    //Implenet changeCityImage(position) method,
    // so that mainActivity can call it to chane the image according to postin value
    //position value is sent from TopActivity to MainActivity
    //then sent from MainActivity to BottomFragment
    void changeCityImage(int position){
        frgBottomImg.setImageResource(imgIds[position]);
        frgBottomImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(youtubeLinks[position]));
                startActivity(viewIntent);
            }
        });
    }

}