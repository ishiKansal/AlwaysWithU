package com.example.dell.alwayswithu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Travel extends AppCompatActivity {

    EditText source,destination,vehicleNumber;
    Button inform;
    MyDatabase2 mydb_Travel;
    SQLiteDatabase db_Travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        source= (EditText) findViewById(R.id.edtSource);
        destination= (EditText) findViewById(R.id.edtDestination);
        vehicleNumber=(EditText)findViewById(R.id.edtVehicleNumber);
        inform= (Button) findViewById(R.id.btnInform);
        mydb_Travel=new MyDatabase2(this);
    }

    public void backArrowTravel(View view) {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
    }


    public void travel_inform(View view) {
        Toast.makeText(this, "Travel_inform", Toast.LENGTH_SHORT).show();
        String s_travel,d_travel,v_travel,msg_travel;
        s_travel=source.getText().toString().trim();
        d_travel=destination.getText().toString().trim();
        v_travel=vehicleNumber.getText().toString().trim();
        msg_travel="Source : "+s_travel+" ,Destination : "+d_travel+" ,Vehicle Number : "+v_travel;
        db_Travel=mydb_Travel.getReadableDatabase();
        String [] cols2={mydb_Travel.PHONE1};
        int i=0;
        String[] tel_Travel=new String[100];
        Cursor cc=db_Travel.query(mydb_Travel.TABLE_NAME1,cols2,null,null,null,null,null);
        while(cc.moveToNext()){
            int id1_Home=cc.getColumnIndex(mydb_Travel.PHONE1);
            tel_Travel[i]=cc.getString(id1_Home);
            if(i>3){
                break;
            }
            else {
                i++;
            }
        }

        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(tel_Travel[0],null,msg_travel,null,null);
        SmsManager sms2=SmsManager.getDefault();
        sms2.sendTextMessage(tel_Travel[1],null,msg_travel,null,null);
        SmsManager sms3=SmsManager.getDefault();
        sms3.sendTextMessage(tel_Travel[2],null,msg_travel,null,null);
        SmsManager sms4= SmsManager.getDefault();
        sms4.sendTextMessage(tel_Travel[3],null,msg_travel,null,null);
    }
}
