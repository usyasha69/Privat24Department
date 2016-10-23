package com.example.pk.privat24department.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pk.privat24department.R;
import com.example.pk.privat24department.adapters.DepartmentsRVAdapter;
import com.example.pk.privat24department.models.DepartmentModel;
import com.example.pk.privat24department.net.Retrofit;
import com.example.pk.privat24department.utils.SharedPreferencesWorker;
import com.example.pk.privat24department.utils.Filter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DepartmentsActivity extends AppCompatActivity {
    @BindView(R.id.ad_departments)
    RecyclerView departments;
    @BindView(R.id.ad_name_filter)
    EditText nameFilter;
    @BindView(R.id.ad_city_filter)
    EditText cityFilter;

    private DepartmentsRVAdapter departmentsRVAdapter;
    private ArrayList<DepartmentModel> departmentModelsForAdapter;

    private SharedPreferencesWorker sharedPreferencesWorker;
    private Filter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        ButterKnife.bind(this);

        sharedPreferencesWorker = new SharedPreferencesWorker(this);
        filter = new Filter();

        tuneRVAndAdapter();
    }

    private void tuneRVAndAdapter() {
        departmentModelsForAdapter =
                getIntent().getParcelableArrayListExtra(SplashScreenActivity.DEPARTMENTS_INTENT_KEY);
        departmentsRVAdapter = new DepartmentsRVAdapter(this, departmentModelsForAdapter);

        departments.setLayoutManager(new LinearLayoutManager(this));
        departments.setAdapter(departmentsRVAdapter);
    }

    @OnClick(R.id.ad_add_name__filter)
    public void addNameFilter() {
        String filterField = nameFilter.getText().toString();

        if (!filterField.equals("")) {
            filter.filterByName(departmentModelsForAdapter, filterField);

            departmentsRVAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Please, enter name filter!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.ad_add_city_filter)
    public void addCityFilter() {
        String filterField = cityFilter.getText().toString();

        if (!filterField.equals("")) {
            filter.filterByCity(departmentModelsForAdapter, filterField);

            departmentsRVAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Please, enter city filter!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.ad_update_departments)
    public void updateDepartments() {
        if (sharedPreferencesWorker.isTimeToUpdate()) {
            Retrofit.getDepartments(new Callback<ArrayList<DepartmentModel>>() {
                @Override
                public void success(ArrayList<DepartmentModel> departmentModels, Response response) {
                    departmentModelsForAdapter.clear();

                    for (DepartmentModel departmentModel : departmentModels) {
                        departmentModelsForAdapter.add(departmentModel);
                    }

                    sharedPreferencesWorker.removeDepartments(departmentModelsForAdapter);
                    sharedPreferencesWorker.saveData(departmentModelsForAdapter);

                    departmentsRVAdapter.notifyDataSetChanged();
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        } else {
            Toast.makeText(this, "Day have not been slit!", Toast.LENGTH_SHORT).show();
        }
    }
}
