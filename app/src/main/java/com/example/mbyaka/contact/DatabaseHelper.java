package com.example.mbyaka.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateUtils;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YAKA on 3.1.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "db";
    private static final int DB_VERSION = 1;
    //Tables
    private static final String TABLE_PERSON = "Person";
    private static final String TABLE_PHONECALLS = "PhoneCalls";
    //private static final String TABLE_LOCATIONS = "Locations";
    //private static final String TABLE_LOCATION_CONTACT = "LocationsContacts";
     //private static final String TABLE_MESSAGE = "Messages";
    //Columns
    private static final String PERSON_ID = "ID";
    private static final String PERSON_NAME = "Name";
    private static final String PERSON_SURNAME = "Surname";
    private static final String PERSON_HOME_NUMBER = "HomeNumber";
    private static final String PERSON_WORK_NUMBER = "WorkNumber";
    private static final String PERSON_MOBILE_NUMBER = "MobileNumber";
    private static final String PERSON_EMAIL = "E_mail";
    private static final String PERSON_LOCATION = "Location";
    private static final String PERSON_TOTAL_DURATION_INCOMING_CALL = "TotalDurationIncomingCall";
    private static final String PERSON_TOTAL_DURATION_OUTGOING_CALL = "TotalDurationOutgoingCall";
    private static final String PERSON_NUMBER_OF_MISSING_CALL = "NumberOfMissingCall";
    private static final String PERSON_NUMBER_OF_SENT_MESSAGE = "NumberOfSentMessage";
    private static final String PERSON_NUMBER_OF_RECEIVED_MESSAGE = "NumberOfReceivedMessage";


    private static final String PERSON_PHONECALLS_ID = "ID";
    private static final String PERSON_PHONECALLS_DATE = "Date";
    private static final String PERSON_PHONECALLS_TIME = "Time";
    private static final String PERSON_PHONECALLS_DURATION = "Duration";
    private static final String PERSON_PHONECALLS_IS_COMING = "IsComing";
    private static final String PERSON_PHONECALLS_PERSON_ID = "PersonID";

/*
    private static final String MESSAGE_ID = "id";
    private static final String MESSAGE_FROM = "from";
    private static final String MESSAGE_TO = "to";
    private static final String MESSAGE_BODY = "body";
    private static final String MESSAGE_DATE = "date";
*/

    private static final String CREATE_TABLE_PERSON = "Create table if not exists "+TABLE_PERSON+"" +
            "('"+PERSON_ID+"' INTEGER PRIMARY KEY ," +
            ""+ PERSON_NAME+" TEXT , " +
            ""+ PERSON_SURNAME+" TEXT ," +
            ""+ PERSON_HOME_NUMBER+" TEXT," +
            ""+ PERSON_WORK_NUMBER+" TEXT," +
            ""+ PERSON_MOBILE_NUMBER+" TEXT," +
            ""+ PERSON_EMAIL+" TEXT," +
            ""+ PERSON_LOCATION+" TEXT," +
            ""+ PERSON_TOTAL_DURATION_INCOMING_CALL+ " INTEGER, "+
            ""+ PERSON_TOTAL_DURATION_OUTGOING_CALL+ " INTEGER, "+
            ""+ PERSON_NUMBER_OF_MISSING_CALL+ " INTEGER, "+
            ""+ PERSON_NUMBER_OF_SENT_MESSAGE+ " INTEGER, "+
            ""+ PERSON_NUMBER_OF_RECEIVED_MESSAGE+ " INTEGER);";

    private static final String CREATE_TABLE_PHONECALLS = "Create table if not exists "+ TABLE_PHONECALLS+""+
            "( "+PERSON_PHONECALLS_ID+" INTEGER PRIMARY KEY , "+
            ""+  PERSON_PHONECALLS_DATE+" TEXT ,"+
            ""+  PERSON_PHONECALLS_TIME+" TEXT ,"+
            ""+  PERSON_PHONECALLS_DURATION+" INTEGER ,"+
            ""+  PERSON_PHONECALLS_IS_COMING+" INTEGER ,"+
            ""+ PERSON_PHONECALLS_PERSON_ID +" INTEGER );";
    /*
    private static final String CREATE_TABLE_MESSAGES = " Create table if not exists "+TABLE_MESSAGE +
            "( "+MESSAGE_ID+" integer PRIMARY KEY ,"+
            ""+MESSAGE_FROM+" text ,"+
            ""+MESSAGE_TO+" text ,"+
            ""+MESSAGE_DATE+" text ,"+
            ""+MESSAGE_BODY+" text )";
    */

    public DatabaseHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
        db.execSQL(CREATE_TABLE_PHONECALLS);
    }

    public void addPerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PERSON_ID,person.getID());
        values.put(PERSON_NAME,person.getName());
        values.put(PERSON_SURNAME,person.getSurName());
        values.put(PERSON_HOME_NUMBER,person.getHome_number());
        values.put(PERSON_MOBILE_NUMBER,person.getMobile_number());
        values.put(PERSON_WORK_NUMBER,person.getWork_number());
        values.put(PERSON_EMAIL, person.geteMail());
        values.put(PERSON_LOCATION, person.getLocation());
        values.put(PERSON_TOTAL_DURATION_INCOMING_CALL,person.getTotalDurationInComingCall());
        values.put(PERSON_TOTAL_DURATION_OUTGOING_CALL,person.getTotalDurationOutGoingCall());
        values.put(PERSON_NUMBER_OF_MISSING_CALL,person.getNumberOfMissingCall());
        values.put(PERSON_NUMBER_OF_SENT_MESSAGE,person.getNumberOfSentMessage());
        values.put(PERSON_NUMBER_OF_RECEIVED_MESSAGE,person.getNumberOfReceivedMessage());

        long rowId = db.insert(TABLE_PERSON, null, values);
        db.close();
        if(rowId ==-1) {
            Log.i("ADD_PERSON_ERROR", "rowID = -1");
        }
        person.setID((int) rowId);
    }

    public void removePerson(Person person){
        String query = "Delete from '"+TABLE_PERSON + "' where "+PERSON_ID+" = "+person.getID();
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public Person getPerson(int personID){
        Person person =null;
        ArrayList<PhoneCalls> phoneCallses = new ArrayList<PhoneCalls>();
        PhoneCalls phoneCalls = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String queryPerson = "Select * from '" + TABLE_PERSON + "' where "+PERSON_ID+" = "+personID;

        String queryPhoneCalls = "Select * from '" + TABLE_PHONECALLS + "' where "+ PERSON_PHONECALLS_PERSON_ID +" = "+personID;

        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date date=null;

        DateFormat tdf = new SimpleDateFormat("hh:mm:ss a");
        Date time=null;

        Cursor cursorPhoneCalls = db.rawQuery(queryPhoneCalls,null);
        if(cursorPhoneCalls.moveToFirst())
        {
            do{
                try {
                    date = df.parse(cursorPhoneCalls.getString(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_DATE)));
                    time = tdf.parse(cursorPhoneCalls.getString(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_TIME)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                phoneCalls = new PhoneCalls(date,
                                            time,
                                            cursorPhoneCalls.getInt(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_DURATION)),
                                (cursorPhoneCalls.getInt(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_IS_COMING)) == 1)
                                            );

                phoneCallses.add(phoneCalls);
            }while(cursorPhoneCalls.moveToNext());
        }


        Cursor cursorPerson = db.rawQuery(queryPerson,null);
        if(cursorPerson.moveToNext()){
            person  = new Person(
                                cursorPerson.getInt(cursorPerson.getColumnIndex(   PERSON_ID)),
                                cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_NAME)),
                                cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_SURNAME)),
                                cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_HOME_NUMBER)),
                                cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_WORK_NUMBER)),
                                cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_MOBILE_NUMBER)),
                                cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_EMAIL)),
                                cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_LOCATION)),
                                cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_TOTAL_DURATION_INCOMING_CALL)),
                                cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_TOTAL_DURATION_OUTGOING_CALL)),
                                cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_NUMBER_OF_MISSING_CALL)),
                                cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_NUMBER_OF_SENT_MESSAGE)),
                                cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_NUMBER_OF_RECEIVED_MESSAGE)),
                                phoneCallses
                                );
        }
        db.close();
        return person;
    }

    public Person getPerson(String phoneNumber){
        Person person =null;
        ArrayList<PhoneCalls> phoneCallses = new ArrayList<PhoneCalls>();
        PhoneCalls phoneCalls = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String queryPerson = "Select * from '" + TABLE_PERSON + "' where "+PERSON_MOBILE_NUMBER+" = "+phoneNumber;

        Cursor cursorPerson = db.rawQuery(queryPerson,null);
        if(cursorPerson.moveToNext()){

            String queryPhoneCalls = "Select * from '" + TABLE_PHONECALLS + "' where "+
                                        PERSON_PHONECALLS_PERSON_ID +" = "+cursorPerson.getInt(cursorPerson.getColumnIndex(  PERSON_ID));

            DateFormat df = new SimpleDateFormat("MM/dd/yy");
            Date date=null;

            DateFormat tdf = new SimpleDateFormat("hh:mm:ss a");
            Date time=null;

            Cursor cursorPhoneCalls = db.rawQuery(queryPhoneCalls,null);
            if(cursorPhoneCalls.moveToFirst())
            {
                do{
                    try {
                        date = df.parse(cursorPhoneCalls.getString(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_DATE)));
                        time = tdf.parse(cursorPhoneCalls.getString(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_TIME)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    phoneCalls = new PhoneCalls(date,
                            time,
                            cursorPhoneCalls.getInt(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_DURATION)),
                            (cursorPhoneCalls.getInt(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_IS_COMING)) == 1)
                    );

                    phoneCallses.add(phoneCalls);
                }while(cursorPhoneCalls.moveToNext());
            }

            person  =new Person(
                    cursorPerson.getInt(cursorPerson.getColumnIndex(   PERSON_ID)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_NAME)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_SURNAME)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_HOME_NUMBER)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_WORK_NUMBER)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_MOBILE_NUMBER)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_EMAIL)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_LOCATION)),
                    cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_TOTAL_DURATION_INCOMING_CALL)),
                    cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_TOTAL_DURATION_OUTGOING_CALL)),
                    cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_NUMBER_OF_MISSING_CALL)),
                    cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_NUMBER_OF_SENT_MESSAGE)),
                    cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_NUMBER_OF_RECEIVED_MESSAGE)),
                    phoneCallses
            );

        }
        db.close();
        return person;
    }

    public ArrayList<Person> getPersons(){

        ArrayList<Person> listPersons = new ArrayList<>();
        Person person =null;
        ArrayList<PhoneCalls> phoneCallses = new ArrayList<PhoneCalls>();
        PhoneCalls phoneCalls = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String queryPerson = "Select * from '" + TABLE_PERSON;

        Cursor cursorPerson = db.rawQuery(queryPerson,null);
        if(cursorPerson.moveToNext()){

            String queryPhoneCalls = "Select * from '" + TABLE_PHONECALLS + "' where "+
                    PERSON_PHONECALLS_PERSON_ID +" = "+cursorPerson.getInt(cursorPerson.getColumnIndex(  PERSON_ID));

            DateFormat df = new SimpleDateFormat("MM/dd/yy");
            Date date=null;

            DateFormat tdf = new SimpleDateFormat("hh:mm:ss a");
            Date time=null;

            Cursor cursorPhoneCalls = db.rawQuery(queryPhoneCalls,null);
            if(cursorPhoneCalls.moveToFirst())
            {
                do{
                    try {
                        date = df.parse(cursorPhoneCalls.getString(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_DATE)));
                        time = tdf.parse(cursorPhoneCalls.getString(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_TIME)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    phoneCalls = new PhoneCalls(date,
                            time,
                            cursorPhoneCalls.getInt(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_DURATION)),
                            (cursorPhoneCalls.getInt(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_IS_COMING)) == 1)
                    );

                    phoneCallses.add(phoneCalls);
                }while(cursorPhoneCalls.moveToNext());
            }

            person  =new Person(
                    cursorPerson.getInt(cursorPerson.getColumnIndex(   PERSON_ID)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_NAME)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_SURNAME)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_HOME_NUMBER)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_WORK_NUMBER)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_MOBILE_NUMBER)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_EMAIL)),
                    cursorPerson.getString(cursorPerson.getColumnIndex(PERSON_LOCATION)),
                    cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_TOTAL_DURATION_INCOMING_CALL)),
                    cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_TOTAL_DURATION_OUTGOING_CALL)),
                    cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_NUMBER_OF_MISSING_CALL)),
                    cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_NUMBER_OF_SENT_MESSAGE)),
                    cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_NUMBER_OF_RECEIVED_MESSAGE)),
                    phoneCallses
            );

            listPersons.add(person);
        }
        db.close();
        return listPersons;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
