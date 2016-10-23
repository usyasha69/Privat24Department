package com.example.pk.privat24department.utils;


import com.example.pk.privat24department.models.DepartmentModel;

import java.util.ArrayList;

public class Filter {

    public void filterByName(ArrayList<DepartmentModel> departmentModels
            , String nameFilter) {
        ArrayList<DepartmentModel> result = new ArrayList<>();

        for (int i = 0; i < departmentModels.size(); i++) {
            if (departmentModels.get(i).name.contains(nameFilter)) {
                result.add(departmentModels.get(i));
            }
        }

        departmentModels.clear();

        for (DepartmentModel departmentModel : result) {
            departmentModels.add(departmentModel);
        }
    }

    public void filterByCity(ArrayList<DepartmentModel> departmentModels
            , String cityFilter) {
        ArrayList<DepartmentModel> result = new ArrayList<>();

        for (int i = 0; i < departmentModels.size(); i++) {
            if (departmentModels.get(i).city.contains(cityFilter)) {
                result.add(departmentModels.get(i));
            }
        }

        departmentModels.clear();

        for (DepartmentModel departmentModel : result) {
            departmentModels.add(departmentModel);
        }
    }
}
