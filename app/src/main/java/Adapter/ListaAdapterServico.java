package Adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.joinservice.joinservice.R;

import java.util.ArrayList;

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
        textViewCategoria.setText("Categoria: Informática");

        TextView textViewDistancia = (TextView) convertView.findViewById(R.id.textViewServicoDistancia);
        //textViewDistancia.setText(Integer.toString(servicoPosicao.getPrazo()));
        textViewDistancia.setText("Distância: 3,2km");

        TextView textViewTempo = (TextView) convertView.findViewById(R.id.textViewServicoPrazo);
        textViewTempo.setText("Postado a 3 dias atrás");

        return convertView;
    }
}
