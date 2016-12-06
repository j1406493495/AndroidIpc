package com.example.whjin.androidipc.pkgAidl;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.whjin.androidipc.IBook;
import com.example.whjin.androidipc.IBookManager;
import com.example.whjin.androidipc.IOnNewBookListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by win7 on 2016/12/5.
 */

public class BookManagerService extends Service{
    private static final String TAG = "BookManagerService";

    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    private CopyOnWriteArrayList<IBook> mBookList = new CopyOnWriteArrayList<IBook>();
//    private CopyOnWriteArrayList<IOnNewBookListener> mListenerList = new CopyOnWriteArrayList<IOnNewBookListener>();
    private RemoteCallbackList<IOnNewBookListener> mListenerList = new RemoteCallbackList<IOnNewBookListener>();

    private IBookManager.Stub mBinder = new IBookManager.Stub() {
        @Override
        public List<IBook> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(IBook book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookListener listener) throws RemoteException {
            /*
            if (!mListenerList.contains(listener)) {
                mListenerList.add(listener);
            } else {
                Loger.d(TAG, "already exist.");
            }
            Loger.d(TAG, "registerListener, size = " + mListenerList.size());
            */
            mListenerList.register(listener);
        }

        @Override
        public void unregisterListener(IOnNewBookListener listener) throws RemoteException {
            /*
            if (mListenerList.contains(listener)) {
                mListenerList.remove(listener);
                Loger.d(TAG, "unregister listener succeed");
            } else {
                Loger.d(TAG, "not found, can not unregister.");
            }
            Loger.d(TAG, "unregisterListener, current size = " + mListenerList.size());
            */
            mListenerList.unregister(listener);
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                        double aDouble, String aString) {
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        mBookList.add(new IBook(1, "Android"));
        mBookList.add(new IBook(2, "Ios"));

        new Thread(new ServiceWorker()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        int check = checkCallingOrSelfPermission("com.example.whjin.androidipc.permission.ACCESS_BOOK_SERVICE");
        if (check == PackageManager.PERMISSION_DENIED) {
            return null;
        }
        return mBinder;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed.set(true);
        super.onDestroy();
    }

    private void onNewBookArrived(IBook book) throws RemoteException {
        mBookList.add(book);
        /*
        Loger.d(TAG, "onNewBookArrived, notity listeners size = " + mListenerList.size());
        for (int i = 0; i < mListenerList.size(); i++) {
            IOnNewBookListener listener = mListenerList.get(i);
            Loger.d(TAG, "onNewBookArrived, notify listener = " + listener);
            listener.onNewBookArrived(book);
        }
        */
        final int N = mListenerList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IOnNewBookListener l = mListenerList.getBroadcastItem(i);
            if (l != null) {
                try {
                    l.onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListenerList.finishBroadcast();
    }

    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int bookId = mBookList.size() + 1;
                IBook newBook = new IBook(bookId, "new book#" + bookId);

                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
