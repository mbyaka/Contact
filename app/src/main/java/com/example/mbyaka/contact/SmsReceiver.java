package com.example.mbyaka.contact;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.style.TtsSpan;
import android.util.Log;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;

/**
 * Created by YAKA on 6.1.2016.
 */
public class SmsReceiver extends BroadcastReceiver {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++)
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                for (SmsMessage message : messages) {
                    String msg = message.getMessageBody();
                    String who = message.getOriginatingAddress();

                    if(who.charAt(0) == '+' && who.charAt(1)=='9')
                    {
                        who = who.subSequence(2,who.length()).toString();
                    }

                    Toast.makeText(context,"Num: "+who + "\n Message: " + msg, Toast.LENGTH_LONG).show();
                    try {
                        new DatabaseHelper(context).addMessage(new Message(who,true, new Date(),new Date(),msg));
                    }catch (Exception ex){
                        Log.i("Hata","addMessage");
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
