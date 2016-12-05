package com.example.whjin.androidipc.Log;

import android.util.Log;

/**
 * Created by win7 on 2016/12/5.
 */

public class Loger {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static final int LEVEL = VERBOSE;

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }

    public static void debugStack(String tag, int trace) {
        StackTraceElement[] s = new Exception().getStackTrace();
        int count = s.length;
        Log.d(tag, "*************************************************** count = " + count);
        for (int i = 0; i < trace; i++) {
            if (i < count) {
                Log.d(tag, "Stack[" + i + "].getClassName= " + s[i].getClassName());
                Log.d(tag, "Stack[" + i + "].getMethodName= " + s[i].getMethodName());
                Log.d(tag, "Stack[" + i + "].getLineNumber= " + s[i].getLineNumber());
                Log.d(tag, "===========================================");
            } else {
                break;
            }
        }
    }
}