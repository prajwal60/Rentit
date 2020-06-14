package com.example.Rentit;

import android.graphics.Bitmap;

public class DatabaseModel {

    String Generated_code, Room_Posted_By, Fullname, Contactnumber, location, Description;
    Bitmap Propertyimage;

    public DatabaseModel( String fullname, String contactnumber, String location, String description, String generated_code, String room_posted_by, Bitmap propertyimage) {
        Generated_code = generated_code;
        Room_Posted_By = room_posted_by;
        Fullname = fullname;
        Contactnumber = contactnumber;
        this.location = location;
        Description = description;
        Propertyimage = propertyimage;
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

    public String getRoom_Posted_By() {
        return Room_Posted_By;
    }

    public void setRoom_Posted_By(String room_Posted_By) {
        Room_Posted_By = room_Posted_By;
    }


    String Received_Owner_Name, Received_Unique_Code_Of_Post, Received_Post_Posted_By, Received_Post_Booked_By;

    public DatabaseModel(String received_Owner_Name, String received_Unique_Code_Of_Post, String received_Post_Posted_By, String received_Post_Booked_By) {
        Received_Owner_Name = received_Owner_Name;
        Received_Unique_Code_Of_Post = received_Unique_Code_Of_Post;
        Received_Post_Posted_By = received_Post_Posted_By;
        Received_Post_Booked_By = received_Post_Booked_By;
    }

    public String getReceived_Owner_Name() {
        return Received_Owner_Name;
    }

    public void setReceived_Owner_Name(String received_Owner_Name) {
        Received_Owner_Name = received_Owner_Name;
    }

    public String getReceived_Unique_Code_Of_Post() {
        return Received_Unique_Code_Of_Post;
    }

    public void setReceived_Unique_Code_Of_Post(String received_Unique_Code_Of_Post) {
        Received_Unique_Code_Of_Post = received_Unique_Code_Of_Post;
    }

    public String getReceived_Post_Posted_By() {
        return Received_Post_Posted_By;
    }

    public void setReceived_Post_Posted_By(String received_Post_Posted_By) {
        Received_Post_Posted_By = received_Post_Posted_By;
    }

    public String getReceived_Post_Booked_By() {
        return Received_Post_Booked_By;
    }

    public void setReceived_Post_Booked_By(String received_Post_Booked_By) {
        Received_Post_Booked_By = received_Post_Booked_By;
    }

}
