package com.ctis487.adapters;

import android.os.Parcel;
import android.os.Parcelable;

public class Quotes implements Parcelable {

    int id;
    String quoteText, quoteAuthor;

    public Quotes(int id, String quoteText, String quoteAuthor) {
        this.id = id;
        this.quoteText = quoteText;
        this.quoteAuthor = quoteAuthor;
    }

    protected Quotes(Parcel in) {
        id = in.readInt();
        quoteText = in.readString();
        quoteAuthor = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(quoteText);
        dest.writeString(quoteAuthor);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Quotes> CREATOR = new Creator<Quotes>() {
        @Override
        public Quotes createFromParcel(Parcel in) {
            return new Quotes(in);
        }

        @Override
        public Quotes[] newArray(int size) {
            return new Quotes[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String quoteAuthor) {
        this.quoteAuthor = quoteAuthor;
    }
}
