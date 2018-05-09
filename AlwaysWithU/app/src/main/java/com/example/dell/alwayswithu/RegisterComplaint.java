package com.example.dell.alwayswithu;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RegisterComplaint extends AppCompatActivity {

    public static final String TAG="Toast";
    private ImageView iv1, iv2, iv3, iv4;
    String[] source={"--Select a Category--","Harassment","Corruption","Domestic Violence","Other Illegal Offencs"};
    ArrayAdapter adapter;
    Spinner rcspin;
    Intent RC_i, RC_i2;
    EditText information, name_number;
    static int RC_check=0;
    //private int reqSize=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint);
        rcspin= (Spinner) findViewById(R.id.rcspin);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,source);
        rcspin.setAdapter(adapter);
        iv1=findViewById(R.id.iv1);
        iv2=findViewById(R.id.iv2);
        iv3=findViewById(R.id.iv3);
        iv4=findViewById(R.id.iv4);
        information=findViewById(R.id.RC_information);
        name_number=findViewById(R.id.RC_Name_Number);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //camera
        if(requestCode==1&&resultCode==Activity.RESULT_OK&&data!=null){
            Bitmap photo= (Bitmap) data.getExtras().get("data");
            if(iv1.getDrawable()==null)
                iv1.setImageBitmap(photo);
            else if(iv2.getDrawable()==null)
                iv2.setImageBitmap(photo);
            else if(iv3.getDrawable()==null)
                iv3.setImageBitmap(photo);
            else
                iv4.setImageBitmap(photo);
        }
        //gallery
        if (requestCode == 4 && resultCode == Activity.RESULT_OK && data != null) {
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                String multiple_inf, multiple_name;
                multiple_inf = information.getText().toString().trim();
                multiple_name = name_number.getText().toString().trim();
                Log.d(TAG, "Multiple_Information: "+multiple_inf+"\nName_Number: "+multiple_name);
                RC_check = 2;
                RC_i2 = new Intent(Intent.ACTION_SEND_MULTIPLE);
                RC_i2.setType("*/*");
                RC_i2.putExtra(Intent.EXTRA_SUBJECT, "Reporting Incident from ALWAYS WITH U");
                RC_i2.putExtra(Intent.EXTRA_EMAIL, new String[]{"Alwayswithumanger@gmail.com"});
                RC_i2.putExtra(Intent.EXTRA_TEXT, multiple_inf + " " + multiple_name);

                /*for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    Uri uri = item.getUri();
                    if (i == 0) {
                        try {
                            Bitmap img=decodeUri(this, uri, reqSize);
                            iv1.setImageBitmap(img);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    if (i == 1) {
                        try {
                            Bitmap img=decodeUri(this, uri, reqSize);
                            iv2.setImageBitmap(img);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    if (i == 2) {
                        try {
                            Bitmap img= decodeUri(this, uri, reqSize);
                            iv3.setImageBitmap(img);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    if (i == 3) {
                        try {
                            Bitmap img=decodeUri(this, uri, reqSize);
                            iv4.setImageBitmap(img);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else
                Toast.makeText(this, "Error ClipData is null", Toast.LENGTH_SHORT).show();*/

                ArrayList<Uri> uris=new ArrayList<>();
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    Uri uri = item.getUri();
                    if(i==0){
                        iv1.setImageURI(uri);
                        uris.add(uri);
                    }
                    if(i==1){
                        iv2.setImageURI(uri);
                        uris.add(uri);
                    }
                    if(i==2){
                        iv3.setImageURI(uri);
                        uris.add(uri);
                    }
                    if(i==3){
                        iv4.setImageURI(uri);
                        uris.add(uri);
                    }
                }
                RC_i2.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
            }
        }
    }

    //to resize the image from gallery
    /*public Bitmap decodeUri(Context c, Uri uri, final int requiredSize)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        while (true) {
            if (width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
    }*/

    public void backArrow(View view) {
        Intent i=new Intent(this,Home.class);
        startActivity(i);
    }


    public void AudioButton(View view) {
        Intent intent= new Intent(this,AudioRecording.class);
        startActivity(intent);
    }

    public void gallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent,
                "select multiple images"), 4);
    }

    public void camera(View view) {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    public void registerComplaint(View view) {
        if (RC_check==0){
            String e="sdsad";
            String inf = information.getText().toString().trim();
            String nameNumber=name_number.getText().toString().trim();
            if(TextUtils.isEmpty(inf)){
                information.setError("Field cannot be Empty");
            }
            else{
                Intent RC_i3=new Intent(Intent.ACTION_SEND);
                RC_i3.setType("plain/text");
                RC_i3.putExtra(Intent.EXTRA_EMAIL,new String[]{"Alwayswithumanger@gmail.com"});
                RC_i3.putExtra(Intent.EXTRA_SUBJECT,"Reporting Incident from ALWAYS WITH U");
                RC_i3.putExtra(Intent.EXTRA_TEXT,inf+" "+nameNumber);
                startActivity(Intent.createChooser(RC_i3,"Choose app to Report an incident (prefer Gmail) "));
            }
        }
        /*else if(RC_check==1) {
            try {
                startActivity(Intent.createChooser(RC_i, "Choose app to Report an incident (prefer Gmail) "));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "There are no clients installed for sending the Report", Toast.LENGTH_SHORT).show();
            }
            RC_check=0;
        }*/
        else if (RC_check==2){
            try {
                startActivity(Intent.createChooser(RC_i2, "Choose app to Report an incident (prefer Gmail) "));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "There are no clients installed for sending the Report", Toast.LENGTH_SHORT).show();
            }
            RC_check=0;
        }

    }
}
