package com.divinedube.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;


public class StatusActivity extends Activity{

	@Override
    protected  void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        if (savedInstance == null){
          StatusFragment fragment = new StatusFragment();
            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.status, menu);

        return true;
    }
}
