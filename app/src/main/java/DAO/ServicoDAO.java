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
import basica.Usuario;

/**
 * Created by Bruno on 03/11/2017.
 */

public class ServicoDAO {

    private BancoSQL helper;
    public ServicoDAO(Context ctx) {
        helper = new BancoSQL(ctx);
    }

    @NonNull
    private ContentValues pegaDadosDoServico(Servico servico) {
        ContentValues cv = new ContentValues();
        cv.put("DESCRICAO", servico.getDescricao());
        cv.put("USUARIO_ID", servico.getUsuario().getId());
        cv.put("CATEGORIA_ID", servico.getCategoria().getId());
        cv.put("PRAZO", servico.getPrazo());
        cv.put("LONGITUDE", servico.getLongitude());
        cv.put("LATITUDE", servico.getLatitude());
        cv.put("STATUS", servico.getStatus());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        cv.put("DATA_INSERCAO", df.format(servico.getDataInsercao()));
        return cv;
    }

    private long inserir(Servico servico) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = pegaDadosDoServico(servico);
        long id = db.insert("SERVICO", null, cv);
        db.close();
        return id;
    }

    public void atualizar(Servico servico) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = pegaDadosDoServico(servico);
        String [] params = new String[]{ String.valueOf(servico.getId())};
        db.update("SERVICO",cv, "ID = ?",params);
        db.close();
    }
    public void salvar(Servico servico) {
        if (servico.getId() == 0) {
            inserir(servico);
        } else {
            atualizar(servico);
        }
    }
    public int excluir(Servico servico) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int linhasAfetadas = db.delete(
                "SERVICO",
                "ID = ?",
                new String[]{ String.valueOf(servico.getId())});
        db.close();
        return linhasAfetadas;
    }


    public void deletarTudo(){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("SERVICO", null, null);
        db.close();

    }

    public List<Servico> buscarServicos(Servico servico, Usuario usuarioLogado){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT SERVICO.ID, SERVICO.DESCRICAO, SERVICO.PRAZO, SERVICO.LONGITUDE, SERVICO.LATITUDE, SERVICO.STATUS, SERVICO.DATA_INSERCAO," +
                "USUARIO.ID AS \"ID_USUARIO\", USUARIO.NOME AS \"NOME_USUARIO\", USUARIO.EMAIL AS \"EMAIL_USUARIO\", USUARIO.CELULAR AS \"CELULAR_USUARIO\"," +
                "CATEGORIA_SERVICO.ID AS \"ID_CATEGORIA_SERVICO\", CATEGORIA_SERVICO.DESCRICAO AS \"DESCRICAO_CATEGORIA_SERVICO\", CATEGORIA_SERVICO.CAMINHO_IMAGEM AS \"CAMINHO_IMAGEM_CATEGORIA_SERVICO\" " +
                "FROM SERVICO " +
                "INNER JOIN USUARIO ON (USUARIO.ID = SERVICO.USUARIO_ID) "+
                "INNER JOIN CATEGORIA_SERVICO ON (CATEGORIA_SERVICO.ID = SERVICO.CATEGORIA_ID) "+
                "WHERE SERVICO.ID NOT IN (SELECT SERVICO_ID FROM SERVICO_USUARIO WHERE USUARIO_ID = ?) ";
        List<String> lista = new ArrayList<String>();
        lista.add(Integer.toString(usuarioLogado.getId()));
        if (servico.getDescricao() != null) {
            sql += " AND SERVICO.DESCRICAO LIKE ?";
            lista.add(servico.getDescricao());
        }
        if (servico.getDescricao() != null) {
            sql += " AND SERVICO.CATEGORIA_ID = ? ";
            lista.add(Integer.toString(servico.getCategoria().getId()));
        }
        sql += " ORDER BY SERVICO.DESCRICAO ASC";
        String[] argumentos = (String[]) lista.toArray (new String[lista.size()]);
        Cursor cursor = db.rawQuery(sql, argumentos);
        List<Servico> servicos= new ArrayList<Servico>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndex("ID"));
            String descricao = cursor.getString(
                    cursor.getColumnIndex("DESCRICAO"));
            int prazo = cursor.getInt(
                    cursor.getColumnIndex("PRAZO"));
            String longitude = cursor.getString(
                    cursor.getColumnIndex("LONGITUDE"));
            String latitude = cursor.getString(
                    cursor.getColumnIndex("LATITUDE"));
            String status = cursor.getString(
                    cursor.getColumnIndex("STATUS"));
            String dataInsercao = cursor.getString(
                    cursor.getColumnIndex("DATA_INSERCAO"));

            int idUsuario = cursor.getInt(
                    cursor.getColumnIndex("ID_USUARIO"));
            String nomeUsuario = cursor.getString(
                    cursor.getColumnIndex("NOME_USUARIO"));
            String emailUsuario = cursor.getString(
                    cursor.getColumnIndex("EMAIL_USUARIO"));
            String celularUsuario = cursor.getString(
                    cursor.getColumnIndex("CELULAR_USUARIO"));

            int idCategoria = cursor.getInt(
                    cursor.getColumnIndex("ID_CATEGORIA_SERVICO"));
            String descricaoCategoria = cursor.getString(
                    cursor.getColumnIndex("DESCRICAO_CATEGORIA_SERVICO"));

            Servico servicoCursor = new Servico();
            servicoCursor.setId(id);
            servicoCursor.setDescricao(descricao);
            servicoCursor.setPrazo(prazo);
            servicoCursor.setLongitude(longitude);
            servicoCursor.setLatitude(latitude);
            servicoCursor.setStatus(status);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;
            try {
                date = formatter.parse(dataInsercao);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            servicoCursor.setDataInsercao(date);
            servicoCursor.getUsuario().setId(idUsuario);
            servicoCursor.getUsuario().setNome(nomeUsuario);
            servicoCursor.getUsuario().setEmail(emailUsuario);
            servicoCursor.getUsuario().setCelular(celularUsuario);
            servicoCursor.getCategoria().setId(idCategoria);
            servicoCursor.getCategoria().setDescricao(descricaoCategoria);

            servicos.add(servicoCursor);
        }
        cursor.close();
        db.close();
        return servicos;
    }

    public List<Servico> buscarServicosDoUsuario(Servico servico, Usuario usuarioLogado) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT SERVICO.ID, SERVICO.DESCRICAO, SERVICO.PRAZO, SERVICO.LONGITUDE, SERVICO.LATITUDE, SERVICO.STATUS, SERVICO.DATA_INSERCAO, SERVICO.PROFISSIONAL_ID, SERVICO.VALOR_PROFISSIONAL, " +
                "CONS.ID AS \"ID_CONSUMIDOR\", CONS.NOME AS \"NOME_CONSUMIDOR\", CONS.EMAIL AS \"EMAIL_CONSUMIDOR\", CONS.CELULAR AS \"CELULAR_CONSUMIDOR\"," +
                "PROF.ID AS \"ID_PROFISSIONAL\", PROF.NOME AS \"NOME_PROFISSIONAL\", PROF.EMAIL AS \"EMAIL_PROFISSIONAL\", PROF.CELULAR AS \"CELULAR_PROFISSIONAL\"," +
                "CATEGORIA_SERVICO.ID AS \"ID_CATEGORIA_SERVICO\", CATEGORIA_SERVICO.DESCRICAO AS \"DESCRICAO_CATEGORIA_SERVICO\", CATEGORIA_SERVICO.CAMINHO_IMAGEM AS \"CAMINHO_IMAGEM_CATEGORIA_SERVICO\" " +
                "FROM SERVICO " +
                "INNER JOIN USUARIO AS CONS ON (CONS.ID = SERVICO.USUARIO_ID) "+
                "LEFT JOIN USUARIO AS PROF ON (PROF.ID = SERVICO.USUARIO_ID) "+
                "INNER JOIN CATEGORIA_SERVICO ON (CATEGORIA_SERVICO.ID = SERVICO.CATEGORIA_ID) "+
                "WHERE USUARIO_ID = ? ";
        List<String> lista = new ArrayList<String>();
        lista.add(Integer.toString(usuarioLogado.getId()));
        if (servico.getDescricao() != null) {
            sql += " AND SERVICO.DESCRICAO LIKE ?";
            lista.add(servico.getDescricao());
        }
        if (servico.getDescricao() != null) {
            sql += " AND SERVICO.CATEGORIA_ID = ? ";
            lista.add(Integer.toString(servico.getCategoria().getId()));
        }
        sql += " ORDER BY SERVICO.DESCRICAO ASC";
        String[] argumentos = (String[]) lista.toArray (new String[lista.size()]);
        Cursor cursor = db.rawQuery(sql, argumentos);
        List<Servico> servicos= new ArrayList<Servico>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndex("ID"));
            String descricao = cursor.getString(
                    cursor.getColumnIndex("DESCRICAO"));
            int prazo = cursor.getInt(
                    cursor.getColumnIndex("PRAZO"));
            String longitude = cursor.getString(
                    cursor.getColumnIndex("LONGITUDE"));
            String latitude = cursor.getString(
                    cursor.getColumnIndex("LATITUDE"));
            String status = cursor.getString(
                    cursor.getColumnIndex("STATUS"));
            String dataInsercao = cursor.getString(
                    cursor.getColumnIndex("DATA_INSERCAO"));


            int idUsuario = cursor.getInt(
                    cursor.getColumnIndex("ID_CONSUMIDOR"));
            String nomeUsuario = cursor.getString(
                    cursor.getColumnIndex("NOME_CONSUMIDOR"));
            String emailUsuario = cursor.getString(
                    cursor.getColumnIndex("EMAIL_CONSUMIDOR"));
            String celularUsuario = cursor.getString(
                    cursor.getColumnIndex("CELULAR_CONSUMIDOR"));

            int idCategoria = cursor.getInt(
                    cursor.getColumnIndex("ID_CATEGORIA_SERVICO"));
            String descricaoCategoria = cursor.getString(
                    cursor.getColumnIndex("DESCRICAO_CATEGORIA_SERVICO"));

            Servico servicoCursor = new Servico();
            servicoCursor.setId(id);
            servicoCursor.setDescricao(descricao);
            servicoCursor.setPrazo(prazo);
            servicoCursor.setLongitude(longitude);
            servicoCursor.setLatitude(latitude);
            servicoCursor.setStatus(status);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = null;
            try {
                date = formatter.parse(dataInsercao);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            servicoCursor.setDataInsercao(date);
            servicoCursor.getUsuario().setId(idUsuario);
            servicoCursor.getUsuario().setNome(nomeUsuario);
            servicoCursor.getUsuario().setEmail(emailUsuario);
            servicoCursor.getUsuario().setCelular(celularUsuario);
            servicoCursor.getCategoria().setId(idCategoria);
            servicoCursor.getCategoria().setDescricao(descricaoCategoria);

            servicos.add(servicoCursor);
        }
        cursor.close();
        db.close();
        return servicos;
    }
}
