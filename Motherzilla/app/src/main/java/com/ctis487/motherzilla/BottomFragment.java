package com.ctis487.motherzilla;

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
    int[] imgIds = new int[]{R.drawable.ankara, R.drawable.istanbul, R.drawable.izmir};

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
        frgBottomImg.setImageResource(imgIds[pos]);
    }

    //STEP 7
    //Implenet changeCityImage(position) method,
    // so that mainActivity can call it to chane the image according to postin value
    //position value is sent from TopActivity to MainActivity
    //then sent from MainActivity to BottomFragment
    void changeCityImage(int position){
        frgBottomImg.setImageResource(imgIds[position]);
    }

}