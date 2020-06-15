package com.example.Rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowCode extends AppCompatActivity {

    TextView showcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_code);

        showcode = (TextView) findViewById(R.id.showcode);
        String Showcode = getIntent().getExtras().getString("showcode");
        showcode.setText(Showcode);
    }

    public void okay(View view) {
        Intent movetoshowposts = new Intent(ShowCode.this,HomeScreen.class);
        startActivity(movetoshowposts);
    }
}