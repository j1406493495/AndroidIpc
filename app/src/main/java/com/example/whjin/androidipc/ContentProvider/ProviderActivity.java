package com.example.whjin.androidipc.ContentProvider;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.example.whjin.androidipc.R;

/**
 * Created by win7 on 2016/12/7.
 */

public class ProviderActivity extends Activity{
    private static final String TAG = "ProviderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider_activity);

        Uri uri = Uri.parse("content://com.example.whjin.androidipc.ContentProvider.provider");
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
        getContentResolver().query(uri, null, null, null, null);
    }
}
