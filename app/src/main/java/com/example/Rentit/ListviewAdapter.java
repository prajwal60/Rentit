package com.example.Rentit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListviewAdapter extends BaseAdapter {

    Context  context;
    ArrayList<DatabaseModel> arrayList;

    public ListviewAdapter(Context context, ArrayList<DatabaseModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.my_booked_rooms,null);
        TextView Post_Code = (TextView) convertView.findViewById(R.id.Post_Code);
        TextView Booked_By = (TextView) convertView.findViewById(R.id.booked_by);

        DatabaseModel databaseModel = arrayList.get(position);

        Post_Code.setText(databaseModel.getReceived_Unique_Code_Of_Post());
        Booked_By.setText(databaseModel.getReceived_Post_Booked_By());

        return convertView;
    }
}
