package com.example.Rentit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AllFunctionSingleData extends AppCompatActivity {


    DatabaseHandler objectDatabaseHandler = new DatabaseHandler(this);
    RecyclerView objectRecyclerView;
    RViewAdapter objectRViewAdapter;

    String All_Owner_Name, All_Generated_Code, All_Room_Posted_By;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String Man_Online_now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_function_single_data);

        All_Owner_Name = getIntent().getExtras().getString("owner_name");
        All_Generated_Code = getIntent().getExtras().getString("unique_code_of_room");
        All_Room_Posted_By = getIntent().getExtras().getString("room_posted_by");

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        Man_Online_now = firebaseUser.getEmail();

    }

    public void updateThisRoomDetail(View view) {
        if(All_Room_Posted_By.equals(Man_Online_now)){

            Intent havepermissionforupdate = new Intent(AllFunctionSingleData.this,Update.class);
            havepermissionforupdate.putExtra("room_posted_by",All_Room_Posted_By);
            havepermissionforupdate.putExtra("unique_code_of_room",All_Generated_Code);
            startActivity(havepermissionforupdate);

        }else {
            Toast.makeText(this,"You don't have permission to update this post",Toast.LENGTH_LONG).show();
        }
    }

    public void deleteThisRoomDetail(View view) {
        if(All_Room_Posted_By.equals(Man_Online_now)){

            try {
                objectRecyclerView = findViewById(R.id.RVdetails);
                objectDatabaseHandler = new DatabaseHandler(this);

                objectRViewAdapter = new RViewAdapter(objectDatabaseHandler.deletePost(All_Room_Posted_By,All_Generated_Code),this);
                Intent intent = new Intent(AllFunctionSingleData.this,HomeScreen.class);
                startActivity(intent);

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"You don't have permission to update this post",Toast.LENGTH_LONG).show();
        }
    }

    public void bookThisRoomDetail(View view) {
        if(All_Room_Posted_By.equals(Man_Online_now)){
            Toast.makeText(this,"This does not make any sense.\nYou posted this post",Toast.LENGTH_LONG).show();
        }else {

            objectDatabaseHandler.storeRoomBookedRecord(new DatabaseModel(All_Owner_Name, All_Generated_Code, All_Room_Posted_By, Man_Online_now));
            Intent havepermissionforbook = new Intent(AllFunctionSingleData.this,HomeScreen.class);
            startActivity(havepermissionforbook);
        }
    }
}