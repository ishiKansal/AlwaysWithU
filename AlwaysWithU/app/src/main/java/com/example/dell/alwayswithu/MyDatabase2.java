package com.example.dell.alwayswithu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Dell on 4/18/2018.
 */

public class MyDatabase2 extends SQLiteOpenHelper {
    private Context context;
    //Schema
    public static final String DATABASE_NAME1="sc.db";
    public static final int DATABASE_VERSION1=1;
    public static final String TABLE_NAME1="Safe_Circle";
    public static final String UID1="_id";
    public static final String NAME1="Name";
    public static final String PHONE1="Phone";

    //Queries
    public static final String CREATE_TABLE1="CREATE TABLE "+TABLE_NAME1+" ("+UID1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME1+" TEXT,"+PHONE1+" TEXT)";

    public static final String DROP_TABLE="DROP TABLE IF EXISTS"+TABLE_NAME1;



    public MyDatabase2(Context context) {
        super(context, DATABASE_NAME1, null, DATABASE_VERSION1);
        this.context=context;
        Toast.makeText(context, "Safe Circle Database Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db1) {
        db1.execSQL(CREATE_TABLE1);
        Toast.makeText(context, "Safe Circle Table created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
