package com.example.mathalarm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathalarm.Class.Alarm;
import com.example.mathalarm.R;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.MyViewHolder> {

    List<Alarm> alarmList;

    public AlarmAdapter(List<Alarm> alarmList) {
        this.alarmList = alarmList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View menuView = layoutInflater.inflate(R.layout.card_alarm, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(menuView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Alarm alarm = alarmList.get(position);
        holder.tvalarm.setText(alarm.getJam());
        holder.tvsubjek.setText(alarm.getSubjek());
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvsubjek,tvalarm;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvalarm = itemView.findViewById(R.id.jamAlarm);
            tvsubjek = itemView.findViewById(R.id.jamSubjek);
        }
    }
}
