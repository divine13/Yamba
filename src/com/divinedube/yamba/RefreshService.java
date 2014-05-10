package com.divinedube.yamba;

import android.app.IntentService;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

import java.util.List;

/**
 * Created by Divine on 2014/05/09.
 */
public class RefreshService extends IntentService {

     static final String TAG = "RefreshService";

    public RefreshService() { super(TAG); }


//    @Override
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }

    //    }
//        return START_STICKY;
//        Log.d(TAG, "onStarted");
//        super.onStartCommand(intent, flags, startId);
//   public int onStartCommand(Intent intent, int flags, int startId ){


    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "onCreated");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String username = pref.getString("username", "");
        final String password = pref.getString("password", "");

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "please update your username and your password",
                    Toast.LENGTH_LONG).show();
            Log.d(TAG,"please update your username and your password" );
            return;
        }

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        YambaClient cloud = new YambaClient(username, password);
        Log.d(TAG, "spawned a new thread to get time-line ");

        try {
            List<YambaClient.Status> timeLine = cloud.getTimeline(20);
            for(YambaClient.Status status :  timeLine){
                values.clear();
                values.put(StatusContract.Column.ID, status.getId());
                values.put(StatusContract.Column.USER, status.getUser());
                values.put(StatusContract.Column.MASSAGE, status.getMessage());
                values.put(StatusContract.Column.CREATED_AT, status.getCreatedAt().getTime());
                Log.d(TAG, String.format("%s: %s", status.getUser(), status.getMessage()));

                db.insertWithOnConflict(StatusContract.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE );
            }
            }catch (YambaClientException yce){
            Log.e(TAG, "failed to fetch your timeline dude, check your internet");
            yce.printStackTrace();
        }

        return;
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
