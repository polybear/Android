package com.study.mythread2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView textView;
    Handler handler;
    ProgressBar progressBar;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        handler = new Handler();

        textView = (TextView)findViewById(R.id.textView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setEnabled(false);

                RequestThread thread = new RequestThread();
                thread.start();
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "쓰레드 테스트", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class RequestThread extends Thread {
        public void run() {
            for (int i = 0; i < 100; i++) {
                final int index = i;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("Request Thread... " + index);

                        progressBar.incrementProgressBy(1);
                    }
                });

                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
