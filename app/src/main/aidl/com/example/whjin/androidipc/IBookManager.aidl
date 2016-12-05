// IBookManager.aidl
package com.example.whjin.androidipc;

import com.example.whjin.androidipc.IBook;
// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    List<IBook> getBookList();
    void addBook(in IBook book);
}
