package com.example.Rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayCode extends AppCompatActivity {

    TextView Code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_code);

        Intent intent =getIntent();
        String Generatedcode = intent.getStringExtra("Code");

        Code =(TextView) findViewById(R.id.code);
        Code.setText(Generatedcode);
    }

    public void viewPosts(View view) {

        Intent jumptoviewpropertydetails = new Intent(getApplicationContext(),HomeScreen.class );
        startActivity(jumptoviewpropertydetails);
    }
}
