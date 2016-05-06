package com.clwater.zcf;

/**
 * Created by yszsyf on 16/3/7.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiverDemo extends BroadcastReceiver {

    private static final String strRes = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context arg0, Intent arg1) {

        if(strRes.equals(arg1.getAction())){
            StringBuilder sb = new StringBuilder();
            Bundle bundle = arg1.getExtras();
            if(bundle!=null){
                Object[] pdus = (Object[])bundle.get("pdus");
                SmsMessage[] msg = new SmsMessage[pdus.length];
                for(int i = 0 ;i<pdus.length;i++){
                    msg[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                }

                String body = "" , time = "";

                for(SmsMessage curMsg:msg){
                    body = curMsg.getDisplayMessageBody();
                    Date date = new Date(curMsg.getTimestampMillis());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    time = format.format(date);

                }


                if(body.length() > 1) {
                    MainActivity.changedate(body , time);
                }

                this.abortBroadcast();

            }
        }
    }

}