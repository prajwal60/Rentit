package com.example.Rentit;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(LoginUser loggedinuser){
        //save session of the user who is logged in
        String id = loggedinuser.getUsername();
        editor.putString(SESSION_KEY,id).commit();
    }

    public String getSession(){
        //return user whose session is saved
        return sharedPreferences.getString(SESSION_KEY,"null");
    }

    public void removeSession(){
        //edit the session of the user to null
        editor.putString(SESSION_KEY,"null").commit();
    }

}