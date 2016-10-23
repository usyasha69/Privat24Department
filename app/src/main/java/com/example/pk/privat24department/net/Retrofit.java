package com.example.pk.privat24department.net;

import com.example.pk.privat24department.models.DepartmentModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

public class Retrofit {
    private static final String BASE_URL_PRIVAT_24_API = "https://api.privatbank.ua";
    private static DepartmentApi departmentApi;

    static {
        initialize();
    }

    private interface DepartmentApi {
        @GET("/p24api/pboffice?json")
        void getDepartments(Callback<ArrayList<DepartmentModel>> callback);
    }

    private static void initialize() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL_PRIVAT_24_API)
                .build();

        departmentApi = restAdapter.create(DepartmentApi.class);
    }

    public static void getDepartments(Callback<ArrayList<DepartmentModel>> callback) {
        departmentApi.getDepartments(callback);
    }
}
