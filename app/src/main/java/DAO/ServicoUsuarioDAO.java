package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import SQL.BancoSQL;
import basica.Servico;
import basica.ServicoUsuario;
import basica.Usuario;

/**
 * Created by bruno.barbosa on 30/11/2017.
 */

public class ServicoUsuarioDAO {

    private BancoSQL helper;
    public ServicoUsuarioDAO(Context ctx) {
        helper = new BancoSQL(ctx);
    }

    @NonNull
    private ContentValues pegaDadosDoServicoUsuario(ServicoUsuario servicoUsuario) {
        ContentValues cv = new ContentValues();
        cv.put("USUARIO_ID", servicoUsuario.getUsuario().getId());
        cv.put("SERVICO_ID", servicoUsuario.getServico().getId());
        cv.put("VALOR_OFERTADO", servicoUsuario.getValorOfertado());
        cv.put("DESCRICAO", servicoUsuario.getDescricao());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        cv.put("DATA_OFERTA", df.format(servicoUsuario.getDataOferta()));
        return cv;
    }

    private long inserir(ServicoUsuario servicoUsuario) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = pegaDadosDoServicoUsuario(servicoUsuario);
        long id = db.insert("SERVICO_USUARIO", null, cv);
        db.close();
        return id;
    }

    public void salvar(ServicoUsuario servicoUsuario) {
        inserir(servicoUsuario);
    }
    public int excluir(ServicoUsuario servicoUsuario) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int linhasAfetadas = db.delete(
                "SERVICO_USUARIO",
                "USUARIO_ID = ? AND SERVICO_ID = ?",
                new String[]{ String.valueOf(servicoUsuario.getUsuario().getId()), String.valueOf(servicoUsuario.getServico().getId())});
        db.close();
        return linhasAfetadas;
    }


    public void deletarTudo(){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("SERVICO_USUARIO", null, null);
        db.close();

    }

    public List<ServicoUsuario> buscarProfInt(Servico servico) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT SERVICO_USUARIO.VALOR_OFERTADO, SERVICO_USUARIO.DATA_OFERTA, SERVICO_USUARIO.DESCRICAO, "+
                "USUARIO.ID AS \"ID_USUARIO\", USUARIO.NOME AS \"NOME_USUARIO\", USUARIO.EMAIL AS \"EMAIL_USUARIO\", USUARIO.CELULAR AS \"CELULAR_USUARIO\" " +
                "FROM SERVICO_USUARIO " +
                "LEFT JOIN USUARIO ON (USUARIO.ID = SERVICO_USUARIO.USUARIO_ID) ";
        //"WHERE USUARIO_ID = ? ";
        List<String> lista = new ArrayList<String>();
        if (servico.getId() > 0) {
            sql += " AND SERVICO_USUARIO.SERVICO_ID = ?";
            lista.add(Integer.toString(servico.getId()));
        }
        sql += " ORDER BY SERVICO_USUARIO.DATA_OFERTA";
        String[] argumentos = (String[]) lista.toArray (new String[lista.size()]);
        Cursor cursor = db.rawQuery(sql, argumentos);
        List<ServicoUsuario> servicoUsuarios= new ArrayList<ServicoUsuario>();
        while (cursor.moveToNext()) {
            int idUsuario = cursor.getInt(
                    cursor.getColumnIndex("ID_USUARIO"));
            String nomeUsuario = cursor.getString(
                    cursor.getColumnIndex("NOME_USUARIO"));
            String emailUsuario = cursor.getString(
                    cursor.getColumnIndex("EMAIL_USUARIO"));
            String celularUsuario = cursor.getString(
                    cursor.getColumnIndex("CELULAR_USUARIO"));

            double valorOfertado = cursor.getDouble(
                    cursor.getColumnIndex("VALOR_OFERTADO"));
            String descricao = cursor.getString(
                    cursor.getColumnIndex("DESCRICAO"));
            String dataOfertado = cursor.getString(
                    cursor.getColumnIndex("DATA_OFERTA"));

            ServicoUsuario servicoUsuarioCursor = new ServicoUsuario();

            servicoUsuarioCursor.setServico(servico);
            servicoUsuarioCursor.setDescricao(descricao);
            servicoUsuarioCursor.setValorOfertado(valorOfertado);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;
            try {
                date = formatter.parse(dataOfertado);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            servicoUsuarioCursor.setDataOferta(date);
            servicoUsuarioCursor.getUsuario().setId(idUsuario);
            servicoUsuarioCursor.getUsuario().setNome(nomeUsuario);
            servicoUsuarioCursor.getUsuario().setEmail(emailUsuario);
            servicoUsuarioCursor.getUsuario().setCelular(celularUsuario);


            servicoUsuarios.add(servicoUsuarioCursor);
        }
        cursor.close();
        db.close();
        return servicoUsuarios;
    }
}
