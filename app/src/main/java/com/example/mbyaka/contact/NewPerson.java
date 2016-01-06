package com.example.mbyaka.contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by YAKA on 6.1.2016.
 */
public class NewPerson extends Activity {

    private EditText editText_new_person_name;
    private EditText editText_new_person_surname;
    private EditText editText_new_person_mobile_phone;
    private EditText editText_new_person_work_phone;
    private EditText editText_new_person_home_phone;
    private EditText editText_new_person_email;
    private EditText editText_new_person_location;
    private TextView textView_new_person_numberOfReceivedMessage;
    private Button btn_new_person_cancel;
    private Button btn_new_person_save;

    DatabaseHelper databaseHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_new_person);

        String newPersonPhoneNumber = getIntent().getExtras().getString("PHONE_NUMBER");

        databaseHelper = new DatabaseHelper(getApplicationContext());
        initComponents();


        editText_new_person_mobile_phone.setText(newPersonPhoneNumber);

        btn_new_person_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.exit(0);
            }
        });

        btn_new_person_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Person newPerson = new Person();
                newPerson.setName(editText_new_person_name.getText().toString());
                newPerson.setSurName(editText_new_person_surname.getText().toString());
                newPerson.setMobile_number(editText_new_person_mobile_phone.getText().toString());
                newPerson.setHome_number(editText_new_person_home_phone.getText().toString());
                newPerson.setWork_number(editText_new_person_work_phone.getText().toString());
                newPerson.seteMail(editText_new_person_email.getText().toString());
                newPerson.setLocation(editText_new_person_location.getText().toString());

                databaseHelper.addPerson(newPerson);
                ((App)getApplication()).personArrayList.add(newPerson);

                Intent personInfoIntent = new Intent(getApplicationContext(),PersonInfo.class);
                personInfoIntent.putExtra("PERSON_ID",newPerson.getID());
                startActivity(personInfoIntent);
                System.exit(0);

            }
        });

    }

    private void initComponents()
    {
        editText_new_person_name = (EditText)findViewById(R.id.editText_new_person_name);
        editText_new_person_surname = (EditText)findViewById(R.id.editText_new_person_surname);
        editText_new_person_mobile_phone = (EditText)findViewById(R.id.editText_new_person_mobile_phone);
        editText_new_person_work_phone = (EditText)findViewById(R.id.editText_new_person_work_phone);
        editText_new_person_home_phone = (EditText)findViewById(R.id.editText_new_person_home_phone);
        editText_new_person_email = (EditText)findViewById(R.id.editText_new_person_email);
        editText_new_person_location = (EditText)findViewById(R.id.editText_new_person_location);
        btn_new_person_cancel = (Button)findViewById(R.id.btn_new_person_cancel);
        btn_new_person_save = (Button)findViewById(R.id.btn_new_person_save);
    }
    
}
