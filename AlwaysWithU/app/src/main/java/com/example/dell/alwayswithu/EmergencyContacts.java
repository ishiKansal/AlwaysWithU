package com.example.dell.alwayswithu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class EmergencyContacts extends AppCompatActivity {

    private TextView name1, name2, name3, name4, name5, name6, name7, name8;
    private TextView number1, number2, number3, number4, number5, number6, number7, number8;
    private ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8;
    public static final int REQUEST_PHONE_CALL=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts);

        //find view by id
        name1=findViewById(R.id.name1);
        name2=findViewById(R.id.name2);
        name3=findViewById(R.id.name3);
        name4=findViewById(R.id.name4);
        name5=findViewById(R.id.name5);
        name6=findViewById(R.id.name6);
        name7=findViewById(R.id.name7);
        name8=findViewById(R.id.name8);

        number1=findViewById(R.id.number1);
        number2=findViewById(R.id.number2);
        number3=findViewById(R.id.number3);
        number4=findViewById(R.id.number4);
        number5=findViewById(R.id.number5);
        number6=findViewById(R.id.number6);
        number7=findViewById(R.id.number7);
        number8=findViewById(R.id.number8);

        iv1=findViewById(R.id.iv1);
        iv2=findViewById(R.id.iv2);
        iv3=findViewById(R.id.iv3);
        iv4=findViewById(R.id.iv4);
        iv5=findViewById(R.id.iv5);
        iv6=findViewById(R.id.iv6);
        iv7=findViewById(R.id.iv7);
        iv8=findViewById(R.id.iv8);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = number1.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
                if (ContextCompat.checkSelfPermission(EmergencyContacts.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }
                else
                {
                    startActivity(intent);
                }
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = number2.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
                if (ContextCompat.checkSelfPermission(EmergencyContacts.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }
                else
                {
                    startActivity(intent);
                }
            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = number3.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
                if (ContextCompat.checkSelfPermission(EmergencyContacts.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }
                else
                {
                    startActivity(intent);
                }
            }
        });

        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = number4.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
                if (ContextCompat.checkSelfPermission(EmergencyContacts.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }
                else
                {
                    startActivity(intent);
                }
            }
        });

        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = number5.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
                if (ContextCompat.checkSelfPermission(EmergencyContacts.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }
                else
                {
                    startActivity(intent);
                }
            }
        });

        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = number6.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
                if (ContextCompat.checkSelfPermission(EmergencyContacts.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }
                else
                {
                    startActivity(intent);
                }
            }
        });

        iv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = number7.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
                if (ContextCompat.checkSelfPermission(EmergencyContacts.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }
                else
                {
                    startActivity(intent);
                }
            }
        });

        iv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = number8.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
                if (ContextCompat.checkSelfPermission(EmergencyContacts.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }
                else
                {
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(EmergencyContacts.this, "Permission Granted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(EmergencyContacts.this,"Permission Denied",Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public void backArrowEmergencyContacts(View view) {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(EmergencyContacts.this, new
                String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
    }
}
