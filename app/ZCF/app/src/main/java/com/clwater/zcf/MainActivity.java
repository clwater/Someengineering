package com.clwater.zcf;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static TextView main_wd , main_sd , main_gq , main_yw , time_date;
    Button sendmsg ,getmsg;
    static Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startActivity(new Intent(this , Error.class));




        activity = this;


        main_wd = (TextView) findViewById(R.id.main_wd);
        main_sd = (TextView) findViewById(R.id.main_sd);
        main_gq = (TextView) findViewById(R.id.main_gq);
        main_yw = (TextView) findViewById(R.id.main_yw);
        time_date = (TextView) findViewById(R.id.time);
        sendmsg = (Button)findViewById(R.id.sendmsg);
        sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Conter.class));
            }
        });

        getmsg = (Button) findViewById(R.id.getmsg);
        getmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences phone_g = null;
                phone_g = getSharedPreferences("zcf" , Activity.MODE_PRIVATE);
                String p = phone_g.getString("phone" , "");
                if (p.length() > 1 ) {
                    android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                    smsManager.sendTextMessage(p, null, "5555", null, null);
                    Toast.makeText(getApplicationContext(), "获取中请稍后", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "请进入设备控制中设置号码", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }


    public  static void changedate(String s , String time){
        String[] date = s.split("A");
        main_wd.setText(date[1] + " ˚C");
        main_sd.setText(date[2] + " %");
        main_gq.setText(date[3] + " %");
        if (date[4].equals("0")){
            main_yw.setText("正常");
        }else {
            main_yw.setText("异常");
            activity.startActivity(new Intent(activity , Error.class));
        }
        time_date.setText("当前信息来自于: " + time);

    }
}
