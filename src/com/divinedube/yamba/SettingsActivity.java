package com.divinedube.yamba;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Divine on 2014/05/04.
 */
public class SettingsActivity extends Activity {

    protected  void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        if (savedInstance == null){
        SettingFragment settingsFragment =  new SettingFragment();
           getFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, settingsFragment, settingsFragment.getClass().getSimpleName() )
                   .commit();
       }
    }

}
