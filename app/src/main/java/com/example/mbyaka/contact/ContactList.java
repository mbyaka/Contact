package com.example.mbyaka.contact;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by YAKA on 2.1.2016.
 */
public class ContactList extends Activity {

    private ListView contactList;
    private ArrayList<String> names;
    private ArrayList<Person> personList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contact_list);

        contactList =(ListView)findViewById(R.id.listView_contact);
        names = new ArrayList<String>();
        personList = new ArrayList<Person>();
        databaseHelper = new DatabaseHelper(getApplicationContext());

        personList = databaseHelper.getPersons();

        for(int i=0;i<personList.size();i++)
        {
            names.add(personList.get(i).toString());
        }

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, names);
        contactList.setAdapter(arrayAdapter);


    }
}
