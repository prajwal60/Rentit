package com.example.Rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseViewOrUpload extends AppCompatActivity {

    String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_view_or_upload);

    }

    public void ViewProperty(View view) {

        Intent jumptoviewpropertydetails = new Intent(getApplicationContext(),HomeScreen.class );
        startActivity(jumptoviewpropertydetails);
    }

    public void UploadProperty(View view) {
        Intent jumptouploadpropertydetails= new Intent(this,UploadPropertyHere.class);
        startActivity(jumptouploadpropertydetails);
    }
}
