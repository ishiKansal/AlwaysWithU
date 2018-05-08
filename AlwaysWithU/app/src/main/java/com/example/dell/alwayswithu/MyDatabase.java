package com.example.dell.alwayswithu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Dell on 4/18/2018.
 */

public class MyDatabase extends SQLiteOpenHelper {
    private Context context;
    //Schema
    public static final String DATABASE_NAME="c.db";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="Contacts";
    public static final String UID="_id";
    public static final String NAME="Name";
    public static final String EMAIL="Email";
    public static final String PHONE="Phone";
    public static final String PASSWORD="Password";
    public static final String DOB="DOB";
    public static final String STATE="State";
    public static final String CITY="City3";

    //Queries
    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" TEXT,"+EMAIL+" TEXT,"+PHONE+" TEXT,"+DOB+" TEXT,"+STATE+" TEXT,"+CITY+" TEXT,"+PASSWORD+" TEXT)";

    public static final String DROP_TABLE="DROP TABLE IF EXISTS"+TABLE_NAME;


    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
        Toast.makeText(context, "Database Created", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Toast.makeText(context, "Table created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
