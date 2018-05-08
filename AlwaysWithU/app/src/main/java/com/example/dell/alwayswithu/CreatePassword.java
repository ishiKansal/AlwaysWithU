package com.example.dell.alwayswithu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePassword extends AppCompatActivity {

    EditText CreatePassword;
    Button UpdatePassword;
    MyDatabase cp_mydb;
    SQLiteDatabase cp_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        cp_mydb=new MyDatabase(this);
        CreatePassword=(EditText)findViewById(R.id.edtCreatePassword1);
        UpdatePassword=(Button)findViewById(R.id.btnUpdatePassword);
    }


    public void updatePassword(View view) {
        String newPassword,whereClause;
        newPassword=CreatePassword.getText().toString().trim();
        Bundle b = getIntent().getExtras();
        String new_email = b.getString("NewEmail");
        cp_db = cp_mydb.getWritableDatabase();
        ContentValues cp_cv=new ContentValues();
        cp_cv.put(cp_mydb.PASSWORD,newPassword);
        whereClause=cp_mydb.EMAIL+"=?";
        String [] whereArgs={new_email};
        int u=cp_db.update(cp_mydb.TABLE_NAME,cp_cv,whereClause,whereArgs);
        Toast.makeText(this, "Password Changed", Toast.LENGTH_SHORT).show();
        Intent i_cc=new Intent(this,MainActivity.class);
        startActivity(i_cc);
        Toast.makeText(this, "Sign In with Your New Password", Toast.LENGTH_SHORT).show();
    }
}
