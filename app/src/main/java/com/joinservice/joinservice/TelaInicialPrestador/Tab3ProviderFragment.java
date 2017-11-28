package com.joinservice.joinservice.TelaInicialPrestador;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joinservice.joinservice.R;

public class Tab3ProviderFragment extends Fragment {

    private static final String TAG = "Tab3ProviderFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3,container,false);
        
        return view;
    }
}
