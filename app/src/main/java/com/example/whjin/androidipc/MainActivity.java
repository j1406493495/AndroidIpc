package com.example.whjin.androidipc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.whjin.androidipc.pkgAidl.BookManagerActivity;

/**
 * Created by win7 on 2016/12/3.
 */

public class MainActivity extends Activity implements View.OnClickListener{
    private static final String TAG = "MainActivity";

    private Button mAidlBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mAidlBtn = (Button) findViewById(R.id.aidl_btn);
        mAidlBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aidl_btn:
                Intent intent = new Intent(MainActivity.this, BookManagerActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
