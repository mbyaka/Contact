package com.example.mbyaka.contact;

import java.util.Comparator;

/**
 * Created by YAKA on 6.1.2016.
 */
public class Comaprator implements Comparator<Person>{
    @Override
    public int compare(Person person1, Person person2) {
        String c1FullName = person1.getName() + " " + person1.getSurName();
        String c2FullName = person2.getName() + " " + person2.getSurName();
        int res = String.CASE_INSENSITIVE_ORDER.compare(c1FullName, c2FullName);
        if (res == 0) {
            res = c1FullName.compareTo(c2FullName);
        }
        return res;
    }
}