package com.example.mathalarm;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CatatanFragment extends Fragment {
    View view;
    TextView tvwCatatan;
    private SharedPreferences sp;
    private static final String EMAIL_KEY = "key_email";
    Context mContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_catatan_fragment, container, false);
        mContext = getContext();
        sp = mContext.getSharedPreferences("sp",mContext.MODE_PRIVATE);
        tvwCatatan = view.findViewById(R.id.tvwCatatan);
        tvwCatatan.setText(sp.getString(EMAIL_KEY, null));

        return view;
    }
}
