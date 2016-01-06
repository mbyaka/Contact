package com.example.mbyaka.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by YAKA on 3.1.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_PERSON = "Person";
    private static final String TABLE_PHONECALLS = "PhoneCalls";
    private static final String TABLE_MESSAGES = "Messages";
    //private static final String TABLE_LOCATIONS = "Locations";
    //private static final String TABLE_LOCATION_CONTACT = "LocationsContacts";

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
    private static final String PERSON_PHONECALLS_IS_MISSING = "IsMissing";
    private static final String PERSON_PHONECALLS_PERSON_ID = "PersonID";


    private static final String MESSAGE_ID = "ID";
    private static final String MESSAGE_WHO = "Who";
    private static final String MESSAGE_IS_COMING = "IsComing";
    private static final String MESSAGE_DATE = "Date";
    private static final String MESSAGE_TIME = "Time";
    private static final String MESSAGE_BODY = "Body";


    private static final String CREATE_TABLE_PERSON = "Create table if not exists "+TABLE_PERSON+"" +
            "("+PERSON_ID+" INTEGER PRIMARY KEY ," +
            ""+ PERSON_NAME+" TEXT , " +
            ""+ PERSON_SURNAME+" TEXT ," +
            ""+ PERSON_HOME_NUMBER+" TEXT ," +
            ""+ PERSON_WORK_NUMBER+" TEXT ," +
            ""+ PERSON_MOBILE_NUMBER+" TEXT ," +
            ""+ PERSON_EMAIL+" TEXT , " +
            ""+ PERSON_LOCATION+" TEXT , " +
            ""+ PERSON_TOTAL_DURATION_INCOMING_CALL+ " INTEGER , "+
            ""+ PERSON_TOTAL_DURATION_OUTGOING_CALL+ " INTEGER , "+
            ""+ PERSON_NUMBER_OF_MISSING_CALL+ " INTEGER , "+
            ""+ PERSON_NUMBER_OF_SENT_MESSAGE+ " INTEGER , "+
            ""+ PERSON_NUMBER_OF_RECEIVED_MESSAGE+ " INTEGER );";

    private static final String CREATE_TABLE_PHONECALLS = "Create table if not exists "+ TABLE_PHONECALLS+""+
            "( "+PERSON_PHONECALLS_ID+" INTEGER PRIMARY KEY , "+
            ""+  PERSON_PHONECALLS_DATE+" TEXT , "+
            ""+  PERSON_PHONECALLS_TIME+" TEXT , "+
            ""+  PERSON_PHONECALLS_DURATION+" INTEGER , "+
            ""+  PERSON_PHONECALLS_IS_COMING+" INTEGER , "+
            ""+  PERSON_PHONECALLS_IS_MISSING+" INTEGER , "+
            ""+  PERSON_PHONECALLS_PERSON_ID +" INTEGER );";

    private static final String CREATE_TABLE_MESSAGES = " Create table if not exists "+TABLE_MESSAGES +
            "( "+MESSAGE_ID+" INTEGER PRIMARY KEY ,"+
            ""+  MESSAGE_WHO+" TEXT ,"+
            ""+  MESSAGE_IS_COMING+" INTEGER ,"+
            ""+  MESSAGE_DATE+" TEXT ,"+
            ""+  MESSAGE_TIME+" TEXT ,"+
            ""+  MESSAGE_BODY+" TEXT );";


    public DatabaseHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
        db.execSQL(CREATE_TABLE_PHONECALLS);
        db.execSQL(CREATE_TABLE_MESSAGES);
    }

    public void addPerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

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
        String query = "Delete from "+TABLE_PERSON + " where "+PERSON_ID+" = "+person.getID();
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void updatePerson(Person person){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

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

        String where = "id=?";
        String[] whereArgs = new String[] {String.valueOf(person.getID())};
        db.update(TABLE_PERSON, values,where,whereArgs);
        db.close();
    }

    public Person getPerson(int personID){
        Person person =null;
        ArrayList<PhoneCall> phoneCalls = new ArrayList<PhoneCall>();

        SQLiteDatabase db = this.getReadableDatabase();
        String queryPerson = "Select * from " + TABLE_PERSON + " where "+PERSON_ID+" = "+personID;


        Cursor cursorPerson = db.rawQuery(queryPerson,null);
        if(cursorPerson.moveToNext()){
            phoneCalls = getPhoneCalls(cursorPerson.getColumnIndex(   PERSON_ID));

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
                                phoneCalls
                                );
        }
        db.close();
        return person;
    }

    public Person getPerson(String phoneNumber){
        Person person =null;
        ArrayList<PhoneCall> phoneCalls = new ArrayList<PhoneCall>();

        SQLiteDatabase db = this.getReadableDatabase();
        String queryPerson = "Select * from " + TABLE_PERSON + " where '"+PERSON_MOBILE_NUMBER+"' = "+phoneNumber;

        Cursor cursorPerson = db.rawQuery(queryPerson,null);
        if(cursorPerson.moveToFirst()){
            phoneCalls = getPhoneCalls(cursorPerson.getColumnIndex(   PERSON_ID));
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
                    phoneCalls
            );

        }
        db.close();
        return person;
    }

    public ArrayList<Person> getPersons(){

        ArrayList<Person> listPersons = new ArrayList<>();
        Person person =null;

        SQLiteDatabase db = this.getReadableDatabase();
        String queryPerson = "Select * from " + TABLE_PERSON;

        Cursor cursorPerson = db.rawQuery(queryPerson,null);
        if(cursorPerson.moveToFirst()) {
            do {
                ArrayList<PhoneCall> phoneCalls = getPhoneCalls(cursorPerson.getColumnIndex(PERSON_ID));
                person = new Person(
                        cursorPerson.getInt(cursorPerson.getColumnIndex(PERSON_ID)),
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
                        phoneCalls
                );

                listPersons.add(person);
            }while (cursorPerson.moveToNext());
        }
        db.close();
        return listPersons;
    }

    public void addPhoneCalls(PhoneCall phoneCall) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PERSON_PHONECALLS_DATE, String.valueOf(phoneCall.getDate()));
        values.put(PERSON_PHONECALLS_TIME, String.valueOf(phoneCall.getTime()));
        values.put(PERSON_PHONECALLS_DURATION,phoneCall.getDuration());
        values.put(PERSON_PHONECALLS_IS_COMING, phoneCall.getIsComingCalls());
        values.put(PERSON_PHONECALLS_IS_MISSING, phoneCall.getIsMissingCalls());
        values.put(PERSON_PHONECALLS_PERSON_ID, phoneCall.getPersonID());

        db.insert(TABLE_PHONECALLS, null, values);
        db.close();

    }

    public ArrayList<PhoneCall> getAllPhoneCalls()
    {
        ArrayList<PhoneCall> phoneCalls = new ArrayList<PhoneCall>();
        PhoneCall phoneCall = null;

        SQLiteDatabase db = this.getReadableDatabase();

        String queryPhoneCalls = "Select * from " + TABLE_PHONECALLS;

        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();

        DateFormat tdf = new SimpleDateFormat("hh:mm:ss a");
        Date time = new Date();

        Cursor cursorPhoneCalls = db.rawQuery(queryPhoneCalls, null);
        if (cursorPhoneCalls.moveToFirst()) {
            do {
                try {
                    date = df.parse(cursorPhoneCalls.getString(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_DATE)));
                    time = tdf.parse(cursorPhoneCalls.getString(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_TIME)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                phoneCall = new PhoneCall(date,time,
                         cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_DURATION),
                         cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_IS_COMING) == 1,
                         cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_IS_MISSING) == 1,
                         cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_PERSON_ID)
                        );

                phoneCalls.add(phoneCall);
            } while (cursorPhoneCalls.moveToNext());
        }

        return phoneCalls;
    }

    public ArrayList<PhoneCall> getPhoneCalls(int personID)
    {
        ArrayList<PhoneCall> phoneCalls = new ArrayList<PhoneCall>();
        PhoneCall phoneCall = null;

        SQLiteDatabase db = this.getReadableDatabase();

        String queryPhoneCalls = "Select * from " + TABLE_PHONECALLS + " where " +
                PERSON_PHONECALLS_PERSON_ID + " = " + personID;

        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();

        DateFormat tdf = new SimpleDateFormat("hh:mm:ss a");
        Date time = new Date();

        Cursor cursorPhoneCalls = db.rawQuery(queryPhoneCalls, null);
        if (cursorPhoneCalls.moveToFirst()) {
            do {
                try {
                    date = df.parse(cursorPhoneCalls.getString(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_DATE)));
                    time = tdf.parse(cursorPhoneCalls.getString(cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_TIME)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                phoneCall = new PhoneCall(date,time,
                        cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_DURATION),
                        cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_IS_COMING) == 1,
                        cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_IS_MISSING) == 1,
                        cursorPhoneCalls.getColumnIndex(PERSON_PHONECALLS_PERSON_ID)
                );

                phoneCalls.add(phoneCall);
            } while (cursorPhoneCalls.moveToNext());
        }

        return phoneCalls;
    }

    public void addMessage(Message message){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MESSAGE_WHO,message.getWho());
        values.put(MESSAGE_IS_COMING,message.getIsComing() ? 1 : 0);
        values.put(MESSAGE_DATE, String.valueOf(message.getDate()));
        values.put(MESSAGE_TIME, String.valueOf(message.getTime()));
        values.put(MESSAGE_BODY,message.getBody());

        long rowId = db.insert(TABLE_MESSAGES, null, values);
        db.close();

        message.setID((int) rowId);
    }
    public ArrayList<Message> getMessages(){
        ArrayList<Message> list = new ArrayList<>();
        String query = "Select * from "+TABLE_MESSAGES;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()){

            DateFormat df = new SimpleDateFormat("MM/dd/yy");
            Date date = new Date();

            DateFormat tdf = new SimpleDateFormat("hh:mm:ss a");
            Date time = new Date();

            try {
                date = df.parse(cursor.getString(cursor.getColumnIndex(MESSAGE_DATE)));
                time = tdf.parse(cursor.getString(cursor.getColumnIndex(MESSAGE_TIME)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Message message = new Message(cursor.getInt(cursor.getColumnIndex(MESSAGE_ID)),
                    cursor.getString(cursor.getColumnIndex(MESSAGE_WHO)),
                    cursor.getColumnIndex(MESSAGE_IS_COMING) == 1,
                    date,
                    time,
                    cursor.getString(cursor.getColumnIndex(MESSAGE_BODY)));

            list.add(message);

        }
        return list;
    }
    public ArrayList<Message> getMessages(String who){
        ArrayList<Message> arrayList_messages = new ArrayList<>();
        String query = "Select * from "+TABLE_MESSAGES+" where "+MESSAGE_WHO+" = "+ who;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){

            DateFormat df = new SimpleDateFormat("MM/dd/yy");
            Date date = new Date();

            DateFormat tdf = new SimpleDateFormat("hh:mm:ss a");
            Date time = new Date();

            try {
                date = df.parse(cursor.getString(cursor.getColumnIndex(MESSAGE_DATE)));
                time = tdf.parse(cursor.getString(cursor.getColumnIndex(MESSAGE_TIME)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Message message = new Message(cursor.getInt(cursor.getColumnIndex(MESSAGE_ID)),
                    cursor.getString(cursor.getColumnIndex(MESSAGE_WHO)),
                    cursor.getColumnIndex(MESSAGE_IS_COMING) == 1,
                    date,
                    time,
                    cursor.getString(cursor.getColumnIndex(MESSAGE_BODY)));

            arrayList_messages.add(message);

        }
        return arrayList_messages;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
