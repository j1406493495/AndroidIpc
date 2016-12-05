package com.example.whjin.androidipc.pkgAidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.whjin.androidipc.IBook;
import com.example.whjin.androidipc.IBookManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by win7 on 2016/12/5.
 */

public class BookManagerService extends Service{
    private static final String TAG = "BookManagerService";

    private CopyOnWriteArrayList<IBook> mBookList = new CopyOnWriteArrayList<IBook>();

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
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                        double aDouble, String aString) {
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        mBookList.add(new IBook(1, "Android"));
        mBookList.add(new IBook(2, "Ios"));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
