package com.study.mylist6;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {

    TextView textView;
    Button button;
    ImageView imageView;

    public SingerItemView(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item_view, this, true);

        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void setName(String name) { textView.setText(name); }

    public void setTel(String telnum) { button.setText(telnum); }

    public void setImage(int imgNum) { imageView.setImageResource(imgNum); }
}
