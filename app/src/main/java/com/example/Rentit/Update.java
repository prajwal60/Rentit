package com.example.Rentit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class Update extends AppCompatActivity {

    TextView Updateownername, Updateownernumber, Updatelocation, Updatedescription;
    ImageView Updatephoto;
    Button Updatechoosephoto;
    String SUpdateownername, SUpdateownernumber, SUpdatelocation, SUpdatedescription;

    DatabaseHandler objectDatabaseHandler;
    RecyclerView objectRecyclerView;
    RViewAdapter objectRViewAdapter;

    final int Image_Req = 1;
    Uri Image_Path;
    Bitmap image_bitmap;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String Man_online_now;
    String Generated_Code_For_Update, Posted_By_For_Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Updateownername = (TextView) findViewById(R.id.updateownername);
        Updateownernumber = (TextView) findViewById(R.id.updateownernumber);
        Updatephoto = (ImageView) findViewById(R.id.updatephoto);
        Updatechoosephoto = (Button) findViewById(R.id.updatechoosephoto);
        Updatelocation = (TextView) findViewById(R.id.updatelocation);
        Updatedescription = (TextView) findViewById(R.id.updatedescription);

        Generated_Code_For_Update = getIntent().getExtras().getString("unique_code_of_room");
        Posted_By_For_Update = getIntent().getExtras().getString("room_posted_by");
    }

    public void choosePhoto(View view) {
        selectImageFromGallery();
    }

    private void selectImageFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,Image_Req);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && requestCode==Image_Req && data.getData()!=null){
            Image_Path = data.getData();
            try {
                image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),Image_Path);
                Updatephoto.setImageBitmap(image_bitmap);

                Updatephoto.setVisibility(View.VISIBLE);
                Updateownername.setVisibility(View.VISIBLE);
                Updateownernumber.setVisibility(View.VISIBLE);
                Updatelocation.setVisibility(View.VISIBLE);
                Updatedescription.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateProperty(View view) {

        SUpdateownername = Updateownername.getText().toString();
        SUpdateownernumber = Updateownernumber.getText().toString();
        SUpdatelocation = Updatelocation.getText().toString();
        SUpdatedescription = Updatedescription.getText().toString();

        if(!SUpdateownername.isEmpty() && !SUpdateownernumber.isEmpty() && Updatephoto.getDrawable()!=null && !SUpdatelocation.isEmpty() && !SUpdatedescription.isEmpty() && image_bitmap!=null){

            try {
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();
                Man_online_now = firebaseUser.getEmail();

                objectRecyclerView = findViewById(R.id.RVdetails);
                objectDatabaseHandler = new DatabaseHandler(this);

                objectRViewAdapter = new RViewAdapter(objectDatabaseHandler.updateChoosedData(SUpdateownername,SUpdateownernumber,SUpdatelocation,SUpdatedescription,Generated_Code_For_Update,Posted_By_For_Update,image_bitmap),this);
                Intent intent = new Intent(Update.this, HomeScreen.class);
                startActivity(intent);

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Please fill all the blanks", Toast.LENGTH_SHORT).show();
        }
    }
}
