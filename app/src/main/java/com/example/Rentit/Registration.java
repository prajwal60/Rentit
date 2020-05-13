package com.example.Rentit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class Registration extends AppCompatActivity {
    private EditText U_name,U_email,U_phone,U_password,U_location,U_repeat_password;
    private RadioGroup button;
    private RadioButton radioButtonMale,radioButtonFemale;
    private Button register_btn;
    private ProgressBar progressBar;


    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    String User_gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        U_name = (EditText)findViewById(R.id.editTextName);
        U_email = (EditText)findViewById(R.id.editTextEmail);
        U_phone = (EditText)findViewById(R.id.editTextMobile);
        button = (RadioGroup) findViewById(R.id.btn_register);
        U_password = (EditText)findViewById(R.id.editTextPassword);
        U_location = (EditText)findViewById(R.id.editTextLocation);
        U_repeat_password =(EditText)findViewById(R.id.editTextRepeatPassword);
        register_btn = (Button)findViewById(R.id.cirRegisterButton);
        radioButtonMale = (RadioButton)findViewById(R.id.radio_male);
        radioButtonFemale = (RadioButton)findViewById(R.id.radio_female);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();


    }


    public void oldlogin(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }


    public void register(View view) {
        final String User_name = U_name.getText().toString().trim();
        final String email = U_email.getText().toString().trim();
        final String password = U_password.getText().toString().trim();

        final String User_location = U_location.getText().toString().trim();
        final String User_phone = U_phone.getText().toString().trim();
        String repeat_pass = U_repeat_password.getText().toString().trim();

        if (radioButtonMale.isChecked()){
            User_gender = "Male";
        }
        else if (radioButtonFemale.isChecked()){
            User_gender = "Female";
        }
        else if (radioButtonFemale.isChecked()){
            User_gender = "Others";
        }
        if (TextUtils.isEmpty(email)){
            Toast.makeText(Registration.this, "Email Address is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(User_name)){
            Toast.makeText(Registration.this, "Please Enter Your FullName", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(Registration.this, "Password is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(User_gender)){
            Toast.makeText(Registration.this, "Please select Your Gender", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(User_location)){
            Toast.makeText(Registration.this, "Enter Your Location", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(User_phone)){
            Toast.makeText(Registration.this, "Enter your Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(repeat_pass)) {
            Toast.makeText(Registration.this, "Please Enter same Password here", Toast.LENGTH_SHORT).show();
            return;
        }


        if(repeat_pass.equals(password)){
            progressBar.setVisibility(View.VISIBLE);
            createAccount(User_name,email,User_phone,User_gender,User_location,password);
        }
        else{
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        }
    }

    private void createAccount(final String User_name, final String email, final String User_phone, final String User_gender, final String User_location, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            User details = new User(
                                    User_name,
                                    email,
                                    User_phone,
                                    User_gender,
                                    User_location,
                                    password
                            );
                            FirebaseDatabase.getInstance().getReference("Users").
                                    child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(details).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Registration.this,MainActivity.class));
                                }

                            });
                            progressBar.setVisibility(View.GONE);
                        } else {
                            // If sign in fails, display a message to the user.
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Registration.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}