package com.example.dell.alwayswithu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    EditText fp1;
    Button btnfp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        fp1= (EditText) findViewById(R.id.edtfp1);
        btnfp1= (Button) findViewById(R.id.btnVerify);
    }

    public void verify(View view) {


        Bundle b=getIntent().getExtras();
        String fp_msg_check=b.getString("fp_msg");
        String fp_email_check=b.getString("fp_email");
        Toast.makeText(this, fp_email_check, Toast.LENGTH_SHORT).show();
        String fp11=fp1.getText().toString().trim();
        if (fp11.equals(fp_msg_check)){
            Intent i=new Intent(this,CreatePassword.class);
            i.putExtra("NewEmail",fp_email_check);
            startActivity(i);
        }

    }
}
