package com.example.Rentit;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BookRoom extends AppCompatActivity {


    EditText Entered_Booking_Code;
    String BookingCode_InString;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    private String Room_Booked_By;
    DatabaseHandler objectDatabaseHandler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_room);

        Entered_Booking_Code = (EditText) findViewById(R.id.receivecodeforbookingroom);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        bookThisRoom();


    }

    private void bookThisRoom() {

        BookingCode_InString = Entered_Booking_Code.getText().toString();
        Room_Booked_By = firebaseUser.getEmail();

        if (BookingCode_InString.isEmpty()) {
            Toast.makeText(this, "Please insert the booking code", Toast.LENGTH_SHORT).show();
        }else {

            objectDatabaseHandler.storeRoomBookedRecord(BookingCode_InString, Room_Booked_By);
        }
    }

}