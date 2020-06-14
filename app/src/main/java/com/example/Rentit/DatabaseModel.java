package com.example.Rentit;

import android.graphics.Bitmap;

public class DatabaseModel {

    String Generated_code,Booking_code,Fullname, Contactnumber, location, Description;

    String Room_Booking_Code, Room_Booked_By;

    Bitmap Propertyimage;

    public DatabaseModel( String fullname, String contactnumber, String location, String description, String generated_code, String booking_code, Bitmap propertyimage) {
        Generated_code = generated_code;
        Booking_code = booking_code;
        Fullname = fullname;
        Contactnumber = contactnumber;
        this.location = location;
        Description = description;
        Propertyimage = propertyimage;
    }

    public DatabaseModel(String room_booking_code, String room_Booked_By) {
        Room_Booking_Code = room_booking_code;
        Room_Booked_By = room_Booked_By;
    }

    public String getRoom_Booking_Code() {
        return Room_Booking_Code;
    }

    public void setRoom_Booking_Code(String room_Booking_Code) {
        Room_Booking_Code = room_Booking_Code;
    }

    public String getRoom_Booked_By() {
        return Room_Booked_By;
    }

    public void setRoom_Booked_By(String room_Booked_By) {
        Room_Booked_By = room_Booked_By;
    }

    public String getGenerated_code() {
        return Generated_code;
    }

    public void setGenerated_code(String generated_code) {
        Generated_code = generated_code;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getContactnumber() {
        return Contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        Contactnumber = contactnumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Bitmap getPropertyimage() {
        return Propertyimage;
    }

    public void setPropertyimage(Bitmap propertyimage) {
        Propertyimage = propertyimage;
    }

    public String getBooking_code() {
        return Booking_code;
    }

    public void setBooking_code(String booking_code) {
        Booking_code = booking_code;
    }
}
