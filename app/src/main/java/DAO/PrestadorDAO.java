package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import SQL.PrestadorSQL;
import SQL.UsuarioSQL;
import basica.Prestador;
import basica.Usuario;

/**
 * Created by TJ on 26/10/2017.
 */

public class PrestadorDAO {

    private PrestadorSQL helper;

    public PrestadorDAO(Context ctx) {
        helper = new PrestadorSQL(ctx);
    }

    private long inserir(Prestador prestador) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NOME", prestador.getNome());
        cv.put("SENHA", prestador.getSenha());
        cv.put("EMAIL", prestador.getEmail());
        cv.put("CELULAR", prestador.getCelular());
        long id = db.insert("PRESTADOR", null, cv);
        db.close();
        return id;
    }

    private int atualizar(Prestador prestador) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NOME", prestador.getNome());
        cv.put("SENHA", prestador.getSenha());
        cv.put("EMAIL", prestador.getEmail());
        cv.put("CELULAR", prestador.getCelular());
        int linhasAfetadas = db.update(
                "PRESTADOR",
                cv,
                "ID = ?",
                new String[]{String.valueOf(prestador.getId())});
        db.close();
        return linhasAfetadas;
    }

    public void salvar(Prestador prestador) {
        if (prestador.getId() == 0) {
            inserir(prestador);
        } else {
            atualizar(prestador);
        }
    }

    public int excluir(Prestador prestador) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int linhasAfetadas = db.delete(
                "PRESTADOR",
                "ID = ?",
                new String[]{ String.valueOf(prestador.getId())});
        db.close();
        return linhasAfetadas;
    }

    public void deletarTudo(){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("PRESTADOR", null, null);
        db.close();
    }

    public Prestador logar(String email, String senha){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM PRESTADOR";
        String[] argumentos = null;

        sql += " WHERE EMAIL = ? AND SENHA = ?";
        argumentos = new String[]{email, senha};
        Cursor cursor = db.rawQuery(sql, argumentos);
        Prestador prestador = new Prestador();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndex("ID"));
            String nome = cursor.getString(
                    cursor.getColumnIndex("NOME"));
            String celular = cursor.getString(
                    cursor.getColumnIndex("CELULAR"));
            String em = cursor.getString(
                    cursor.getColumnIndex("EMAIL"));

            prestador.setId(id);
            prestador.setNome(nome);
            prestador.setEmail(em);
            prestador.setCelular(celular);
        }
        return prestador;
    }

    public List<Prestador> buscarPrestador(String filtro) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM PRESTADOR";
        String[] argumentos = null;
        if (filtro != null) {
            sql += " WHERE NOME LIKE ?";
            argumentos = new String[]{ filtro };
        }
        sql += " ORDER BY NOME ASC";
        Cursor cursor = db.rawQuery(sql, argumentos);
        List<Prestador> prestadores = new ArrayList<Prestador>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndex("ID"));
            String nome = cursor.getString(
                    cursor.getColumnIndex("NOME"));
            String celular = cursor.getString(
                    cursor.getColumnIndex("CELULAR"));
            String email = cursor.getString(
                    cursor.getColumnIndex("EMAIL"));
            Prestador prestador = new Prestador();
            prestador.setId(id);
            prestador.setNome(nome);
            prestador.setEmail(email);
            prestador.setCelular(celular);

            prestadores.add(prestador);
        }
        cursor.close();
        db.close();
        return prestadores;
    }
}