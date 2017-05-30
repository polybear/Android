package com.study.mygrid;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        GridView gridView = (GridView)findViewById(R.id.gridView);

        final MyAdapter adapter = new MyAdapter();
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                int row = position / 2;
                int column = position % 2;

                Log.d("study", "Row index : " + row + "\nColumn index : " + column);
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        String[] names = { "홍길동", "강감찬", "을지문덕", "양만춘", "유관순" };
        String[] ages = { "20", "25", "30", "35", "15" };

        @Override
        public int getCount() { return names.length; }

        @Override
        public Object getItem(int position) { return names[position]; }

        @Override
        public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            TextView view1 = new TextView(getApplicationContext());
            view1.setText(names[position]);
            view1.setTextSize(40.0f);
            view1.setTextColor(Color.BLUE);

            layout.addView(view1);

            TextView view2 = new TextView(getApplicationContext());
            view2.setText(ages[position]);
            view2.setTextSize(40.0f);
            view2.setTextColor(Color.RED);

            layout.addView(view2);

            return layout;
        }
    }
}
