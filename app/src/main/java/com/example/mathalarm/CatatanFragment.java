package com.example.mathalarm;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class CatatanFragment extends Fragment {
    View view;
    TextView headerBulanTahun;
    ImageView bulanKiri,bulanKanan, tambahCatatan;
    private SharedPreferences sp;
    private static final String EMAIL_KEY = "key_email";
    Context mContext;
    int year,month;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_catatan_fragment, container, false);
        mContext = getContext();
        sp = mContext.getSharedPreferences("sp",mContext.MODE_PRIVATE);
//        tvwCatatan = view.findViewById(R.id.tvwCatatan);
//        tvwCatatan.setText(sp.getString(EMAIL_KEY, null));

        headerBulanTahun = view.findViewById(R.id.tvBulanTahun);
        bulanKiri = view.findViewById(R.id.imvKiriCalender);
        bulanKanan = view.findViewById(R.id.imvKananCalendar);
        tambahCatatan = view.findViewById(R.id.TambahCatatan);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        setDateForHeader(month,year, "sekarang");

        final CompactCalendarView compactCalendarView = view.findViewById(R.id.compactcalendar_view);

        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

        bulanKiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compactCalendarView.scrollLeft();
                setDateForHeader(month,year,"kiri");
            }
        });

        bulanKanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compactCalendarView.scrollRight();
                setDateForHeader(month,year,"kanan");
            }
        });

        tambahCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,FormCatatanActivity.class));
            }
        });

        Event ev3 = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse("2019-12-19 10:10:10.100");
            ev3 = new Event(Color.BLUE, parsedDate.getTime(), "Teachers' Professional Day");
        } catch(Exception e) {
            // dateFormat.parse() could throw a ParseException
        }
        compactCalendarView.addEvent(ev3);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        Log.e("Tes OnStart","Halo");
    }

    public void setDateForHeader(int bulan, int tahun, String proses){
        if(proses.equals("kiri")){
            if(bulan == 0){
                year = tahun - 1;
                month = 11;
            }
            else{
                month = bulan - 1;
            }
        }
        else if(proses.equals("kanan")){
            if(bulan == 11){
                year = tahun + 1;
                month = 0;
            }
            else{
                month = bulan + 1;
            }
        }
        else if(proses.equals("sekarang")){
            bulan = bulan + 0;
            tahun = tahun + 0;
        }

        switch (month){
            case 0:
                headerBulanTahun.setText("JANUARI "+year);
                break;
            case 1 :
                headerBulanTahun.setText("FEBRUARI "+year);
                break;
            case 2 :
                headerBulanTahun.setText("MARET "+year);
                break;
            case 3:
                headerBulanTahun.setText("APRIL "+year);
                break;
            case 4:
                headerBulanTahun.setText("MEI "+year);
                break;
            case 5:
                headerBulanTahun.setText("JUNI "+year);
                break;
            case 6 :
                headerBulanTahun.setText("JULI "+year);
                break;
            case 7:
                headerBulanTahun.setText("AGUSTUS "+year);
                break;
            case 8:
                headerBulanTahun.setText("SEPTEMBER "+year);
                break;
            case 9:
                headerBulanTahun.setText("OKTOBER "+year);
                break;
            case 10:
                headerBulanTahun.setText("NOVEMBER "+year);
                break;
            case 11:
                headerBulanTahun.setText("DESEMBER "+year);
                break;

        }
    }
}
