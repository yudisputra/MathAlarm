package com.example.mathalarm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class FormAlarmActivity extends AppCompatActivity {
    ImageView back;
    EditText subjek;
    TimePicker timePicker;
    Button simpanAlarm;
    Context mContext;
    Date dt;
    Calendar c;
    private SharedPreferences sp;
    private static final String EMAIL_KEY = "key_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_alarm);

        back = findViewById(R.id.backFormAlarm);
        subjek = findViewById(R.id.edtSubjekAlarm);
        simpanAlarm = findViewById(R.id.btnSimpanAlarm);
        timePicker = findViewById(R.id.alarmTimePicker);
        mContext = this;
        sp = mContext.getSharedPreferences("sp",mContext.MODE_PRIVATE);

        timePicker.setIs24HourView(true);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        simpanAlarm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                String isiSubjek = subjek.getText().toString();
                int jamAlarm = timePicker.getHour();
                int menitAlarm = timePicker.getMinute();

                c = Calendar.getInstance();
                int jamsekarang = c.get(Calendar.HOUR_OF_DAY);
                int menitsekarang = c.get(Calendar.MINUTE);

                if(jamAlarm < jamsekarang){
                    dt = new Date();
                    Calendar cd = Calendar.getInstance();
                    cd.setTime(dt);
                    cd.add(Calendar.DATE, 1);
                    cd.set(Calendar.HOUR_OF_DAY,jamAlarm);
                    cd.set(Calendar.MINUTE,menitAlarm);
                    dt = cd.getTime();
//                    Log.e("If ke","1");
                    printBedaWaktu(c.getTime(),cd.getTime());
                }
                else if( jamAlarm==jamsekarang && menitAlarm < menitsekarang){
                    dt = new Date();
                    Calendar cd = Calendar.getInstance();
                    cd.setTime(dt);
                    cd.add(Calendar.DATE, 1);
                    cd.set(Calendar.HOUR_OF_DAY,jamAlarm);
                    cd.set(Calendar.MINUTE,menitAlarm);
                    dt = cd.getTime();
//                    Log.e("If ke","2");
                    printBedaWaktu(c.getTime(),cd.getTime());
                }
                else{
                    dt = new Date();
                    Calendar cd = Calendar.getInstance();
                    cd.setTime(dt);
                    cd.set(Calendar.HOUR_OF_DAY,jamAlarm);
                    cd.set(Calendar.MINUTE,menitAlarm);
                    dt = cd.getTime();
//                    Log.e("If ke","3");
                    printBedaWaktu(c.getTime(),cd.getTime());
                }

//                Log.e("tes tanggal",""+dt);
                String email = sp.getString(EMAIL_KEY, null);
                String tanggaljamalarm = dt.toString();
                String subjek = isiSubjek;
                String status = "Hidup";
            }
        });
    }

    public void printBedaWaktu(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

//        Log.e("startdate :",""+startDate);
//        Log.e("enddate :",""+endDate);
//        Log.e("different :",""+different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        if(elapsedHours == 0){
            Toast.makeText(mContext,"Alarm Di Set " + elapsedMinutes + " menit dari sekarang",Toast.LENGTH_LONG).show();
        }
        else if(elapsedMinutes == 0){
            Toast.makeText(mContext,"Alarm Di Set " + elapsedHours + " jam dari sekarang",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(mContext,"Alarm Di Set " + elapsedHours + " jam " + elapsedMinutes + " menit dari sekarang",Toast.LENGTH_LONG).show();
        }

//        Log.e("Hasil :","" + elapsedDays+ elapsedHours+ elapsedMinutes+ elapsedSeconds);
    }
}
