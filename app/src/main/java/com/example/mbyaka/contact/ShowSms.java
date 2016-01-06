package com.example.mbyaka.contact;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by YAKA on 6.1.2016.
 */
public class ShowSms extends Activity {

    private ListView listView_show_sms;
    private TextView textView_show_sms_item;
    private TextView textView_show_sms_who;
    private ArrayList<Message> messages;
    private ArrayList<String> show_sms_body;

    private ArrayAdapter<String> arrayAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_show_sms);

        listView_show_sms = (ListView) findViewById(R.id.listView_show_sms);
        textView_show_sms_who = (TextView) findViewById(R.id.textView_show_sms_who);
        textView_show_sms_item = (TextView) findViewById(R.id.textView_show_sms_item);

        show_sms_body = new ArrayList<String>();

        databaseHelper = new DatabaseHelper(this);
        messages = new ArrayList<Message>();

        String phoneNumber = getIntent().getExtras().getString("PHONE_NUMBER");

        messages = databaseHelper.getMessages(phoneNumber);

        //if(messages.size() != 0) {
        textView_show_sms_who.setText(databaseHelper.getPerson(phoneNumber).toString());

        for (int i = 0; i < messages.size(); i++) {
            String body = messages.get(i).getBody();
            if (messages.get(i).getIsComing()) {
                show_sms_body.add("Geldi\n " + body);
            } else {
                show_sms_body.add("Gönderildi\n " + body);
            }
        }

        arrayAdapter = new ArrayAdapter<String>
                (this, R.layout.layout_show_sms_list_item, R.id.textView_show_sms_item, show_sms_body);
        listView_show_sms.setAdapter(arrayAdapter);

        listView_show_sms.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShowSms.this);
                Message msg = messages.get(position);

                if (msg.getIsComing()) {
                    alertDialogBuilder.setMessage("Kimden: " + msg.getWho() +
                            "\nTarih: " + msg.getDate().toString() +
                            "\nSaat: " + msg.getTime().toString());
                } else {
                    alertDialogBuilder.setMessage("Kime: " + msg.getWho() +
                            "\nTarih: " + msg.getDate().toString() +
                            "\nSaat: " + msg.getTime().toString());
                }

                alertDialogBuilder.show();
                return true;
            }
        });
    }
}
