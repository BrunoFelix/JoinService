package com.joinservice.joinservice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by tj on 06/11/17.
 */

public class MyServicesFragment extends Fragment {

    // armazenar variáveis de instância
    private String title;
    private int page;

    //armazena variáveis de instância com base nos argumentos passados
    public static MyServicesFragment newInstance(int page, String title) {
        MyServicesFragment fragment = new MyServicesFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 1);
        title = getArguments().getString("someTitle");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup contaier, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_list_order_consumer, contaier, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
        tvLabel.setText(/*page + " -- " +*/ title);
        return view;
    }

}
