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

import java.io.IOException;

public class Update extends AppCompatActivity {

    TextView Updateownername, Updateownernumber, Updatelocation, Updatedescription, Confirmcode;
    ImageView Updatephoto;
    Button Updatechoosephoto;
    String SUpdateownername, SUpdateownernumber, SUpdatelocation, SUpdatedescription, SConfirmcode;

    DatabaseHandler objectDatabaseHandler;
    RecyclerView objectRecyclerView;
    RViewAdapter objectRViewAdapter;

    final int Image_Req = 1;
    Uri Image_Path;
    Bitmap image_bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Updateownernumber = (TextView) findViewById(R.id.updateownernumber);
        Updatedescription = (TextView) findViewById(R.id.updatedescription);
        Updateownername = (TextView) findViewById(R.id.updateownername);
        Updatelocation = (TextView) findViewById(R.id.updatelocation);
        Confirmcode = (TextView) findViewById(R.id.matchcode);
        Updatephoto = (ImageView) findViewById(R.id.updatephoto);
        Updatechoosephoto = (Button) findViewById(R.id.updatechoosephoto);
    }
//
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//
//            case R.id.updatechoosephoto:
//                selectImageFromGallery();
//                break;
//        }
//    }

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
        SUpdateownernumber = Updateownernumber.getText().toString();
        SUpdatedescription = Updatedescription.getText().toString();
        SUpdateownername = Updateownername.getText().toString();
        SUpdatelocation = Updatelocation.getText().toString();
        SConfirmcode = Confirmcode.getText().toString();

        if(!SUpdateownername.isEmpty() && !SUpdateownernumber.isEmpty() && Updatephoto.getDrawable()!=null && !SUpdatelocation.isEmpty() && !SUpdatedescription.isEmpty() && image_bitmap!=null){

            try {
                objectRecyclerView = findViewById(R.id.RVdetails);
                objectDatabaseHandler = new DatabaseHandler(this);

                objectRViewAdapter = new RViewAdapter(objectDatabaseHandler.updateChoosedData(SUpdateownername,SUpdateownernumber,SUpdatelocation,SUpdatedescription,image_bitmap,SConfirmcode),this);
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
