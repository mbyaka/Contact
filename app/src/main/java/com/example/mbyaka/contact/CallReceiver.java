package com.example.mbyaka.contact;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by YAKA on 6.1.2016.
 */
public class CallReceiver extends BroadcastReceiver {

    private static int lastState = TelephonyManager.CALL_STATE_IDLE;
    private static boolean isIncoming;
    private static String phoneNumber;  //because the passed incoming is only valid in ringing
    private long lastCurrentMillis=0;
    private DatabaseHelper databaseHelper;
    @Override
    public void onReceive(Context context, Intent intent) {
        databaseHelper = new DatabaseHelper(context);

        Log.i("ARAMA", "GİRDİM");

        TelephonyManager tmgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        MyPhoneStateListener PhoneListener = new MyPhoneStateListener();
        tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

/*
        if (!intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            phoneNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");

            Log.i("ARAMA","GİDEN");
            //Log.i("NUMARA",phoneNumber);

            PhoneCall call = new PhoneCall(new Date(),
                                        new Date(),
                                        0,
                                        false,
                                        false,
                                        databaseHelper.getPerson(phoneNumber).getID());
            databaseHelper.addPhoneCall(call);
        }
        else if(intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            Log.i("ARAMA","GELEN");
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String phoneNumber = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

            //Log.i("NUMARA",phoneNumber);
            if(stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                PhoneCall call = null;
                if(lastState==TelephonyManager.CALL_STATE_RINGING){
                    // cevapsiz
                    call = new PhoneCall(new Date(),
                                        new Date(),
                                        0,
                                        true,
                                        true,
                                        databaseHelper.getPerson(phoneNumber).getID());

                }else if(lastState==TelephonyManager.CALL_STATE_OFFHOOK){
                    // konusuludu
                    call = new PhoneCall(new Date(),
                                        new Date(),
                                        (int)((System.currentTimeMillis()-lastCurrentMillis)/1000),
                                        false,
                                        false,
                                        databaseHelper.getPerson(phoneNumber).getID());
                }else{
                    // bu durum yok sanirsam...
                }
                if(call!=null)
                    databaseHelper.addPhoneCall(call);
                lastState = TelephonyManager.CALL_STATE_IDLE;

            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                lastState = TelephonyManager.CALL_STATE_OFFHOOK;
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                lastState = TelephonyManager.CALL_STATE_RINGING;
            }
            lastCurrentMillis = System.currentTimeMillis();

        }
*/
    }

    private class MyPhoneStateListener extends PhoneStateListener {

        public void onCallStateChanged(int state, String incomingNumber) {

            if(!incomingNumber.equals(""))
            {
                phoneNumber = incomingNumber;
            }

            if(phoneNumber!= null && !phoneNumber.equals("")) {
                if(phoneNumber.charAt(0) == '+' && phoneNumber.charAt(1) == '9')
                {
                    phoneNumber = phoneNumber.substring(2,phoneNumber.length());
                }

                Log.i("NUMARA",phoneNumber);
                if (state == TelephonyManager.CALL_STATE_IDLE) {
                    PhoneCall call = null;
                    if (lastState == TelephonyManager.CALL_STATE_RINGING) {
                        // cevapsiz
                        call = new PhoneCall(new Date(),
                                new Date(),
                                0,
                                true,
                                true,
                                databaseHelper.getPerson(phoneNumber).getID());

                    } else if (lastState == TelephonyManager.CALL_STATE_OFFHOOK) {
                        // konusuludu
                        call = new PhoneCall(new Date(),
                                new Date(),
                                (int) ((System.currentTimeMillis() - lastCurrentMillis) / 1000),
                                false,
                                false,
                                databaseHelper.getPerson(phoneNumber).getID());
                    } else {
                        // bu durum yok sanirsam...
                    }
                    if (call != null)
                        databaseHelper.addPhoneCall(call);
                    lastState = TelephonyManager.CALL_STATE_IDLE;

                } else if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
                    lastState = TelephonyManager.CALL_STATE_OFFHOOK;
                } else if (state == TelephonyManager.CALL_STATE_RINGING) {
                    lastState = TelephonyManager.CALL_STATE_RINGING;
                }
                lastCurrentMillis = System.currentTimeMillis();
            }

        }
    }
}
