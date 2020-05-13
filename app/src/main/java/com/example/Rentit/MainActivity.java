package com.example.Rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private TextView HelpInPassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);
        HelpInPassword = (TextView) findViewById(R.id.forget_password);
        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

    }


    public void newRegistration(View view) {
        startActivity(new Intent(this, Registration.class));
    }

    public void validation(View view) {
        progressBar.setVisibility(View.VISIBLE);

        String USERNAME = username.getText().toString().trim();
        String PASSWORD = password.getText().toString().trim();

        if ((USERNAME.isEmpty()) && (PASSWORD.isEmpty())) {
            Toast.makeText(this, "Please insert Username & Password", Toast.LENGTH_SHORT).show();
            HelpInPassword.setText(" ");
        } else if (USERNAME.isEmpty()) {
            Toast.makeText(this, "Please insert USERNAME", Toast.LENGTH_SHORT).show();
            HelpInPassword.setText(" ");
        } else if (PASSWORD.isEmpty()) {
            Toast.makeText(this, "Please insert PASSWORD", Toast.LENGTH_SHORT).show();
            HelpInPassword.setText(" ");
        }
        else{
            mAuth.signInWithEmailAndPassword(USERNAME, PASSWORD)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,home.class));

                            } else {
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Login UnSuccess", Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });

        }


    }
    public void loginBtn (View view){
        validation(view);
    }
}



