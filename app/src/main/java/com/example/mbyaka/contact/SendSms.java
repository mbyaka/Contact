package com.example.mbyaka.contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by YAKA on 2.1.2016.
 */
public class SendSms extends Activity{

    EditText editText_messageNumber;
    EditText editText_messageText;
    Button btn_send_sms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_send_sms);

        editText_messageNumber = (EditText)findViewById(R.id.editText_messageNumber);
        editText_messageText = (EditText)findViewById(R.id.editText_messageText);
        btn_send_sms= (Button)findViewById(R.id.btn_send_sms);


        editText_messageNumber.setText(getIntent().getExtras().getString("Number").toString());

        btn_send_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS(editText_messageNumber.getText().toString(), editText_messageText.getText().toString());
            }
        });
    }
    private void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(getApplication(),"Mesajınız Gönderildi...",Toast.LENGTH_SHORT).show();
            System.exit(0);
        }
        catch (Exception e)
        {
            Toast.makeText(getApplication(),"Mesaj Gönderilirken Bir Hata Ouştu!",Toast.LENGTH_SHORT).show();
        }
    }
}