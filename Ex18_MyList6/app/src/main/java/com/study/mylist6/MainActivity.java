package com.study.mylist6;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final SingerAdapter adapter = new SingerAdapter(this);

        SingerItem item1 = new SingerItem("홍길동", "010-1111-1234", R.mipmap.face1);
        adapter.addItem(item1);

        SingerItem item2 = new SingerItem("이순신", "010-2222-5678", R.mipmap.face2);
        adapter.addItem(item2);

        SingerItem item3 = new SingerItem("김유신", "010-3333-2468", R.mipmap.face3);
        adapter.addItem(item3);

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                SingerItem item = (SingerItem)adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "selected : " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

//        Button button = (Button)findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String inputName = editText1.getText().toString();
//                String inputAge = editText2.getText().toString();
//
//                SingerItem item = new SingerItem(inputName, inputAge, R.mipmap.face1);
//                adapter.addItem(item);
//                adapter.notifyDataSetChanged();
//            }
//        });
    }
}