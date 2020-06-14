package com.example.Rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DummyPostDetail extends AppCompatActivity {

    TextView textView2, textView3, textView4;
    String string2, string3, string4, string5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_post_detail);

        string2  = getIntent().getExtras().getString("ownername");
        string3 = getIntent().getExtras().getString("ownernumber");
        string4 = getIntent().getExtras().getString("location");
        string5 = getIntent().getExtras().getString("description") ;

        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);

        textView2.setText(string2);
        textView3.setText(string3);
        textView4.setText(string4);
    }
}