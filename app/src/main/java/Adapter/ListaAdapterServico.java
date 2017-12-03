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
import Fachada.Fachada;
import basica.Usuario;

/**
 * Created by Bruno on 04/11/2017.
 */

public class ListaAdapterServico extends ArrayAdapter<Servico> {

    Fachada fachada;
    Usuario usuarioLocalizacao;
    String categoria;

    private Context context;
    private ArrayList<Servico> lista;

    public ListaAdapterServico(Context context, ArrayList<Servico> lista) {
        super(context,0,lista);
        this.context = context;
        this.lista = lista;

        fachada = Fachada.getInstance(context);
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

        if (servicoPosicao.getCategoria().getId() == 1){
            categoria = context.getString(R.string.texto_novo_registro_01);
        } else if (servicoPosicao.getCategoria().getId() == 2){
            categoria = context.getString(R.string.texto_novo_registro_02);
        } else if (servicoPosicao.getCategoria().getId() == 3){
            categoria = context.getString(R.string.texto_novo_registro_03);
        } else if (servicoPosicao.getCategoria().getId() == 4){
            categoria = context.getString(R.string.texto_novo_registro_04);
        } else if (servicoPosicao.getCategoria().getId() == 5){
            categoria = context.getString(R.string.texto_novo_registro_05);
        }

        textViewCategoria.setText(context.getString(R.string.texto_lista_servico_01) + ": " + categoria);

        //calculaDistancia(Double.parseDouble(servicoPosicao.getLatitude()), Double.parseDouble(servicoPosicao.getLongitude()), latitude, longitude)

        TextView textViewDistancia = (TextView) convertView.findViewById(R.id.textViewServicoDistancia);
        //textViewDistancia.setText(Integer.toString(servicoPosicao.getPrazo()));
        usuarioLocalizacao = fachada.usuarioLogado();
        double distancia = distance(Double.parseDouble(servicoPosicao.getLatitude()), Double.parseDouble(servicoPosicao.getLongitude()), Double.parseDouble(usuarioLocalizacao.getLatitude()), Double.parseDouble(usuarioLocalizacao.getLongitude()), "K");
        textViewDistancia.setText(context.getString(R.string.texto_lista_servico_02) + ": "+ String.format("%.1f", distancia)  + " km");

        TextView textViewTempo = (TextView) convertView.findViewById(R.id.textViewServicoPrazo);
        Date date = new Date();
        int qtdDias = (date.compareTo(servicoPosicao.getDataInsercao()) - 1);
        String texto;
        if (qtdDias > 1) {
            texto = context.getString(R.string.texto_lista_servico_03) + " " + Integer.toString(qtdDias) + " " + context.getString(R.string.texto_lista_servico_05);
        }else{
            texto = context.getString(R.string.texto_lista_servico_04);
        }

        textViewTempo.setText(texto);

        return convertView;
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::    This function converts decimal degrees to radians                         :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::    This function converts radians to decimal degrees                         :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
