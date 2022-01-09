package com.ctis487.motherzilla;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ctis487.adapters.Commons;
import com.ctis487.adapters.Quotes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TopFragment extends Fragment {


    private String jsonStr;
    private JSONArray quotes;
    private JSONObject allJSON;

    private ArrayList<Quotes> mArrayList = new ArrayList();
    public static final String TAG_QUOTE = "quote";
    public static final String TAG_TEXT = "quoteText";
    public static final String TAG_AUTHOR = "quoteAuthor";


    TextView quote;
    TopFragmentInterface topFragmentInterfaceListener;
    Quotes randomQuote = null;

    interface TopFragmentInterface {
        public void changeAuthor(int position);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TopFragmentInterface)
            topFragmentInterfaceListener = (TopFragmentInterface) context;
        // here, context is the MainActivity
        // Assign context to TopFragmentInterfaceListener means that MainActivity implements that interface
        // and changeImage() method is definietly implemented in MainActivity
    }


    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quote, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quote = view.findViewById(R.id.tvQuote);
//        int rno = Commons.getRandomno();
//        ArrayList<Quotes> mArrayList = new ArrayList();
//
//        Quotes qq = Commons.getQdata().get(1);
//        quote.setText(qq.getQuoteText().toString());
//

    }




}