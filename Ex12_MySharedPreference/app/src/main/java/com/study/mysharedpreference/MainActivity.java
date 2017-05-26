package com.study.mysharedpreference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        SharedPreferences pref = getSharedPreferences("login", Activity.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        final EditText tvID = (EditText)findViewById(R.id.etID);
        final EditText tvPW = (EditText)findViewById(R.id.etPW);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sid = tvID.getText().toString();
                String spw = tvPW.getText().toString();

                editor.putString("id", sid);
                editor.putString("pw", spw);
                editor.commit();
            }
        });

        String id = pref.getString("id", "");
        String pw = pref.getString("pw", "");

        Log.d("study", "id : " + id);

        tvID.setText(id);
        tvID.setSelection(tvID.length());
        tvPW.setText(pw);
    }
}
