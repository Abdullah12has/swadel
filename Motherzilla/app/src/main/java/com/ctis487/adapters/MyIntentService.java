package com.ctis487.adapters;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MyIntentService extends IntentService {

    ArrayList<Quotes> quotesList;
    private JSONObject jsonObject;
    private JSONArray qquotes;

    private String jsonStr;

    public static final String TAG_QUOTES = "quotes";
    public static final String TAG_QUOTES_TEXT = "quoteText";
    public static final String TAG_AUTHOR = "quoteAuthor";


    public MyIntentService() {
        super("MyIntentService");
//        Log.d("Service","Service Started LLLLLLLLLLLLLLLLLLLL");

        setIntentRedelivery(true);
    }


    private String loadFileFromAssets(String fileName) {

        String fileContent = null;
        try {
            InputStream is = getBaseContext().getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            fileContent = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return fileContent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

//        System.out.println("111111111111111111111111");

        quotesList = new ArrayList<>();
        jsonStr = loadFileFromAssets("quotes.json");

        if (jsonStr != null) {
//            System.out.println("22222222222222222222222");
            try {
                jsonObject = new JSONObject(jsonStr);
                qquotes = jsonObject.getJSONArray(TAG_QUOTES);
                // looping through all quotes
                for (int i = 0; i < qquotes.length(); i++) {

                    JSONObject jsonObj = qquotes.getJSONObject(i);

                    String quote = jsonObj.getString(TAG_QUOTES_TEXT);
                    String author = jsonObj.getString(TAG_AUTHOR);
                    Quotes q = new Quotes(i, quote, author);
                    quotesList.add(q);
                }

                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction("JSON");
                if(quotesList.size()>0) {
//                    System.out.println("3333333333333333333333333333333");
                    Commons.qdata = quotesList;
                    Bundle b=new Bundle();
                    b.putParcelableArrayList("quotes", quotesList);
                    broadcastIntent.putExtras(b);

                    broadcastIntent.putExtra("result","FOUND");
                }
                else{
                    broadcastIntent.putExtra("result","NOTFOUND");
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                }
                sendBroadcast(broadcastIntent);

            } catch (JSONException ee) {
                ee.printStackTrace();
            }
        }
    }
}
