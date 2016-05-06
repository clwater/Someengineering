package com.clwater.zcf;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by yszsyf on 16/3/7.
 */
public class Conter  extends AppCompatActivity implements View.OnClickListener {
    private TextView openlight , closelight , opendoor , closedoor , openfs , closefs;
    private EditText phonenumber;
    private Button savephone;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conter);

       // create();

        openlight = (TextView) findViewById(R.id.openlight);
        openlight.setOnClickListener(this);
        closelight = (TextView) findViewById(R.id.closelight);
        closelight.setOnClickListener(this);
        opendoor = (TextView) findViewById(R.id.opendoor);
        opendoor.setOnClickListener(this);
        closedoor = (TextView)findViewById(R.id.closedoor);
        closedoor.setOnClickListener(this);
        openfs = (TextView)findViewById(R.id.openfs);
        openfs.setOnClickListener(this);
        closefs = (TextView)findViewById(R.id.closefs);
        closefs.setOnClickListener(this);

        phonenumber = (EditText)findViewById(R.id.phonenumber);

        savephone = (Button) findViewById(R.id.savephone);
        savephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String p = phonenumber.getText().toString();
                if (p.length() > 1) {

                    SharedPreferences.Editor phone_w = null;
                    phone_w = getSharedPreferences("zcf" , Activity.MODE_PRIVATE).edit();

                    phone_w.putString("phone" , p);
                    phone_w.commit();
                    Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "号码错误", Toast.LENGTH_SHORT).show();
                }



            }
        });

        create();


    }

    private void create() {
        SharedPreferences phone_g = null;
        phone_g = this.getSharedPreferences("zcf" , Activity.MODE_PRIVATE);
        String p = phone_g.getString("phone" , "");
        Log.d("zcfout" , "p: " + p);
        if (p.length()>1) {
            phonenumber.setText(p);
        }




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.openlight:
                sendmessage(0);
                break;
            case R.id.closelight:
                sendmessage(1);
                break;
            case R.id.opendoor:
                sendmessage(2);
                break;
            case R.id.closedoor:
                sendmessage(3);
                break;
            case R.id.openfs:
                sendmessage(4);
                break;
            case R.id.closefs:
                sendmessage(5);
                break;
        }
    }

    private void sendmessage(int index) {
        String phone , message = null;

        phone = phonenumber.getText().toString();

        switch (index){
            case 0:
                message = "1111";
                break;
            case 1:
                message = "2222";
                break;
            case 2:
                message = "3333";
                break;
            case 3:
                message = "4444";
                break;
            case 4:
                message = "6666";
                break;
            case 5:
                message = "7777";
                break;
        }


        if (phone.length() > 1) {

            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, message, null, null);

            Toast.makeText(getApplicationContext(), "发送完毕", Toast.LENGTH_SHORT).show();


        }
        else {
            Toast.makeText(getApplicationContext(), "号码错误", Toast.LENGTH_SHORT).show();
        }






    }
}
