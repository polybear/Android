package com.study.mytouch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final Button button = (Button)findViewById(R.id.button);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int pointerCount = event.getPointerCount();

                float curX1 = 0.0f;
                float curY1 = 0.0f;
                float curX2 = 0.0f;
                float curY2 = 0.0f;

                if (pointerCount == 1) {
                    curX1 = event.getX(0);
                    curY1 = event.getY(0);
                }

                else if (pointerCount == 2) {
                    curX1 = event.getX(0);
                    curY1 = event.getY(0);
                    curX2 = event.getX(1);
                    curY2 = event.getY(1);
                }

                int action = event.getAction();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("study", "Touch down..." + curX1 + " : " + curY1 + " / " + curX2 + " : " + curY2);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("study", "Touch move..." + curX1 + " : " + curY1 + " / " + curX2 + " : " + curY2);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("study", "Touch up..." + curX1 + " : " + curY1 + " / " + curX2 + " : " + curY2);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
