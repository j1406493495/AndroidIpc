package com.example.whjin.androidipc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.whjin.androidipc.Messenger.MessengerActivity;
import com.example.whjin.androidipc.pkgAidl.BookManagerActivity;

/**
 * Created by win7 on 2016/12/3.
 */

public class MainActivity extends Activity implements View.OnClickListener{
    private static final String TAG = "MainActivity";

    private Button mAidlBtn;
    private Button mMessengerBtn;
    private Button mProviderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mAidlBtn = (Button) findViewById(R.id.aidl_btn);
        mMessengerBtn = (Button) findViewById(R.id.messenger_btn);
        mProviderBtn = (Button) findViewById(R.id.provider_btn);
        mAidlBtn.setOnClickListener(this);
        mMessengerBtn.setOnClickListener(this);
        mProviderBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aidl_btn:
                Intent aidlIntent = new Intent(MainActivity.this, BookManagerActivity.class);
                startActivity(aidlIntent);
                break;
            case R.id.messenger_btn:
                Intent messengerIntent = new Intent(this, MessengerActivity.class);
                startActivity(messengerIntent);
                break;
            case R.id.provider_btn:
                Intent providerIntent = new Intent(this, BookManagerActivity.class);
                startActivity(providerIntent);
                break;
            default:
                break;
        }
    }
}
