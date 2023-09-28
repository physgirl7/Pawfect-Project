package org.launchcode.Pawfect.Harmony.models;

import java.util.ArrayList;
import java.util.HashMap;

public class UserData {

    public static ArrayList<User> findByColumnAndValue(String column, String value, Iterable<User> allUsers) {

        ArrayList<User> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<User>) allUsers;
        }

        if (column.equals("all")){
            results = findByValue(value, allUsers);
            return results;
        }
        for (User user : allUsers) {

            String aValue = getFieldValue(user, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                results.add(user);
            }
        }

        return results;
    }

    public static String getFieldValue(User user, String fieldName){
        String theValue;
        if (fieldName.equals("name")){
            theValue = user.getFirstName() +" " + user.getLastName();
        } else {
            theValue = user.getLocation();
        }
        return theValue;
    }

    public static ArrayList<User> findByValue(String value, Iterable<User> allUsers) {
        String lower_val = value.toLowerCase();

        ArrayList<User> results = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getFirstName().toLowerCase().contains(lower_val)) {
                results.add(user);
            } else if (user.getLastName().toLowerCase().contains(lower_val)) {
                results.add(user);
            }else if (user.getLocation().toLowerCase().contains(lower_val)) {
                results.add(user);
            }
        }
        return results;
    }
}






