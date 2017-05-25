package com.study.myimageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends Activity {

    ScrollView scrollView1;

    ImageView imageView1;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);

        imageView1.setImageResource(R.mipmap.background);
        imageView1.setImageResource(0);

        imageView1.invalidate();
        imageView2.invalidate();

        scrollView1 = (ScrollView)findViewById(R.id.scrollView1);
        scrollView1.setVerticalScrollBarEnabled(true);
        scrollView1.setHorizontalScrollBarEnabled(true);

        Button btnDown = (Button)findViewById(R.id.button1);
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageDown();
            }
        });

        Button btnUp = (Button)findViewById(R.id.button2);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageUp();
            }
        });
    }

    private void imageDown() {
        imageView1.setImageResource(0);
        imageView2.setImageResource(R.mipmap.background);

        imageView1.invalidate();
        imageView2.invalidate();
    }

    private void imageUp() {
        imageView1.setImageResource(R.mipmap.background);
        imageView2.setImageResource(0);

        imageView1.invalidate();
        imageView2.invalidate();
    }
}
