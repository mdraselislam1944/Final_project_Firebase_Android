package com.example.firebase;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String[] HomeActivity;
    private LayoutInflater inflater;

    CustomAdapter (Context context,String[] HomeActivity){
        this.HomeActivity=HomeActivity;
        this.context=context;
    }
    @Override
    public int getCount() {
        return HomeActivity.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sample_view_home, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.textViewId);

        textView.setText(HomeActivity[position]);

        return convertView;
    }
}

