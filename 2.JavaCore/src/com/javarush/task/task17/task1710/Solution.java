package com.javarush.task.task17.task1710;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/*
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] s) throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        if (s[0].startsWith("-c")) {
            if (s[2].equals("м")) {
                allPeople.add(Person.createMale(s[1], dateFormat.parse(s[3])));
            } else if (s[2].equals("ж")) {
                allPeople.add(Person.createFemale(s[1], dateFormat.parse(s[3])));
            }
            System.out.println(allPeople.size() - 1);

        }

        if (s[0].startsWith("-u")) {
            if (s[3].equals("м")) {
                allPeople.add(Integer.parseInt(s[1]), Person.createMale(s[2], dateFormat.parse(s[4])));
            } else if (s[3].equals("ж")) {
                allPeople.add(Integer.parseInt(s[1]), Person.createFemale(s[2], dateFormat.parse(s[4])));
            }
            allPeople.remove(Integer.parseInt(s[1]) + 1);
        }

        if (s[0].startsWith("-d")) {
            allPeople.get(Integer.parseInt(s[1])).setBirthDay(null);
            allPeople.get(Integer.parseInt(s[1])).setName(null);
            allPeople.get(Integer.parseInt(s[1])).setSex(null);
        }

        if (s[0].startsWith("-i")) {
            System.out.println(allPeople.get(Integer.parseInt(s[1])));
        }
    }
}
