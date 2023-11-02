package com.example.testworkmanager;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderData> {

    List<Data> dataList = new ArrayList<>();
    Listener listener;

    public Adapter(Listener listener) {
        this.listener = listener;
    }

    void addInformation(List<Data> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    void deleteItem() {
        this.dataList.clear();
        notifyDataSetChanged();
    }

    void updateData(Data data) {
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).getId() == data.getId()) {
                dataList.set(i, data);
                notifyItemChanged(i);
            }
        }
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

        ConstraintLayout background_item;
        TextView tv_UUID, tv_status, tv_time;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);

            tv_status = itemView.findViewById(R.id.tv_status);
            tv_UUID = itemView.findViewById(R.id.tv_UUID);
            tv_time = itemView.findViewById(R.id.tv_time);
            background_item = itemView.findViewById(R.id.background_item);
        }

        void bind(Data data) {
            tv_UUID.setText(data.getUUID().toString());
            tv_status.setText(data.getStatus());
            tv_time.setText(data.getTimeNew());

            itemView.setOnClickListener(view -> listener.cancelWork(data, data.getStatus()));
        }
    }

    interface Listener {
        void cancelWork(Data data, String status);
    }
}
