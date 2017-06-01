package com.study.mythread1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView textView;
    ProgressHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //3: 추가한 클래스를 이용한 핸들러 변수 만들기
        handler = new ProgressHandler();

        textView = (TextView)findViewById(R.id.textView);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestThread thread = new RequestThread();
                thread.start();
            }
        });
    }

    class RequestThread extends Thread {
        public void run() {
            for (int i = 0; i < 20; i++) {
                //Log.d("study", "Request Thread... " + i);

                //1 : 쓰레드에서 메인 쓰레더의 객체로 접근은 불가능
                //textView.setText("Request Thread... " + i);

                //4 : 핸들러에 전달한 메세지 작성성
               Message msg = handler.obtainMessage();

                Bundle bundle = new Bundle();
                bundle.putString("data", "Request Thread... " + (i + 1));
                msg.setData(bundle);

                handler.sendMessage(msg);

                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //2 : 핸들러 클래스 생성
    class ProgressHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            //5 : 핸들러에 메세지가 전달되면 원하는 동작 처리
            Bundle bundle = msg.getData();
            String data =bundle.getString("data");

            textView.setText(data);
        }
    }
}
