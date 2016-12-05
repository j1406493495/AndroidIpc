package com.example.whjin.androidipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by win7 on 2016/12/5.
 */

public class IBook implements Parcelable{
    public int bookId;
    public String bookName;

    public IBook(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.bookId);
        dest.writeString(this.bookName);
    }

    protected IBook(Parcel in) {
        this.bookId = in.readInt();
        this.bookName = in.readString();
    }

    public static final Creator<IBook> CREATOR = new Creator<IBook>() {
        @Override
        public IBook createFromParcel(Parcel source) {
            return new IBook(source);
        }

        @Override
        public IBook[] newArray(int size) {
            return new IBook[size];
        }
    };

    @Override
    public String toString() {
        return "BookId = " + bookId + ", BookName = " + bookName;
    }
}
