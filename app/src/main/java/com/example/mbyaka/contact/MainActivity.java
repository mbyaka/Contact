package com.example.mbyaka.contact;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    Button btn_new_person;
    Button btn_number_1;
    Button btn_number_2;
    Button btn_number_3;
    Button btn_number_4;
    Button btn_number_5;
    Button btn_number_6;
    Button btn_number_7;
    Button btn_number_8;
    Button btn_number_9;
    Button btn_number_0;
    Button btn_number_star;
    Button btn_number_sharp;
    Button btn_number_call;
    Button btn_number_message;
    Button btn_number_back;
    Button btn_contact_list;
    Button btn_all_messages;

    TextView textView_showNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        btn_number_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView_showNumber.getText().length() > 0) {
                    textView_showNumber.setText(textView_showNumber.getText().subSequence(0, textView_showNumber.getText().length() - 1));
                }

            }
        });

        btn_number_back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                textView_showNumber.setText("");
                return true;
            }
        });
        btn_number_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!textView_showNumber.getText().equals("")) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + textView_showNumber.getText().toString()));
                    startActivity(callIntent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Geçersiz Numara",Toast.LENGTH_SHORT).show();
                }
            }

        });

        btn_number_0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(textView_showNumber.getText().length() != 13)
                {
                    textView_showNumber.setText(textView_showNumber.getText().toString() + "+");
                    return true;
                }
                return false;
            }
        });

        btn_number_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent msgIntent = new Intent(getApplicationContext(),SendSms.class);
                msgIntent.putExtra("Number",textView_showNumber.getText().toString());
                startActivity(msgIntent);
            }
        });

        btn_contact_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ContactList.class);
                startActivity(intent);
            }
        });

        btn_new_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(btn_new_person.getText().equals("Yeni Kişi")) {
                    Intent newPersonIntent = new Intent(getApplicationContext(), NewPerson.class);
                    newPersonIntent.putExtra("PHONE_NUMBER", textView_showNumber.getText().toString());
                    startActivity(newPersonIntent);
                }
                else {
                    textView_showNumber.setText(btn_new_person.getText().toString().split("\n")[1]);
                }
            }
        });

        btn_all_messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allSmsIntetn = new Intent(getApplicationContext(), SmsList.class);
                startActivity(allSmsIntetn);
            }
        });

        textView_showNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<Person> personList;
                personList = ((App)getApplication()).personArrayList;

                if(s.length() !=0) {
                    btn_new_person.setVisibility(View.VISIBLE);
                    int i = 0;
                    while (i < personList.size()
                            && !personList.get(i).getMobile_number().contains(s)
                            && !personList.get(i).getHome_number().contains(s)
                            && !personList.get(i).getWork_number().contains(s)) {
                        i++;
                    }

                    if (i != personList.size()) {
                        btn_new_person.setText(personList.get(i).toString() + "\n" + personList.get(i).getMobile_number());
                    } else {
                        btn_new_person.setText("Yeni Kişi");
                    }
                }
                else {
                    btn_new_person.setVisibility(View.GONE);
                    btn_new_person.setText("");
                    btn_new_person.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initComponents() {
        btn_new_person = (Button)findViewById(R.id.btn_new_person);
        btn_number_1 = (Button)findViewById(R.id.btn_number_1);
        btn_number_2 = (Button)findViewById(R.id.btn_number_2);
        btn_number_3 = (Button)findViewById(R.id.btn_number_3);
        btn_number_4 = (Button)findViewById(R.id.btn_number_4);
        btn_number_5 = (Button)findViewById(R.id.btn_number_5);
        btn_number_6 = (Button)findViewById(R.id.btn_number_6);
        btn_number_7 = (Button)findViewById(R.id.btn_number_7);
        btn_number_8 = (Button)findViewById(R.id.btn_number_8);
        btn_number_9 = (Button)findViewById(R.id.btn_number_9);
        btn_number_0 = (Button)findViewById(R.id.btn_number_0);
        btn_number_star = (Button)findViewById(R.id.btn_number_star);
        btn_number_sharp = (Button)findViewById(R.id.btn_number_sharp);
        btn_number_call = (Button)findViewById(R.id.btn_number_call);
        btn_number_message = (Button)findViewById(R.id.btn_number_message);
        btn_number_back = (Button)findViewById(R.id.btn_number_back);
        btn_contact_list = (Button)findViewById(R.id.btn_contact_list);
        btn_all_messages = (Button)findViewById(R.id.btn_all_messages);

        textView_showNumber = (TextView)findViewById(R.id.textView_showNumber);
    }

    public void numberOnClick(View view) {

        if(textView_showNumber.getText().length() != 13)
        {
            textView_showNumber.setText(textView_showNumber.getText().toString() + ((Button)view).getText().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
