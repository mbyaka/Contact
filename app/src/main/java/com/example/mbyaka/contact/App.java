package com.example.mbyaka.contact;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by YAKA on 3.1.2016.
 */
public class App extends Application {

    private DatabaseHelper databaseHelper;
    public ArrayList<Person> personArrayList;
    int count=0;

    @Override
    public void onCreate() {
        super.onCreate();

        Boolean isTrue = false;
        personArrayList = new DatabaseHelper(getApplicationContext()).getPersons();

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("APP_CONFIG", Context.MODE_PRIVATE);
        isTrue = Boolean.parseBoolean(prefs.getString("IS_FIRST",""));

        if(!isTrue) {

            prefs.edit().putString("IS_FIRST", "True").apply();

            databaseHelper = new DatabaseHelper(getApplicationContext());

            Person person = null;

            ContentResolver cr = getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);
            if (cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    if (Integer.parseInt(cur.getString(
                            cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                        Cursor pCur = cr.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id}, null);

                        if (pCur.moveToNext()) {

                            String[] names = null;
                            String surName = "";

                            if (name.split(" ").length > 0) {
                                names = name.split(" ");
                                surName = names[names.length - 1];
                                name = "";
                                for (int i = 0; i < names.length - 1; i++)
                                    name += names[i] + " ";
                            }
                            String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                            phoneNo = editPhoneNumber(phoneNo);

                            ArrayList<PhoneCall> phoneCallses = new ArrayList<PhoneCall>();
                            person = new Person(count, name, surName, "", "", phoneNo, "", "", 0, 0, 0, 0, 0, phoneCallses);

                            count++;
                            databaseHelper.addPerson(person);
                        }
                        pCur.close();
                    }
                }
            }
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        this.personArrayList.clear();
    }

    private String editPhoneNumber(String phoneNo)
    {
        phoneNo = phoneNo.replaceAll(" ","");
        if(phoneNo.charAt(0) == '+' && phoneNo.charAt(1) == '9')
        {
            phoneNo = phoneNo.substring(2,phoneNo.length());
        }
        return phoneNo;
    }
}
