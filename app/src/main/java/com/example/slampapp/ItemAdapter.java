package com.example.slampapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    List<String> devicesName = new ArrayList<String>();
    List<String> devicesId = new ArrayList<String>();

    public ItemAdapter(Context c, List<String> devicesName, List<String> devicesId){
        this.devicesName = devicesName;
        this.devicesId = devicesId;
        this.mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return devicesName.size();
    }

    @Override
    public Object getItem(int position) {
        return devicesName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.my_listview_details, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView IdTextView = (TextView) v.findViewById(R.id.IdTextView);

        String name = devicesName.get(position);
        String id = devicesId.get(position);

        nameTextView.setText(name);
        IdTextView.setText(id);

        return v;
    }
}