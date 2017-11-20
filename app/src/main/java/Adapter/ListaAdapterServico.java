package Adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.joinservice.joinservice.R;
import com.joinservice.joinservice.maps.MapsFragment;

import java.util.ArrayList;
import java.util.Date;

import basica.Servico;

/**
 * Created by Bruno on 04/11/2017.
 */

public class ListaAdapterServico extends ArrayAdapter<Servico> {

    private Context context;
    private ArrayList<Servico> lista;

    public ListaAdapterServico(Context context, ArrayList<Servico> lista) {
        super(context,0,lista);
        this.context = context;
        this.lista = lista;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Servico servicoPosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.servico, null);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewServico);
        //imageView.setImageResource(servicoPosicao.get);

        TextView textViewDescricao = (TextView) convertView.findViewById(R.id.textViewServicoDescricao);
        textViewDescricao.setText(servicoPosicao.getDescricao().toString());

        TextView textViewCategoria = (TextView) convertView.findViewById(R.id.textViewServicoCategoria);
        textViewCategoria.setText("Categoria: " + servicoPosicao.getCategoria().getDescricao());

        //calculaDistancia(Double.parseDouble(servicoPosicao.getLatitude()), Double.parseDouble(servicoPosicao.getLongitude()), latitude, longitude)

        TextView textViewDistancia = (TextView) convertView.findViewById(R.id.textViewServicoDistancia);
        //textViewDistancia.setText(Integer.toString(servicoPosicao.getPrazo()));
        textViewDistancia.setText("Distância: \"Em desenvolvimento\" km");

        TextView textViewTempo = (TextView) convertView.findViewById(R.id.textViewServicoPrazo);
        Date date = new Date();
        int qtdDias = (date.compareTo(servicoPosicao.getDataInsercao()) - 1);
        String texto;
        if (qtdDias > 1) {
            texto = "Postado há " + Integer.toString(qtdDias) + " dias atrás";
        }else{
            texto = "Postado hoje";
        }

        textViewTempo.setText(texto);

        return convertView;
    }

    private double calculaDistancia(double lat1, double lng1, double lat2, double lng2) {
        //double earthRadius = 3958.75;//miles
        double earthRadius = 6371;//kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        return dist * 1000; //em metros
    }
}
