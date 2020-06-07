package com.example.Rentit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Delete extends AppCompatActivity {

    EditText owner, code;
    String Owner, Code;

    DatabaseHandler objectDatabaseHandler;
    RecyclerView objectRecyclerView;
    RViewAdapter objectRViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        code = (EditText) findViewById(R.id.matchcodedelete);
        owner = (EditText) findViewById(R.id.ownernamedelete);

    }

    public void deleteProperty(View view) {

        Owner = owner.getText().toString();
        Code = code.getText().toString();
        try {
            objectRecyclerView = findViewById(R.id.RVdetails);
            objectDatabaseHandler = new DatabaseHandler(this);

            objectRViewAdapter = new RViewAdapter(objectDatabaseHandler.deletePost(Owner,Code));
            Intent intent = new Intent(Delete.this,HomeScreen.class);
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
