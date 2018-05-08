package com.example.dell.alwayswithu;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MySafeCircle extends AppCompatActivity {

    private static final int RESULT_PICK_CONTACT1 = 1;
    private static final int RESULT_PICK_CONTACT2 = 2;
    private static final int RESULT_PICK_CONTACT3 = 3;
    private static final int RESULT_PICK_CONTACT4 = 4;
    int row_check=1;
    EditText name1,name2,name3,name4,contact1,contact2,contact3,contact4;
    MyDatabase2 mydb2;
    SQLiteDatabase db2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_safe_circle);

        name1 = (EditText) findViewById(R.id.etSC_name1);
        name2 = (EditText) findViewById(R.id.etSC_name2);
        name3 = (EditText) findViewById(R.id.etSC_name3);
        name4 = (EditText) findViewById(R.id.etSC_name4);
        contact1 = (EditText) findViewById(R.id.etc1);
        contact2 = (EditText) findViewById(R.id.etc2);
        contact3 = (EditText) findViewById(R.id.etc3);
        contact4 = (EditText) findViewById(R.id.etc4);
        mydb2=new MyDatabase2(this);
    }

    public void call_contact(View view) {
        if (view.getId()==R.id.btncontact1) {
            Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT1);
        }

        if (view.getId()==R.id.btncontact2){
            Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT2);
        }
        if (view.getId()==R.id.btncontact3){
            Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT3);
        }
        if (view.getId()==R.id.btncontact4){
            Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT4);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT1:
                    contactPicked1(data);
                    break;
                case RESULT_PICK_CONTACT2:
                    contactPicked2(data);
                    break;
                case RESULT_PICK_CONTACT3:
                    contactPicked3(data);
                    break;
                case RESULT_PICK_CONTACT4:
                    contactPicked4(data);
                    break;
            }
        }
    }
    private void contactPicked1(Intent data) {
        Cursor cursor;
        try {
            String phoneNo;
            String name;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();

            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);

            name1.setText(name);
            contact1.setText(phoneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void contactPicked2(Intent data) {
        Cursor cursor;
        try {
            String phoneNo;
            String name;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();

            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);

            name2.setText(name);
            contact2.setText(phoneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void contactPicked3(Intent data) {
        Cursor cursor;
        try {
            String phoneNo;
            String name;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();

            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);

            name3.setText(name);
            contact3.setText(phoneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void contactPicked4(Intent data) {
        Cursor cursor;
        try {
            String phoneNo;
            String name;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();

            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);

            name4.setText(name);
            contact4.setText(phoneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void save_contacts(View view) {
        String n1,n2,n3,n4,cn1,cn2,cn3,cn4;
        ContentValues cv1,cv2,cv3,cv4;
        n1=name1.getText().toString().trim();
        n2=name2.getText().toString().trim();
        n3=name3.getText().toString().trim();
        n4=name4.getText().toString().trim();
        cn1=contact1.getText().toString().trim();
        cn2=contact2.getText().toString().trim();
        cn3=contact3.getText().toString().trim();
        cn4=contact4.getText().toString().trim();

        if (TextUtils.isEmpty(n1)){
            name1.setError("Cannot be Empty");
        }
        else if (TextUtils.isEmpty(cn1)){
            contact1.setError("Cannot be Empty");
        }
        else if (TextUtils.isEmpty(n2)){
            name2.setError("Cannot be Empty");
        }
        else if (TextUtils.isEmpty(cn2)){
            contact2.setError("Cannot be Empty");
        }
        else if (TextUtils.isEmpty(n3)){
            name3.setError("Cannot be Empty");
        }
        else if (TextUtils.isEmpty(cn3)){
            contact3.setError("Cannot be Empty");
        }
        else if (TextUtils.isEmpty(n4)){
            name4.setError("Cannot be Empty");
        }
        else if (TextUtils.isEmpty(cn4)){
            contact4.setError("Cannot be Empty");
        }
        else {
            if (row_check==1) {
                db2 = mydb2.getWritableDatabase();
                cv1 = new ContentValues();
                cv2 = new ContentValues();
                cv3 = new ContentValues();
                cv4 = new ContentValues();

                //Adding values in content values
                cv1.put(MyDatabase2.NAME1, n1);
                cv1.put(MyDatabase2.PHONE1, cn1);

                cv2.put(MyDatabase2.NAME1, n2);
                cv2.put(MyDatabase2.PHONE1, cn2);

                cv3.put(MyDatabase2.NAME1, n3);
                cv3.put(MyDatabase2.PHONE1, cn3);

                cv4.put(MyDatabase2.NAME1, n4);
                cv4.put(MyDatabase2.PHONE1, cn4);

                //Inserting Values
                long id1 = db2.insert(MyDatabase2.TABLE_NAME1, null, cv1);
                long id2 = db2.insert(MyDatabase2.TABLE_NAME1, null, cv2);
                long id3 = db2.insert(MyDatabase2.TABLE_NAME1, null, cv3);
                long id4 = db2.insert(MyDatabase2.TABLE_NAME1, null, cv4);

                if (id1 < 0 & id2 < 0 & id3 < 0 & id4 < 0) {
                    Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                    row_check=4;
                }
            }
            else if (row_check==4){
                Toast.makeText(this, "Contacts are saved...Click on Update to change any previously saved contacts", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void safeCircleBackArrow(View view) {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
    }
}
