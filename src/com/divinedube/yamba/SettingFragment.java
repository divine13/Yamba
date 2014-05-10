package com.divinedube.yamba;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

/**
 * Created by Divine on 2014/05/04.
 */
public class SettingFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {

    private SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onStop(){
        super.onStop();
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }
}
