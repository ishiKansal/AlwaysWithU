package com.example.dell.alwayswithu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView t1;
    ImageButton ib1, ib2, ib3, ib4, ib5;
    NavigationView navigationView;
    View view;
    LayoutInflater inflater;
    SharedPreferences sp1;
    SharedPreferences.Editor editor1;
    MyDatabase2 mydb2_Home;
    SQLiteDatabase db_Home;
    SwitchCompat switchCompat;
    double latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView= (NavigationView) findViewById(R.id.nav_view);
        view=navigationView.getHeaderView(0);

        GPSTracker gps=new GPSTracker(this);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();
        Toast.makeText(this, "Hello"+latitude+longitude, Toast.LENGTH_SHORT).show();
        Menu menu=navigationView.getMenu();
        MenuItem item=menu.findItem(R.id.timer);

        View actionView= MenuItemCompat.getActionView(item);
        switchCompat=actionView.findViewById(R.id.switchbtn);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

        /*t1 = navigationView.findViewById(R.id.edtEmail);
        sp1=getSharedPreferences("My_Pref1", Context.MODE_PRIVATE);
        String email= sp1.getString("email_key", null);
        t1.setText(email);*/

        // t1 = (TextView) findViewById(R.id.tvNAV_HEAD1);
        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            View headerLayout = navigationView.getHeaderView(0);
            t1 = headerLayout.findViewById(R.id.tvNAV_HEAD1);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        ib1 = (ImageButton) findViewById(R.id.btnregister);
        ib2 = (ImageButton) findViewById(R.id.btntravel);
        ib3 = (ImageButton) findViewById(R.id.btnSOS);
        ib4 = (ImageButton) findViewById(R.id.btnsafeCircle);
        ib5 = (ImageButton) findViewById(R.id.btnaroundme);
        mydb2_Home=new MyDatabase2(this);
        switchCompat= (SwitchCompat) findViewById(R.id.switchbtn);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.timer) {
            switchCompat.setChecked(!switchCompat.isChecked());
            navigationView.getMenu().getItem(0).setChecked(true);

        } else if (id == R.id.nav_ec) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void register(View view) {
        Intent i = new Intent(this, RegisterComplaint.class);
        startActivity(i);
    }

    public void travel(View view) {
        Intent i = new Intent(this, Travel.class);
        startActivity(i);
    }

    public void safeCircle(View view) {
        Intent i = new Intent(this, MySafeCircle.class);
        startActivity(i);
    }

    public void emergencyContacts(MenuItem item){
        Intent i=new Intent(this, EmergencyContacts.class);
        startActivity(i);
    }

    public void logout(MenuItem item) {
        sp1=getSharedPreferences("My_Pref1", Context.MODE_PRIVATE);
        editor1= sp1.edit();
        editor1.clear();
        editor1.commit();
        Toast.makeText(this, "I am logout", Toast.LENGTH_SHORT).show();
        Intent ii=new Intent(this,MainActivity.class);
        startActivity(ii);
    }

    public void shareIt(MenuItem item){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody="This is a great app, you should try it out!!!";
        String shareSub="Always with U";
        intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(intent, "Share Via"));
    }

    public void sos(View view) {
        db_Home=mydb2_Home.getReadableDatabase();
        String [] cols2={mydb2_Home.PHONE1};
        int i=0;
        String[] tel=new String[100];
        Cursor cc=db_Home.query(mydb2_Home.TABLE_NAME1,cols2,null,null,null,null,null);
        while(cc.moveToNext()){
            int id1_Home=cc.getColumnIndex(mydb2_Home.PHONE1);
            tel[i]=cc.getString(id1_Home);
            if(i>3){
                break;
            }
            else {
                i++;
            }
        }

        String message="HELP!!!!!  My CURRENT LOCATION: "+"http://www.google.com/maps/place/"+latitude+","+longitude;
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(tel[0],null,message,null,null);
        SmsManager sms2=SmsManager.getDefault();
        sms2.sendTextMessage(tel[1],null,message,null,null);
        SmsManager sms3=SmsManager.getDefault();
        sms3.sendTextMessage(tel[2],null,message,null,null);
        SmsManager sms4= SmsManager.getDefault();
        sms4.sendTextMessage(tel[3],null,message,null,null);

    }

    public void aroundMe(View view) {
        Intent i = new Intent(this, AroundMe.class);
        startActivity(i);
    }

}
