package com.example.Rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class BookedData extends AppCompatActivity {

    String Room_owner_online_now;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    ListView listview;
    DatabaseHandler objecDatabaseHandler = new DatabaseHandler(this);
    DatabaseModel objectDatabaseModel;
    ArrayList<DatabaseModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_data);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        Room_owner_online_now = firebaseUser.getEmail();

        listview = (ListView) findViewById(R.id.listview);
        
        presentDataInListView();

    }

    private void presentDataInListView() {
        arrayList = objecDatabaseHandler.passPostCodeAndBookerName(Room_owner_online_now);
        ListviewAdapter objectListviewAdapter = new ListviewAdapter(this,arrayList);
        listview.setAdapter(objectListviewAdapter);
        objectListviewAdapter.notifyDataSetChanged();
    }

}