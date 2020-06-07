package com.example.Rentit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class CRUD_C extends AppCompatActivity {
    private EditText Name;
    private EditText Location;
    private EditText area;
    private EditText Street;
    private EditText Description;
    private EditText Contact;
    private ImageView Picture;
    private Button Upload, choose;
    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;
    private StorageReference mStorageRef;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_r_u_d__c);



        Name =  findViewById(R.id.editTextName);
        Location =  findViewById(R.id.editTextLocation);
        Description =  findViewById(R.id.editTextDescription);
        Contact =  findViewById(R.id.editTextContact);
        Picture =  findViewById(R.id.image_added);
        Upload =  findViewById(R.id.cirUploadBtn);
        choose =  findViewById(R.id.btnChoose);
        mStorageRef = FirebaseStorage.getInstance().getReference("Posts");
        databaseReference = FirebaseDatabase.getInstance().getReference("Posts");

        Upload.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                uploadImage(Name,Location,area,Street,Contact,Picture,Description);
            }
        });
        choose.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                chooseImage();
            }
        });

    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Picture.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(final EditText Name, final EditText Location, final EditText Area, final EditText Street, final EditText Contact, final ImageView Picture, final EditText Description) {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = mStorageRef.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Posts details = new Posts(
                                    Name,
                                    Location,
                                    Area,
                                    Street,
                                    Contact,
                                    Picture,
                                    Description
                            );

                            FirebaseDatabase.getInstance().getReference("Posts").setValue(details).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(CRUD_C.this, "BHOSDIKE", Toast.LENGTH_LONG).show();
//                                    startActivity(new Intent(CRUD_C.this,CRUD_R.class));
                                }

                            });
                            progressDialog.dismiss();
                            Toast.makeText(CRUD_C.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(CRUD_C.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

}
