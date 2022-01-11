package com.ctis487.adapters;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Commons {
    public static Quotes quote;
    public static ArrayList<Quotes> qdata;
    public static ArrayList<Profile> pdata;

    public static Quotes getQuote() {
        return quote;
    }

    public static void setQuote(Quotes quote) {
        Commons.quote = quote;
    }

    public static ArrayList<Quotes> getQdata() {
        return qdata;
    }

    public static void setQdata(ArrayList<Quotes> qdata) {
        Commons.qdata = qdata;
    }

    public static ArrayList<Profile> getPdata() {
        return pdata;
    }

    public static void setPdata(ArrayList<Profile> pdata) {
        Commons.pdata = pdata;
    }
}
