package com.example.pk.privat24department.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pk.privat24department.R;
import com.example.pk.privat24department.models.DepartmentModel;
import com.example.pk.privat24department.net.Retrofit;
import com.example.pk.privat24department.utils.SharedPreferencesWorker;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashScreenActivity extends AppCompatActivity {
    public static final String DEPARTMENTS_INTENT_KEY = "departments";

    private SharedPreferencesWorker sharedPreferencesWorker;
    private ArrayList<DepartmentModel> departmentModelsForIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPreferencesWorker = new SharedPreferencesWorker(this);

        if (!sharedPreferencesWorker.containsKey()) {
            Retrofit.getDepartments(new Callback<ArrayList<DepartmentModel>>() {
                @Override
                public void success(ArrayList<DepartmentModel> departmentModels, Response response) {
                    departmentModelsForIntent = departmentModels;
                    sharedPreferencesWorker.removeDepartments(departmentModelsForIntent);

                    sharedPreferencesWorker.saveData(departmentModelsForIntent);

                    startActivity(new Intent(SplashScreenActivity.this, DepartmentsActivity.class)
                            .putExtra(DEPARTMENTS_INTENT_KEY, departmentModelsForIntent));
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        } else {
            departmentModelsForIntent = sharedPreferencesWorker.readData();

            startActivity(new Intent(SplashScreenActivity.this, DepartmentsActivity.class)
                    .putExtra(DEPARTMENTS_INTENT_KEY, departmentModelsForIntent));
        }
    }
}
