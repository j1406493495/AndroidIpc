package com.example.whjin.androidipc.pkgAidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;

import com.example.whjin.androidipc.IBook;
import com.example.whjin.androidipc.IBookManager;
import com.example.whjin.androidipc.IOnNewBookListener;
import com.example.whjin.androidipc.Log.Loger;

import java.util.List;

/**
 * Created by win7 on 2016/12/5.
 */

public class BookManagerActivity extends Activity{
    private static final String TAG = "BookManagerActivity";
    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;

    private IBookManager mRemoteBookManager;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Loger.d(TAG, "receive new book = " + msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

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
                mRemoteBookManager = iBookManager;
                List<IBook> list = iBookManager.getBookList();
                Loger.d(TAG, "query book list, list type:" + list.getClass().getCanonicalName());
                Loger.d(TAG, "query book list:" + list.toString());

                IBook book = new IBook(3, "HTML");
                iBookManager.addBook(book);
                Loger.d(TAG, "add book = " + book);

                List<IBook> newList = iBookManager.getBookList();
                Loger.d(TAG, "query book newList:" + newList.toString());
                iBookManager.registerListener(mOnNewBookListener);
//                Loger.debugStack(TAG, 10);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IOnNewBookListener mOnNewBookListener = new IOnNewBookListener.Stub() {
        @Override
        public void onNewBookArrived(IBook newBook) throws RemoteException {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook).sendToTarget();
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                        double aDouble, String aString) {
        }
    };

    @Override
    protected void onDestroy() {
        if (mRemoteBookManager!=null && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                Loger.d(TAG, "unregister listener = " + mOnNewBookListener);
                mRemoteBookManager.unregisterListener(mOnNewBookListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mServiceConnection);
        super.onDestroy();
    }
}
