package com.example.Rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdvancedPage extends AppCompatActivity {


    DatabaseHandler objectDatabaseHandler;
    RecyclerView objectRecyclerView;
    RViewAdapter objectRViewAdapter;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    TextView Entered_Code;
    String CodeInstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_page);
    }

    public void updateThisPost(View view) {
        Intent jumptoupdatepage = new Intent(AdvancedPage.this, Update.class);
        startActivity(jumptoupdatepage);
    }

    public void LogOut(View view) {
        SessionManagement sessionManagement =new SessionManagement(AdvancedPage.this);
        sessionManagement.removeSession();
        Intent outofthesystem =new Intent(AdvancedPage.this,MainActivity.class);
        outofthesystem.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        outofthesystem.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(outofthesystem);
    }

    public void moveToHomepage(View view) {
        Intent jumptohomepage = new Intent(AdvancedPage.this, HomeScreen.class);
        startActivity(jumptohomepage);
    }

    public void bookRoom(View view) {

        Intent jumptobookingsection = new Intent(AdvancedPage.this, BookRoom.class);
        startActivity(jumptobookingsection);
    }

    public void deleteThisRoomPost(View view) {
        Intent jumptodeletepost = new Intent(AdvancedPage.this, DeleteProperty.class);
        startActivity(jumptodeletepost);
    }
}
