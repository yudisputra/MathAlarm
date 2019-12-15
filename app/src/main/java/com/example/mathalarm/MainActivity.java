package com.example.mathalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button btnAlarm, btnCatatan;
    View vwAlarm, vwCatatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlarm = findViewById(R.id.btnAlarm);
        btnCatatan = findViewById(R.id.btnNote);
        vwAlarm = findViewById(R.id.viewAlarm);
        vwCatatan = findViewById(R.id.viewCatatan);

        catatanActive();

        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               alarmActive();
            }
        });

        btnCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                catatanActive();
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.frameUtama, fragment);
        fragmentTransaction.commit();
    }

    private void alarmActive(){
        vwAlarm.setVisibility(View.VISIBLE);
        vwCatatan.setVisibility(View.INVISIBLE);
        loadFragment(new AlarmFragment());
    }

    private void catatanActive(){
        vwAlarm.setVisibility(View.INVISIBLE);
        vwCatatan.setVisibility(View.VISIBLE);
        loadFragment(new CatatanFragment());
    }

}
