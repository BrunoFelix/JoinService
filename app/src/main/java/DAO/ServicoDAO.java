package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import SQL.BancoSQL;
import basica.Servico;

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
        cv.put("LONGITUDE", servico.getLatitude());
        cv.put("LATITUDE", servico.getLongitude());
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

    public List<Servico> buscarServico(Servico servico) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM SERVICO WHERE ID > 0";
        List<String> lista = new ArrayList<String>();
        if (servico.getDescricao() != null) {
            sql += " AND DESCRICAO LIKE ?";
            lista.add(servico.getDescricao());
        }
        if (servico.getDescricao() != null) {
            sql += " AND CATEGORIA_ID = ? ";
            lista.add(Integer.toString(servico.getCategoria().getId()));
        }
        sql += " ORDER BY DESCRICAO ASC";
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
            int usuarioId = cursor.getInt(
                    cursor.getColumnIndex("USUARIO_ID"));
            int categoriaId = cursor.getInt(
                    cursor.getColumnIndex("CATEGORIA_ID"));
            Servico servicoCursor = new Servico();
            servicoCursor.setId(id);
            servicoCursor.setDescricao(descricao);
            servicoCursor.setPrazo(prazo);
            servicoCursor.setLongitude(longitude);
            servicoCursor.setLatitude(latitude);
            servicoCursor.getUsuario().setId(usuarioId);
            servicoCursor.getCategoria().setId(categoriaId);

            servicos.add(servicoCursor);
        }
        cursor.close();
        db.close();
        return servicos;
    }
}
