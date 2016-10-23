package com.example.pk.privat24department.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pk.privat24department.R;
import com.example.pk.privat24department.models.DepartmentModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DepartmentsRVAdapter extends RecyclerView.Adapter<DepartmentsRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DepartmentModel> departmentModels;

    public DepartmentsRVAdapter(Context context, ArrayList<DepartmentModel> departmentModels) {
        this.context = context;
        this.departmentModels = departmentModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View root = layoutInflater.inflate(R.layout.departments_rv_item, parent, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(departmentModels.get(position).name);
        holder.state.setText(departmentModels.get(position).state);
        holder.id.setText(departmentModels.get(position).id);
        holder.country.setText(departmentModels.get(position).country);
        holder.city.setText(departmentModels.get(position).city);
        holder.index.setText(departmentModels.get(position).index);
        holder.phone.setText(departmentModels.get(position).phone);
        holder.address.setText(departmentModels.get(position).address);
    }

    @Override
    public int getItemCount() {
        return departmentModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.drvi_name)
        TextView name;
        @BindView(R.id.drvi_state)
        TextView state;
        @BindView(R.id.drvi_id)
        TextView id;
        @BindView(R.id.drvi_country)
        TextView country;
        @BindView(R.id.drvi_city)
        TextView city;
        @BindView(R.id.drvi_index)
        TextView index;
        @BindView(R.id.drvi_phone)
        TextView phone;
        @BindView(R.id.drvi_address)
        TextView address;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
