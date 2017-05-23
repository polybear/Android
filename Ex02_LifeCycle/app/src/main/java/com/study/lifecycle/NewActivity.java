package com.study.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        Toast.makeText(getApplicationContext(), "onCreate() 호출됨", Toast.LENGTH_SHORT).show();
        Log.d("study", "onCreate() 호출됨");

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("BackData", "강감찬");
                setResult(10, intent);

                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(getApplicationContext(), "onStart() 호출됨", Toast.LENGTH_SHORT).show();
        Log.d("study", "onStart() 호출됨");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(), "onResume() 호출됨", Toast.LENGTH_SHORT).show();
        Log.d("study", "onResume() 호출됨");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(getApplicationContext(), "onPause() 호출됨", Toast.LENGTH_SHORT).show();
        Log.d("study", "onPause() 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(getApplicationContext(), "onStop() 호출됨", Toast.LENGTH_SHORT).show();
        Log.d("study", "onStop() 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(getApplicationContext(), "onDestroy() 호출됨", Toast.LENGTH_SHORT).show();
        Log.d("study", "onDestroy() 호출됨");
    }
}
