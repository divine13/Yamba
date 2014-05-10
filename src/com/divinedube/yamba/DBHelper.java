package com.divinedube.yamba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Divine on 2014/05/10.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = DBHelper.class.getSimpleName();
    DBHelper(Context context){
        super(context, StatusContract.DB_NAME, null, StatusContract.DB_VERSION );

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("create table %s (%s primary key, %s text, %s text, %s int)",
                StatusContract.TABLE, StatusContract.Column.ID,
                StatusContract.Column.USER, StatusContract.Column.MASSAGE, StatusContract.Column.CREATED_AT);

        Log.d(TAG, "onCreate sql " + sql);
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + StatusContract.TABLE  );
        onCreate(db);
    }
}
