package com.study.mylist6;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class SingerAdapter extends BaseAdapter {

    Context context;
    ArrayList<SingerItem> items = new ArrayList<SingerItem>();

    public SingerAdapter(Context context) { this.context = context; }

    public void addItem(SingerItem item) {
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SingerItemView view = null;

        if (convertView == null)
        {
            view = new SingerItemView(context);
        }
        else
        {
            view = (SingerItemView)convertView;
        }

        final SingerItem item = items.get(position);
        view.setName(item.getName());
        view.setTel(item.getTelnum());
        view.setImage(item.getResId());

        Button button = (Button)view.findViewById(R.id.button);
        button.setFocusable(false);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "tel: " + item.getTelnum();
                Log.d("study", str);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str));
                context.startActivity(intent);
            }
        });

        return view;
    }
}