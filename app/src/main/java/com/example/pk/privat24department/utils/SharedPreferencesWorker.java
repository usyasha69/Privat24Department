package com.example.pk.privat24department.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pk.privat24department.models.DepartmentModel;

import java.util.ArrayList;

public class SharedPreferencesWorker {
    public static final String DEPARTMENTS_SHARED_PREFERENCES = "departments shared preferences";
    public static final String SP_NAME_KEY = "name";
    public static final String SP_STATE_KEY = "state";
    public static final String SP_ID_KEY = "id";
    public static final String SP_COUNTRY_KEY = "country";
    public static final String SP_CITY_KEY = "city";
    public static final String SP_INDEX_KEY = "index";
    public static final String SP_PHONE_KEY = "phone";
    public static final String SP_ADDRESS_KEY = "address";
    public static final String SP_UPDATE_TIME = "update time";

    private Context context;

    public SharedPreferencesWorker(Context context) {
        this.context = context;
    }

    public void saveData(ArrayList<DepartmentModel> departmentModels) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DEPARTMENTS_SHARED_PREFERENCES
                , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (int i = 0; i < departmentModels.size(); i++) {
            editor.putString(SP_NAME_KEY + i, departmentModels.get(i).name);
            editor.putString(SP_STATE_KEY + i, departmentModels.get(i).state);
            editor.putString(SP_ID_KEY + i, departmentModels.get(i).id);
            editor.putString(SP_COUNTRY_KEY + i, departmentModels.get(i).country);
            editor.putString(SP_CITY_KEY + i, departmentModels.get(i).city);
            editor.putString(SP_INDEX_KEY + i, departmentModels.get(i).index);
            editor.putString(SP_PHONE_KEY + i, departmentModels.get(i).phone);
            editor.putString(SP_ADDRESS_KEY + i, departmentModels.get(i).address);

            editor.putLong(SP_UPDATE_TIME, System.currentTimeMillis());
        }

        editor.apply();
    }

    public ArrayList<DepartmentModel> readData() {
        ArrayList<DepartmentModel> departmentModels = new ArrayList<>();

        SharedPreferences sharedPreferences = context.getSharedPreferences(DEPARTMENTS_SHARED_PREFERENCES
                , Context.MODE_PRIVATE);
        final int SHARED_PREFERENCES_SIZE = sharedPreferences.getAll().size();

        for (int i = 0; i < SHARED_PREFERENCES_SIZE; i++) {
            departmentModels.add(
                    new DepartmentModel(sharedPreferences.getString(SP_NAME_KEY + i, "Unknown")
                            , sharedPreferences.getString(SP_STATE_KEY + i, "Unknown")
                            , sharedPreferences.getString(SP_ID_KEY + i, "Unknown")
                            , sharedPreferences.getString(SP_COUNTRY_KEY + i, "Unknown")
                            , sharedPreferences.getString(SP_CITY_KEY + i, "Unknown")
                            , sharedPreferences.getString(SP_INDEX_KEY + i, "Unknown")
                            , sharedPreferences.getString(SP_PHONE_KEY + i, "Unknown")
                            , sharedPreferences.getString(SP_ADDRESS_KEY + i, "Unknown")));
        }

        return departmentModels;
    }

    public boolean containsKey() {
        boolean result = true;

        SharedPreferences sharedPreferences = context.getSharedPreferences(DEPARTMENTS_SHARED_PREFERENCES
                , Context.MODE_PRIVATE);

        if (!sharedPreferences.contains(SP_NAME_KEY + "0")
                || !sharedPreferences.contains(SP_STATE_KEY + "0")
                || !sharedPreferences.contains(SP_ID_KEY + "0")
                || !sharedPreferences.contains(SP_COUNTRY_KEY + "0")
                || !sharedPreferences.contains(SP_CITY_KEY + "0")
                || !sharedPreferences.contains(SP_INDEX_KEY + "0")
                || !sharedPreferences.contains(SP_PHONE_KEY + "0")
                || !sharedPreferences.contains(SP_ADDRESS_KEY + "0")) {
            result = false;
        }

        return result;
    }

    public boolean isTimeToUpdate() {
        final int DAY = 86400000;

        SharedPreferences sharedPreferences = context.getSharedPreferences(DEPARTMENTS_SHARED_PREFERENCES
                , Context.MODE_PRIVATE);

        return (System.currentTimeMillis() - sharedPreferences.getLong(SP_UPDATE_TIME, 0L) >= DAY);
    }

    public void removeDepartments(ArrayList<DepartmentModel> departmentModels) {
        final int DEPARTMENTS_NUMBER = 200;
        final int REMOVE_DEPARTMENTS = departmentModels.size() - DEPARTMENTS_NUMBER;

        for (int i = 0; i < REMOVE_DEPARTMENTS; i++) {
            int randomIndex = (int) (Math.random() * departmentModels.size());

            departmentModels.remove(randomIndex);
        }
    }
}
