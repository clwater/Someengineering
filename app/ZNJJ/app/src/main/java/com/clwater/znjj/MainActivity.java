package com.clwater.znjj;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static TextView show , time_date , show_title , power;
    Button get ,save;
    EditText phone;
    static Activity activity;
    static float dip ,wi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startActivity(new Intent(this , Error.class));



        activity = this;


        show_title = (TextView) findViewById(R.id.show_title);
        //power = (TextView) findViewById(R.id.power);

//        WindowManager wm = this.getWindowManager();
//
//        int width = wm.getDefaultDisplay().getWidth();
//        int height = wm.getDefaultDisplay().getHeight();
//
//        dip = (float) (height * 5 / 11.0 / 100);
//        wi = (float) (width / 3.5);
//        power.setLayoutParams(new LinearLayout.LayoutParams((int)wi,  0 ));
//
//        //power.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
//        Log.d("testw" , "  3   "  + dip + "");



        show = (TextView) findViewById(R.id.show);
        time_date = (TextView) findViewById(R.id.date);
        save = (Button) findViewById(R.id.save);
        phone = (EditText)findViewById(R.id.phone);
        create();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p = phone.getText().toString();
                if (p.length() > 1) {

                    SharedPreferences.Editor phone_w = null;
                    phone_w = getSharedPreferences("znjj" , Activity.MODE_PRIVATE).edit();

                    phone_w.putString("phone" , p);
                    phone_w.commit();
                    Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "号码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });



        get = (Button) findViewById(R.id.get);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String p = phone.getText().toString();
                if (p.length() > 1 ) {
                    time_date.setText("正在获取中......");
                    android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                    smsManager.sendTextMessage(p, null, "9999", null, null);
                    Toast.makeText(getApplicationContext(), "获取中请稍后", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "请进入设置号码", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    private void create() {
        SharedPreferences phone_g = null;
        phone_g = this.getSharedPreferences("znjj" , Activity.MODE_PRIVATE);
        String p = phone_g.getString("phone" , "");
        //Log.d("zcfout" , "p: " + p);
        if (p.length()>1) {
            phone.setText(p);
        }


    }


    public  static void changedate(String s , String time){
        String[] date = s.split("AAAA");
        show_title.setText("");
        show.setText(date[1] + " %");

        if(Integer.valueOf(date[1]) >= 90 ) {
            activity.startActivity(new Intent(activity, Error.class));
            //power.setBackgroundColor(Color.parseColor("#FF0000"));
        }
//        else{
//            power.setBackgroundColor(Color.parseColor("#00ff00"));
//        }
           time_date.setText("数据更新成功, \n----" + time);

//        power.setLayoutParams(new LinearLayout.LayoutParams((int)wi, (int) (dip * Integer.valueOf(date[1]))));

    }
}
