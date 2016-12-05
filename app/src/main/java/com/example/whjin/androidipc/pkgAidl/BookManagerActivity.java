package com.example.whjin.androidipc.pkgAidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.whjin.androidipc.IBook;
import com.example.whjin.androidipc.IBookManager;
import com.example.whjin.androidipc.Log.Loger;

import java.util.List;

/**
 * Created by win7 on 2016/12/5.
 */

public class BookManagerActivity extends Activity{
    private static final String TAG = "BookManagerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            IBookManager iBookManager = IBookManager.Stub.asInterface(binder);

            try {
                List<IBook> list = iBookManager.getBookList();
                Loger.d(TAG, "query book list, list type:" + list.getClass().getCanonicalName());
                Loger.d(TAG, "query book list:" + list.toString());

                IBook book = new IBook(3, "HTML");
                iBookManager.addBook(book);
                Loger.d(TAG, "add book = " + book);

                List<IBook> newList = iBookManager.getBookList();
                Loger.d(TAG, "query book newList:" + newList.toString());
//                Loger.debugStack(TAG, 10);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        unbindService(mServiceConnection);
        super.onDestroy();
    }
}
