package com.divinedube.yamba;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;
//import com.marakana.android.yamba.clientlib.YambaClientException;
//import winterwell.jtwitter.Twitter;


public class StatusFragment extends Fragment implements  OnClickListener{

    private final static String TAG = "StatusFragment";
    private Button buttonTweet;
    private EditText editStatus;
    private TextView textCount;
    private int defaultTextColor;
    private SharedPreferences prefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(savedInstanceState);
        //setContentView(R.layout.fregment_status);

        View view = inflater.inflate(R.layout.fregment_status, container, false);

        buttonTweet = (Button) view.findViewById(R.id.buttonTweet);
        editStatus = (EditText) view.findViewById(R.id.editStatus);
        textCount = (TextView) view.findViewById(R.id.textCount);

        buttonTweet.setOnClickListener(this);

        defaultTextColor = textCount.getTextColors().getDefaultColor();
        editStatus.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                int count = 140 - editStatus.length();
                textCount.setText(Integer.toString(count));
                textCount.setTextColor(Color.GREEN);

                if (count < 10){
                    textCount.setTextColor(Color.RED);
                }else{
                    textCount.setTextColor(defaultTextColor);
                }

            }
        });

        return  view;
    }



    @Override
    public void onClick(View view) {
        String status = editStatus.getText().toString();
        Log.d(TAG, "Status from app editText is " + status );

        new PostTask().execute(status);

    }

    private final class PostTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
//
//            YambaClient yambaCloud = new YambaClient("student", "password");
            //just wanted to catch any exception that will happen

            try {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String username = prefs.getString("username", "");
                String password = prefs.getString("username", "");

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    getActivity().startActivity(new Intent(getActivity(), StatusActivity.class ));
                    return "please update you username and password";
                }

                YambaClient yambaCloud = new YambaClient(username, password);
                yambaCloud.postStatus(params[0]);

                return "status posted";

            } catch (Exception e) {
                e.printStackTrace();
                return "failed to post status to Yamba services";
            }

        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            //  change the (StatusFragment.this.getActivity())  to refer to which ever class it is in
            Toast.makeText(StatusFragment.this.getActivity(), result, Toast.LENGTH_LONG).show();
        }

    }
}
