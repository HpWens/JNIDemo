package com.github.jni_demo;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

public class MainActivity extends CheckPermissionsActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    static {        // 加载动态库
        System.loadLibrary("TestJNI");
    }

    static final String[] PROJECTION = new String[]{ContactsContract.Data._ID,
            ContactsContract.Data.DISPLAY_NAME};

    String mCurFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestJNI testJNI = new TestJNI();
        Log.e("---------------", "************" + testJNI.HelloWord("恭喜你,调用成功!"));


        //一个可以标识loader的唯一的ID。本例中的ID是0。
        //一个可选参数，当loader初始化时提供给它(在本例中是null)。
        //getLoaderManager().initLoader(0, null, this);

    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri baseUri;
        if (mCurFilter != null) {

            baseUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI
                    , Uri.encode(mCurFilter));

        } else {
            baseUri = ContactsContract.Contacts.CONTENT_URI;
        }

        String select = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND ("
                + ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1) AND ("
                + ContactsContract.Contacts.DISPLAY_NAME + " != '' ))";

        CursorLoader loader = new CursorLoader(MainActivity.this, baseUri,
                PROJECTION, select, null, ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null) {
            return;
        }
        while (cursor.moveToNext()) {
            String[] names = cursor.getColumnNames();
            for (String str : names) {
                String contacts = cursor.getString(cursor.getColumnIndex(str));
                Log.e("---------------", "************" + contacts);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {


    }
}
