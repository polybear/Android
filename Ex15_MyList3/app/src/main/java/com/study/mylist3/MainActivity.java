package com.study.mylist3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    String[] names = { "홍길동", "강감찬", "을지문덕", "양만춘", "유관순" };
    String[] ages = { "20", "25", "30", "35", "15" };
    int[] images = { R.mipmap.face1, R.mipmap.face2, R.mipmap.face3, R.mipmap.face1, R.mipmap.face2 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final MyAdapter adapter = new MyAdapter();

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(getApplicationContext(), "selected : " + names[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() { return names.length; }

        @Override
        public Object getItem(int position) { return names[position]; }

        @Override
        public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        /*    LinearLayout layout = new LinearLayout(getApplicationContext());
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

            return layout; */

            SingerItemView view = new SingerItemView(getApplicationContext());
            view.setName(names[position]);
            view.setAge(ages[position]);
            view.setImage(images[position]);

            return view;
        }
    }
}
