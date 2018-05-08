package com.example.dell.alwayswithu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OTP extends AppCompatActivity {

    private EditText otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otp= (EditText) findViewById(R.id.etOTP);
    }


    public void check(View view) {
        Bundle b=getIntent().getExtras();
        String msg_check=b.getString("msg");

        String otp1=otp.getText().toString().trim();

        if (otp1.equals(msg_check)){
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
            Toast.makeText(this, "User Verified....Sign In with your credentials", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "OTP is not verified", Toast.LENGTH_SHORT).show();
        }
    }
}
