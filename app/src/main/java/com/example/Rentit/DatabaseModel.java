package com.example.Rentit;

import android.graphics.Bitmap;

public class DatabaseModel {

    String Generated_code,Fullname, Contactnumber, location, Description;

    Bitmap Propertyimage;

    public DatabaseModel(String fullname, String contactnumber, String location, String description, String generated_code, Bitmap propertyimage) {
        Generated_code = generated_code;
        Fullname = fullname;
        Contactnumber = contactnumber;
        this.location = location;
        Description = description;
        Propertyimage = propertyimage;
    }

    public DatabaseModel(String fullname, String contactnumber,String description, String generated_code) {
        Generated_code = generated_code;
        Fullname = fullname;
        Contactnumber = contactnumber;
        Description = description;
    }

    public DatabaseModel(String generated_code, String fullname) {
        Generated_code = generated_code;
        Fullname = fullname;
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
}
