package com.clwater.znrqz;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static TextView main_sd , main_wd , main_yw , main_rqz , time;
    private EditText phone;
    public static Button getmsg , savephone , changerqz;
    public static Activity activity;
    public static int pd =  -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;

        create();
        getphone();
    }

    private void getphone() {
        SharedPreferences phone_g = null;
        phone_g = this.getSharedPreferences("znrqz" , Activity.MODE_PRIVATE);
        String p = phone_g.getString("phone" , "");
        //Log.d("znrqz" , "p: " + p);
        if (p.length()>1) {
            phone.setText(p);
        }

    }

    private void create() {
        main_sd = (TextView) findViewById(R.id.main_sd);
        main_wd = (TextView) findViewById(R.id.main_wd);
        main_yw = (TextView) findViewById(R.id.main_yw);
        main_rqz = (TextView) findViewById(R.id.main_rqz);

        time = (TextView) findViewById(R.id.time);

        phone = (EditText) findViewById(R.id.phone);


        getmsg = (Button) findViewById(R.id.getmsg);
        getmsg.setOnClickListener(this);

        savephone = (Button) findViewById(R.id.savephone);
        savephone.setOnClickListener(this);

        changerqz = (Button) findViewById(R.id.sw);
        changerqz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getmsg:
                getmsg();
                break;
            case R.id.savephone:
                savephone();
                break;
            case R.id.sw:
                sw();
                break;
        }

    }

    private void getmsg() {

        String p = phone.getText().toString();
        if (p.length() > 1 ) {
            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage(p, null, "5555", null, null);
            Toast.makeText(getApplicationContext(), "获取中请稍后", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "请进入设备控制中设置号码", Toast.LENGTH_SHORT).show();
        }

    }
    private void savephone() {
        String p = phone.getText().toString();
        if (p.length() > 1) {

            SharedPreferences.Editor phone_w = null;
            phone_w = getSharedPreferences("znrqz" , Activity.MODE_PRIVATE).edit();

            phone_w.putString("phone" , p);
            phone_w.commit();
            Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(getApplicationContext(), "号码错误", Toast.LENGTH_SHORT).show();
        }

    }
    private void sw() {
        String m = "";
        if (pd == -1) {
            Toast.makeText(getApplicationContext(), "当前状态未知,请获取状态后操作", Toast.LENGTH_SHORT).show();
        } else {
            if (pd == 0) {
                m = "1111";
                changerqz.setText("关闭燃气灶");
                main_rqz.setText("打开");
                pd = 1;
            } else {
                m = "2222";
                changerqz.setText("打开燃气灶");
                main_rqz.setText("关闭");
                pd = 0 ;
            }



            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage(phone.getText().toString(), null, m, null, null);
            Toast.makeText(getApplicationContext(), "更改中请稍后", Toast.LENGTH_SHORT).show();
        }

    }

    public static void changedate(String s, String t){
        String[] date = s.split("A");
        main_wd.setText(date[1] + " ˚C");
        main_sd.setText(date[2] + " %");
        main_yw.setText(date[3] + " %");

        if(Integer.valueOf(date[3]) >= 50){
            activity.startActivity(new Intent(activity , Error.class));
        }

        if (date[4].equals("0")){
            main_rqz.setText("关闭");
            pd = 0;
            changerqz.setText("打开燃气灶");
        }else {
            pd = 1;
            main_rqz.setText("打开");
            changerqz.setText("关闭燃气灶");

        }
        time.setText("当前信息来自于: " + t);
    }
}
