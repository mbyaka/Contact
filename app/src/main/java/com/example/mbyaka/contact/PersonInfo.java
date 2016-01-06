package com.example.mbyaka.contact;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by YAKA on 4.1.2016.
 */
public class PersonInfo extends Activity{

    private EditText editText_person_name;
    private EditText editText_person_surname;
    private EditText editText_person_mobile_phone;
    private EditText editText_person_work_phone;
    private EditText editText_person_home_phone;
    private EditText editText_person_email;
    private EditText editText_person_location;
    private TextView textView_person_totalDurationInComingCall;
    private TextView textView_person_totalDurationOutGoingCall;
    private TextView textView_person_numberOfMissingCall;
    private TextView textView_person_numberOfSentMessage;
    private TextView textView_person_numberOfReceivedMessage;
    private Button btn_person_phoneCalls;
    private Button btn_person_messages;
    private Button btn_person_send_message;
    private Button btn_person_calls;
    private Button btn_person_edit;
    private LinearLayout linearLayout_person_info_details;


    private Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_person_info);

        initComponents();

        person = null;
        final DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        final int personID = getIntent().getExtras().getInt("PERSON_ID");

        person = ((App)getApplication()).personArrayList.get(personID-1); //person = databaseHelper.getPerson(personID);

        setInfoComponents(person);

        btn_person_calls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_person_calls.getText().equals("Ara")) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + person.getMobile_number()));
                    startActivity(callIntent);
                }
                else if(btn_person_calls.getText().equals("Kişiyi Sil"))
                {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PersonInfo.this);
                    alertDialogBuilder.setMessage("Bu kişi silinecek. Devam edilsin mi?");

                    alertDialogBuilder.setPositiveButton("Evet",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            databaseHelper.removePerson(person);
                            ((App)getApplication()).personArrayList.remove(person.getID()-1);
                            Intent contactListIntent = new Intent(getApplicationContext(),ContactList.class);
                            startActivity(contactListIntent);
                            dialog.dismiss();
                            System.exit(0);
                        }
                    });

                    alertDialogBuilder.setNegativeButton("Hayır",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alertDialogBuilder.show();
                }
            }
        });

        btn_person_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_person_send_message.getText().equals("Mesaj")) {
                    Intent msgIntent = new Intent(getApplicationContext(), SendSms.class);
                    msgIntent.putExtra("Number", person.getMobile_number());
                    startActivity(msgIntent);
                }
                else if(btn_person_send_message.getText().equals("İptal")) {
                    editText_person_name.setEnabled(false);
                    editText_person_surname.setEnabled(false);
                    editText_person_mobile_phone.setEnabled(false);
                    editText_person_home_phone.setEnabled(false);
                    editText_person_work_phone.setEnabled(false);
                    editText_person_email.setEnabled(false);
                    editText_person_location.setEnabled(false);

                    btn_person_edit.setText("Düzenle");
                    btn_person_send_message.setText("Mesaj");
                    btn_person_calls.setText("Ara");
                    btn_person_phoneCalls.setVisibility(View.VISIBLE);
                    btn_person_messages.setVisibility(View.VISIBLE);
                    linearLayout_person_info_details.setVisibility(View.VISIBLE);

                }
            }
        });
        btn_person_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_person_edit.getText().equals("Düzenle")) {
                    editText_person_name.setEnabled(true);
                    editText_person_surname.setEnabled(true);
                    editText_person_mobile_phone.setEnabled(true);
                    editText_person_home_phone.setEnabled(true);
                    editText_person_work_phone.setEnabled(true);
                    editText_person_email.setEnabled(true);
                    editText_person_location.setEnabled(true);

                    btn_person_edit.setText("Kaydet");
                    btn_person_send_message.setText("İptal");
                    btn_person_calls.setText("Kişiyi Sil");
                    btn_person_phoneCalls.setVisibility(View.INVISIBLE);
                    btn_person_messages.setVisibility(View.INVISIBLE);
                    linearLayout_person_info_details.setVisibility(View.INVISIBLE);

                }
                else if(btn_person_edit.getText().equals("Kaydet"))
                {
                    editText_person_name.setEnabled(false);
                    editText_person_surname.setEnabled(false);
                    editText_person_mobile_phone.setEnabled(false);
                    editText_person_home_phone.setEnabled(false);
                    editText_person_work_phone.setEnabled(false);
                    editText_person_email.setEnabled(false);
                    editText_person_location.setEnabled(false);

                    btn_person_edit.setText("Düzenle");
                    btn_person_send_message.setText("Mesaj");
                    btn_person_calls.setText("Ara");
                    btn_person_phoneCalls.setVisibility(View.VISIBLE);
                    btn_person_messages.setVisibility(View.VISIBLE);
                    linearLayout_person_info_details.setVisibility(View.VISIBLE);


                    Person editPerson = person;
                    editPerson.setName(editText_person_name.getText().toString());
                    editPerson.setSurName(editText_person_surname.getText().toString());
                    editPerson.setMobile_number(editText_person_mobile_phone.getText().toString());
                    editPerson.setHome_number(editText_person_home_phone.getText().toString());
                    editPerson.setWork_number(editText_person_work_phone.getText().toString());
                    editPerson.seteMail(editText_person_email.getText().toString());
                    editPerson.setLocation(editText_person_location.getText().toString());

                    databaseHelper.updatePerson(editPerson);
                    ((App)getApplication()).personArrayList.set(personID-1,editPerson);

                }

            }
        });

        btn_person_messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mesaj kısmı yazılınca devam et

                Toast.makeText(getApplicationContext(),"Yapım Aşamasında!",Toast.LENGTH_SHORT).show();
            }
        });

        btn_person_phoneCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // arama detaylarıyla deva ete

                Toast.makeText(getApplicationContext(),"Yapım Aşamasında!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setInfoComponents(Person person)
    {
        editText_person_name.setText(person.getName());
        editText_person_surname.setText(person.getSurName());
        editText_person_mobile_phone.setText(person.getMobile_number());
        editText_person_work_phone.setText(person.getWork_number());
        editText_person_home_phone.setText(person.getHome_number());
        editText_person_email.setText(person.geteMail());
        editText_person_location.setText(person.getLocation());
        textView_person_totalDurationInComingCall.setText(textView_person_totalDurationInComingCall.getText().toString() +
                person.getTotalDurationInComingCall());
        textView_person_totalDurationOutGoingCall.setText(textView_person_totalDurationOutGoingCall.getText().toString() +
                person.getTotalDurationOutGoingCall());
        textView_person_numberOfMissingCall.setText(textView_person_numberOfMissingCall.getText().toString() +
                person.getNumberOfMissingCall());
        textView_person_numberOfSentMessage.setText(textView_person_numberOfSentMessage.getText().toString() +
                person.getNumberOfSentMessage());
        textView_person_numberOfReceivedMessage.setText(textView_person_numberOfReceivedMessage.getText().toString() +
                person.getNumberOfReceivedMessage());
    }

    private void initComponents()
    {
        editText_person_name = (EditText)findViewById(R.id.editText_person_name);
        editText_person_surname = (EditText)findViewById(R.id.editText_person_surname);
        editText_person_mobile_phone = (EditText)findViewById(R.id.editText_person_mobile_phone);
        editText_person_work_phone = (EditText)findViewById(R.id.editText_person_work_phone);
        editText_person_home_phone = (EditText)findViewById(R.id.editText_person_home_phone);
        editText_person_email = (EditText)findViewById(R.id.editText_person_email);
        editText_person_location = (EditText)findViewById(R.id.editText_person_location);
        textView_person_totalDurationInComingCall = (TextView)findViewById(R.id.textView_person_totalDurationInComingCall);
        textView_person_totalDurationOutGoingCall = (TextView)findViewById(R.id.textView_person_totalDurationOutGoingCall);
        textView_person_numberOfMissingCall = (TextView)findViewById(R.id.textView_person_numberOfMissingCall);
        textView_person_numberOfSentMessage = (TextView)findViewById(R.id.textView_person_numberOfSentMessage);
        textView_person_numberOfReceivedMessage = (TextView)findViewById(R.id.textView_person_numberOfReceivedMessage);
        btn_person_messages = (Button)findViewById(R.id.btn_person_messages);
        btn_person_phoneCalls = (Button)findViewById(R.id.btn_person_phoneCalls);
        btn_person_send_message = (Button)findViewById(R.id.btn_person_send_message);
        btn_person_calls = (Button)findViewById(R.id.btn_person_call);
        btn_person_edit = (Button)findViewById(R.id.btn_person_edit);

        linearLayout_person_info_details = (LinearLayout)findViewById(R.id.linearLayout_person_info_details);
    }
}
