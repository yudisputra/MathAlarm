package com.example.mathalarm;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mathalarm.Adapter.AlarmAdapter;
import com.example.mathalarm.Class.Alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_alarm_fragment, container, false);

        List<Alarm> alarmList = new ArrayList(2);
        Alarm alarm1 = new Alarm("Alarm 1","06.00");
        Alarm alarm2 = new Alarm("Alarm 2", "07.00");

        alarmList.add(alarm1);
        alarmList.add(alarm2);


        recyclerView= view.findViewById(R.id.rvAlarm);
        layoutManager = new LinearLayoutManager(view.getContext());
        AlarmAdapter menuAdapter = new AlarmAdapter(alarmList);
        recyclerView.setAdapter(menuAdapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }
}

