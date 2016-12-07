package com.example.whjin.androidipc.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.whjin.androidipc.Log.Loger;

/**
 * Created by win7 on 2016/12/7.
 */

public class BookProvider extends ContentProvider{
    private static final String TAG = "BookProvider";

    @Override
    public boolean onCreate() {
        Loger.d(TAG, "onCreate, current thread = " + Thread.currentThread().getName());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Loger.d(TAG, "query, current thread = " + Thread.currentThread().getName());
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Loger.d(TAG, "getType");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Loger.d(TAG, "insert");
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        Loger.d(TAG, "delete");
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String s, String[] strings) {
        Loger.d(TAG, "update");
        return 0;
    }
}
