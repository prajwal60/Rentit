package com.example.Rentit;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class DatabaseHandler extends SQLiteOpenHelper {

    private Context context;
    private static String DATABASE_NAME = "Coursework";
    private static int DATABASE_VERSION = 1;

    private static String CREATE_TABLE_SQL_QUERY = "create table property_detail (Owner_name TEXT"
            + " , Owner_contact TEXT , property_photo BLOB , property_location TEXT, property_description TEXT, unique_code TEXT)";

    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] ImageInByte;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_SQL_QUERY);
            Toast.makeText(context, "Table created Successfully", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void storePropertyDetail(DatabaseModel objectDatabaseModel) {
        try {
            SQLiteDatabase objectSQLiteDatabase = getWritableDatabase();
            Bitmap ImageToStore = objectDatabaseModel.getPropertyimage();

            objectByteArrayOutputStream = new ByteArrayOutputStream();
            ImageToStore.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);

            ImageInByte = objectByteArrayOutputStream.toByteArray();
            ContentValues objectContentValues = new ContentValues();

            objectContentValues.put("Owner_name", objectDatabaseModel.getFullname());
            objectContentValues.put("Owner_contact", objectDatabaseModel.getContactnumber());
            objectContentValues.put("property_photo", ImageInByte);
            objectContentValues.put("property_location", objectDatabaseModel.getLocation());
            objectContentValues.put("property_description", objectDatabaseModel.getDescription());
            objectContentValues.put("unique_code", objectDatabaseModel.getGenerated_code());

            Long checkifthedatainserted = objectSQLiteDatabase.insert("property_detail", null, objectContentValues);

            if (checkifthedatainserted != -1) {
                Toast.makeText(context, "Property detail posted Successfully", Toast.LENGTH_SHORT).show();
                objectSQLiteDatabase.close();
            } else {
                Toast.makeText(context, "Failed to post", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public ArrayList<DatabaseModel> extractDataAndImages() {
        try {
            SQLiteDatabase objectSQLiteDatabase = getReadableDatabase();
            ArrayList<DatabaseModel> objectDatabaseModellist = new ArrayList<>();

            Cursor objectCursor = objectSQLiteDatabase.rawQuery("select * from property_detail", null);
            if (objectCursor.getCount() != 0) {

                while (objectCursor.moveToNext()) {
                    String Name_To_Display = objectCursor.getString(0);
                    String Number_To_Display = objectCursor.getString(1);
                    byte[] Image_In_Byte = objectCursor.getBlob(2);
                    String Location_To_Display = objectCursor.getString(3);
                    String Description_To_Display = objectCursor.getString(4);
                    String Unique_code = objectCursor.getString(5);

                    Bitmap Image_To_Display = BitmapFactory.decodeByteArray(Image_In_Byte, 0, Image_In_Byte.length);
                    objectDatabaseModellist.add(new DatabaseModel(Name_To_Display, Number_To_Display, Location_To_Display, Description_To_Display, Unique_code, Image_To_Display));

                }
                return objectDatabaseModellist;
            } else {
                String Name_To_Display = "Null";
                String Number_To_Display = "Null";
                String Location_To_Display = "Null";
                String Description_To_Display = "Null";
                String Unique_code = "Null";

                objectDatabaseModellist.add(new DatabaseModel(Name_To_Display, Number_To_Display, Location_To_Display, Description_To_Display, Unique_code, null));
                return objectDatabaseModellist;
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public String generateAndCheckCode() {

        Random r = new Random();
        int min = 10000;
        int max = 100000;
        int res = r.nextInt(max - min) + min;
        String str = "";

        for (int i = 0; i <= 5; i++) {
            //checks that the last two digits fall under the ASCII number
            int remainder = res % 100;
            if ((remainder >= 63) && (remainder <= 90) || (remainder >= 35) && (remainder <= 38) || (remainder >= 97) && (remainder <= 122)) {

                str = Character.toString((char) remainder) + str;
            } else {

                Random ren = new Random();
                int renmin = 49;
                int renmax = 57;
                int renres = ren.nextInt(renmax - renmin) + renmin;
                remainder = (renres - remainder) + remainder;
                str = Character.toString((char) remainder) + str;
            }
            res = (res - (remainder % 10)) / 10;
        }

        try {
            SQLiteDatabase objectSQLiteDatabase = getReadableDatabase();
            ArrayList<DatabaseModel> objectDatabaseModellist = new ArrayList<>();

            Cursor checkcode = objectSQLiteDatabase.rawQuery("select * from property_detail where unique_code ='" + str + "'", null);
            if (checkcode.getCount() == 0) {
                return str;
            } else {
                generateAndCheckCode();
            }

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
        return str;
    }

    public ArrayList<DatabaseModel> searchByCode(String code) {


        ArrayList<DatabaseModel> objectDatabaseModellist = new ArrayList<>();
        try {
            SQLiteDatabase objectSQLiteDatabase = getReadableDatabase();

            Cursor searchcursor = objectSQLiteDatabase.rawQuery("select * from property_detail where unique_code ='" + code + "'", null);
//            or Owner_name LIKE '%"+code+"%'
            if (searchcursor.getCount() != 0) {

                while (searchcursor.moveToNext()) {
                    String Name_To_Display = searchcursor.getString(0);
                    String Number_To_Display = searchcursor.getString(1);
                    byte[] Image_In_Byte = searchcursor.getBlob(2);
                    String Location_To_Display = searchcursor.getString(3);
                    String Description_To_Display = searchcursor.getString(4);
                    String Unique_code = searchcursor.getString(5);

                    Bitmap Image_To_Display = BitmapFactory.decodeByteArray(Image_In_Byte, 0, Image_In_Byte.length);
                    objectDatabaseModellist.add(new DatabaseModel(Name_To_Display, Number_To_Display, Location_To_Display, Description_To_Display, Unique_code, Image_To_Display));

                }
                return objectDatabaseModellist;
            } else {
                Toast.makeText(context, "No search found", Toast.LENGTH_LONG).show();
                Cursor objectCursor = objectSQLiteDatabase.rawQuery("select * from property_detail", null);

            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
        return objectDatabaseModellist;
    }

    public ArrayList<DatabaseModel> updateChoosedData(String sUpdateownername, String sUpdateownernumber, String sUpdatelocation, String sUpdatedescription, Bitmap image_bitmap, String sConfirmcode) {

        ArrayList<DatabaseModel> objectDatabaseModellist = new ArrayList<>();
        try {
            SQLiteDatabase ObjectSQLiteDatabase = getWritableDatabase();

            Bitmap ImageToUpdate = image_bitmap;
            objectByteArrayOutputStream = new ByteArrayOutputStream();
            ImageToUpdate.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);
            byte[] UpdateImageInByte = objectByteArrayOutputStream.toByteArray();

            ContentValues objectContentValues = new ContentValues();
            objectContentValues.put("Owner_name", sUpdateownername);
            objectContentValues.put("Owner_contact", sUpdateownernumber);
            objectContentValues.put("property_photo", UpdateImageInByte);
            objectContentValues.put("property_location", sUpdatelocation);
            objectContentValues.put("property_description", sUpdatedescription);

            String[] whereArgs = {sConfirmcode};

            int count = ObjectSQLiteDatabase.update("property_detail", objectContentValues, "unique_code = ?", whereArgs);

            if (count <= 0) {
                Toast.makeText(context, "Update UnSuccess", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Post updated Successfully", Toast.LENGTH_SHORT).show();

                Cursor searchcursor = ObjectSQLiteDatabase.rawQuery("select * from property_detail", null);
                if (searchcursor.getCount() != 0) {

                    while (searchcursor.moveToNext()) {
                        String Name_To_Display = searchcursor.getString(0);
                        String Number_To_Display = searchcursor.getString(1);
                        byte[] Image_In_Byte = searchcursor.getBlob(2);
                        String Location_To_Display = searchcursor.getString(3);
                        String Description_To_Display = searchcursor.getString(4);
                        String Unique_code = searchcursor.getString(5);

                        Bitmap Image_To_Display = BitmapFactory.decodeByteArray(Image_In_Byte, 0, Image_In_Byte.length);
                        objectDatabaseModellist.add(new DatabaseModel(Name_To_Display, Number_To_Display, Location_To_Display, Description_To_Display, Unique_code, Image_To_Display));
                    }
                } else {
                    String Name_To_Display = "Null";
                    String Number_To_Display = "Null";
                    String Location_To_Display = "Null";
                    String Description_To_Display = "Null";
                    String Unique_code = "Null";

                    objectDatabaseModellist.add(new DatabaseModel(Name_To_Display, Number_To_Display, Location_To_Display, Description_To_Display, Unique_code, null));
                    return objectDatabaseModellist;
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return objectDatabaseModellist;
    }

    public ArrayList<DatabaseModel> deletePost(String name_of_owner, String code_entered) {

        ArrayList<DatabaseModel> objectDatabaseModellist = new ArrayList<>();
        try {
            SQLiteDatabase objectSQLiteDatabase = getWritableDatabase();
            String[] whereArgs = {name_of_owner, code_entered};

            int count = objectSQLiteDatabase.delete("property_detail", "Owner_name = ? and unique_code = ?", whereArgs);
            if (count <= 0) {
                Toast.makeText(context, "Delete UnSuccess", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();

                Cursor searchcursor = objectSQLiteDatabase.rawQuery("select * from property_detail", null);
                if (searchcursor.getCount() != 0) {

                    while (searchcursor.moveToNext()) {
                        String Name_To_Display = searchcursor.getString(0);
                        String Number_To_Display = searchcursor.getString(1);
                        byte[] Image_In_Byte = searchcursor.getBlob(2);
                        String Location_To_Display = searchcursor.getString(3);
                        String Description_To_Display = searchcursor.getString(4);
                        String Unique_code = searchcursor.getString(5);

                        Bitmap Image_To_Display = BitmapFactory.decodeByteArray(Image_In_Byte, 0, Image_In_Byte.length);
                        objectDatabaseModellist.add(new DatabaseModel(Name_To_Display, Number_To_Display, Location_To_Display, Description_To_Display, Unique_code, Image_To_Display));
                    }
                } else {
                    String Name_To_Display = "Null";
                    String Number_To_Display = "Null";
                    String Location_To_Display = "Null";
                    String Description_To_Display = "Null";
                    String Unique_code = "Null";

                    objectDatabaseModellist.add(new DatabaseModel(Name_To_Display, Number_To_Display, Location_To_Display, Description_To_Display, Unique_code, null));
                    return objectDatabaseModellist;
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return objectDatabaseModellist;
    }
}
