package com.example.Rentit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class UploadPropertyHere extends AppCompatActivity implements View.OnClickListener {

    EditText PropertyOwnerName, PropertyOwnerContact, PropertyLocation, PropertyDescription;
    ImageView PropertyPhoto;
    Button ChoosePhoto , PostPropertyDetail;

    String SPropertyOwnerName, SPropertyOwnerContact, SPropertyLocation, SPropertyDescription, RoomPostedBy;
    final int Image_Request = 1;
    Uri Image_File_Path;
    Bitmap bitmap;

    DatabaseHandler objectDatabaseHandler;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String Generatedcode , Bookingcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_property_here);

        PropertyOwnerName = (EditText) findViewById(R.id.RoomContactName);
        PropertyOwnerContact = (EditText) findViewById(R.id.RoomContactNumber);
        PropertyPhoto = (ImageView) findViewById(R.id.RoomPhoto);
        PropertyLocation = (EditText) findViewById(R.id.RoomLocation);
        PropertyDescription = (EditText) findViewById(R.id.RoomDescription);
        ChoosePhoto = (Button) findViewById(R.id.choosephoto);
        PostPropertyDetail = (Button) findViewById(R.id.postroom);

        ChoosePhoto.setOnClickListener(this);

        objectDatabaseHandler = new DatabaseHandler(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.choosephoto:
                selectImageFromGallery();
                break;
        }
    }

    private void selectImageFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,Image_Request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && requestCode==Image_Request && data.getData()!=null){
            Image_File_Path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),Image_File_Path);

                PropertyPhoto.setImageBitmap(bitmap);
                PropertyPhoto.setVisibility(View.VISIBLE);
                PropertyOwnerName.setVisibility(View.VISIBLE);
                PropertyOwnerContact.setVisibility(View.VISIBLE);
                PropertyLocation.setVisibility(View.VISIBLE);
                PropertyDescription.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void postProperty(View view) {

        if(!PropertyOwnerName.getText().toString().isEmpty() && !PropertyOwnerContact.getText().toString().isEmpty() && PropertyPhoto.getDrawable()!=null && !PropertyLocation.getText().toString().isEmpty() && !PropertyDescription.getText().toString().isEmpty() && bitmap!=null){

            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();
            RoomPostedBy = firebaseUser.getEmail();

            SPropertyOwnerName = PropertyOwnerName.getText().toString();
            SPropertyOwnerContact = PropertyOwnerContact.getText().toString();
            SPropertyLocation = PropertyLocation.getText().toString();
            SPropertyDescription = PropertyDescription.getText().toString();

            Generatedcode = objectDatabaseHandler.generateAndCheckCode();

            objectDatabaseHandler.storePropertyDetail(new DatabaseModel(SPropertyOwnerName,SPropertyOwnerContact,SPropertyLocation,SPropertyDescription,Generatedcode,RoomPostedBy,bitmap));

            Intent jumptohomescreen = new Intent(this,ShowCode.class);
            jumptohomescreen.putExtra("showcode",Generatedcode);
            startActivity(jumptohomescreen);
        }else {
            Toast.makeText(this, "Please fill all the blanks", Toast.LENGTH_SHORT).show();
        }
    }

}
