package com.example.mbyaka.contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by YAKA on 6.1.2016.
 */
public class SmsList extends Activity {

    private ListView listView_sms;
    private ArrayList<String> numbers;
    private ArrayList<String> names;
    private ArrayList<Message> messages;
    private DatabaseHelper databaseHelper;

    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sms_list);

        listView_sms =(ListView)findViewById(R.id.listView_sms);
        numbers = new ArrayList<String>();
        names = new ArrayList<String>();
        messages = new ArrayList<Message>();
        databaseHelper = new DatabaseHelper(getApplicationContext());

        messages = databaseHelper.getMessages();

        for(Message message : messages)
        {
            if(!numbers.contains(message.getWho())) {
                numbers.add(message.getWho());
                names.add(databaseHelper.getPerson(message.getWho()).toString());
            }
        }

        arrayAdapter=new ArrayAdapter<String>
                (this, R.layout.layout_sms_list_item, R.id.textView_sms_list_item,names);
        listView_sms.setAdapter(arrayAdapter);

        listView_sms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showSms = new Intent(getApplicationContext(),ShowSms.class);
                showSms.putExtra("PHONE_NUMBER",numbers.get(position));
                startActivity(showSms);
            }
        });

    }
}
