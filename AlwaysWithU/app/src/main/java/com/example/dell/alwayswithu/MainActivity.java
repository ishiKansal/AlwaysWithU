package com.example.dell.alwayswithu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AbsRuntimePermission {
    public int session_check = 1;
    private static final int REQUEST_PERMISSION = 10;

    TextView tvsignup;
    Button btnsignin;
    EditText etemail, etpaswd;
    SignUp su;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("My_Pref1", Context.MODE_PRIVATE);
        d = sp.getString("check", null);
        if (d == null) {
            etemail = (EditText) findViewById(R.id.etemail);
            etpaswd = (EditText) findViewById(R.id.etpaswd);
            tvsignup = (TextView) findViewById(R.id.tvsignup);
            tvsignup.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    openSignUp();
                }

            });
            su = new SignUp();
            su.mydb = new MyDatabase(this);
            //  sa=new SubActivity();


            btnsignin = (Button) findViewById(R.id.btnsignin);
            btnsignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openHome();
                }
            });

        } else {
            String r = sp.getString("check", null);
            Toast.makeText(this, r, Toast.LENGTH_SHORT).show();
            if (r.equals("1")) {
                Toast.makeText(this, "In new", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, Home.class);
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        }
    }

    @Override
    public void onPermissionGranted(int requestCode) {
        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
    }

    private void openHome() {
        String ma1, ma2, email_check, password_check = null;
        ma1 = etemail.getText().toString().trim();
        ma2 = etpaswd.getText().toString().trim();
        if (TextUtils.isEmpty(ma1)) {
            etemail.setError("Cannot be empty");
        } else if (TextUtils.isEmpty(ma2)) {
            etpaswd.setError("Cannot be empty");
        } else {
            su.db = su.mydb.getReadableDatabase();
            String[] cols = {su.mydb.EMAIL, su.mydb.PASSWORD};
            Cursor c = su.db.query(su.mydb.TABLE_NAME, cols, null, null, null, null, null);
            while (c.moveToNext()) {
                int i1 = c.getColumnIndex(su.mydb.EMAIL);
                int i2 = c.getColumnIndex(su.mydb.PASSWORD);
                email_check = c.getString(i1);
                if (ma1.equals(email_check)) {
                    Toast.makeText(this, "Hello checking", Toast.LENGTH_SHORT).show();
                    password_check = c.getString(i2);
                }
            }

            if (ma2.equals(password_check)) {
                if (session_check == 1) {
                    editor = sp.edit();
                    editor.putString("email_key", ma1);
                    editor.putString("password_key", ma2);
                    editor.putString("check", "1");
                    editor.commit();
                    Toast.makeText(this, "SP_DONE", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Incorrect email and password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openSignUp() {
        requestAppPermission(new String[]{
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET},
                R.string.msg, REQUEST_PERMISSION);
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void Forgotpswd(View view) {
        String fp_email, subject;
        subject = "Code for Forgot Password";
        fp_email = etemail.getText().toString().trim();
        if (TextUtils.isEmpty(fp_email)) {
            etemail.setError("Cannot be empty");
        } else {
            String fp_msg = generateRandomString("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 8);
            SendMail sm = new SendMail(this, fp_email, subject, fp_msg);
            sm.execute();
            Intent i_fp = new Intent(this, ForgotPassword.class);
            i_fp.putExtra("fp_msg", fp_msg);
            i_fp.putExtra("fp_email", fp_email);
            startActivity(i_fp);
        }
    }

    public static String generateRandomString(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }
        return sb.toString();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
