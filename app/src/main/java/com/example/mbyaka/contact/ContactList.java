package com.example.mbyaka.contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by YAKA on 2.1.2016.
 */
public class ContactList extends Activity {

    public ListView contactList;
    private ArrayList<String> names;
    private ArrayList<Person> personList;
    private EditText editText_search_person;
    private ArrayList<Integer> indexes;

    ArrayAdapter<String> arrayAdapter;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contact_list);

        contactList =(ListView)findViewById(R.id.listView_contact);
        editText_search_person = (EditText)findViewById(R.id.editText_search_person);

        names = new ArrayList<String>();
        personList = new ArrayList<Person>();
        databaseHelper = new DatabaseHelper(getApplicationContext());
        indexes = new ArrayList<Integer>();


        personList = ((App)getApplication()).personArrayList;

        for(int i=0;i<personList.size();i++)
        {
            names.add(personList.get(i).toString());
            indexes.add(i);
        }

        arrayAdapter=new ArrayAdapter<String>
                (this, R.layout.layout_contact_list_item, R.id.textView_contact,names);
        contactList.setAdapter(arrayAdapter);

        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent setPersonIntent = new Intent(getApplicationContext(),PersonInfo.class);
                setPersonIntent.putExtra("PERSON_POSITION",indexes.get(position));
                startActivity(setPersonIntent);
            }
        });

        editText_search_person.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ArrayList<String> foundedNames = new ArrayList<String>();

                indexes.clear();
                for(int i= 0;i<personList.size();i++)
                {
                    String listName = personList.get(i).getName();
                    String searchName = editText_search_person.getText().toString();

                    listName = listName.toLowerCase();
                    searchName = searchName.toLowerCase();
                    if(listName.contains(searchName))
                    {
                        foundedNames.add(names.get(i));
                        indexes.add(i);
                    }

                    else if(personList.get(i).getMobile_number().contains(editText_search_person.getText())
                            || personList.get(i).getHome_number().contains(editText_search_person.getText())
                            || personList.get(i).getWork_number().contains(editText_search_person.getText()))
                    {
                        foundedNames.add(names.get(i));
                        indexes.add(i);
                    }
                }

                arrayAdapter=new ArrayAdapter<String>
                        (getApplicationContext(), R.layout.layout_contact_list_item, R.id.textView_contact, foundedNames);
                contactList.setAdapter(arrayAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
