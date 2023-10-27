package com.example.testworkmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderData> {

    List<Data> dataList = new ArrayList<>();

    public void addInformations(List<Data> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderData(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        TextView tv_UUID, tv_status, tv_time;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_UUID = itemView.findViewById(R.id.tv_UUID);
            tv_time = itemView.findViewById(R.id.tv_time);
        }

        void bind(Data data) {
            tv_UUID.setText(data.getUUID().toString());
            tv_status.setText(data.getStatus());
            tv_time.setText(data.getTimeNew());
        }
    }
}
