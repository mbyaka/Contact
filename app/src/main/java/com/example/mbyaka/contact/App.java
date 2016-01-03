package com.example.mbyaka.contact;

import android.app.Application;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by YAKA on 3.1.2016.
 */
public class App extends Application {

    private ArrayList<Person> personList;
    private DatabaseHelper databaseHelper;

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        personList = new ArrayList<Person>();
        databaseHelper = new DatabaseHelper(getApplicationContext());

        Person person =null;

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
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);

                    if (pCur.moveToNext()){

                        String[] names = null;
                        String surName = "";

                        if(name.split(" ").length > 0) {
                            names= name.split(" ");
                            surName = names[names.length - 1];
                            name ="";
                            for(int i=0;i<names.length-1;i++)
                                name += names[i] + " ";
                        }
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        ArrayList<PhoneCalls> phoneCallses = new ArrayList<PhoneCalls>();
                        person = new Person(personList.size(),name,surName,"","",phoneNo,"","",0,0,0,0,0,phoneCallses);

                        //personList.add(person);
                        databaseHelper.addPerson(person);
                    }
                    pCur.close();
                }
            }
        }
    }
}
