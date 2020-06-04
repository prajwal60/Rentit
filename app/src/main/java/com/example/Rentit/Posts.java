package com.example.Rentit;

import android.widget.EditText;
import android.widget.ImageView;

public class Posts {
    public String  name,location,areaCode,street,contact,description;

    public Posts(EditText name, EditText location, EditText area, EditText street, EditText contact, ImageView picture, EditText description) {
    }

    public Posts(String name, String location, String areaCode, String street, String contact, String description) {
        this.name = name;
        this.location = location;
        this.areaCode = areaCode;
        this.street = street;
        this.contact = contact;
        this.description = description;
    }

    public Posts(EditText name, EditText location, EditText area, EditText street, EditText contact, EditText description) {
    }

    public Posts(String name, String location, String area, String street, String contact, String picture, String description) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
