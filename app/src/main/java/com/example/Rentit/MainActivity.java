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

    String USERNAME = "";
    String PASSWORD = "";

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

    @Override
    protected void onStart() {
        super.onStart();

        //check if the user is logged in or not
        //if user is logged in then allow him to move ahead
        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
        String UserID = sessionManagement.getSession();
        if (UserID!="null"){
            // user is logged in and so allowed to move ahead
            startActivity(new Intent(MainActivity.this,ChooseViewOrUpload.class));
        }

    }

    public void newRegistration(View view) {
        startActivity(new Intent(this, Registration.class));
    }

    public void validation(View view) {
        progressBar.setVisibility(View.VISIBLE);

        USERNAME = username.getText().toString().trim();
        PASSWORD = password.getText().toString().trim();

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

                                LoginUser loginUser = new LoginUser(USERNAME,PASSWORD);

                                SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
                                sessionManagement.saveSession(loginUser);

                                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this,ChooseViewOrUpload.class);
                                startActivity(intent);


                            } else {
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

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



