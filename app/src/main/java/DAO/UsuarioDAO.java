package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import SQL.UsuarioSQL;
import basica.Usuario;

/**
 * Created by Bruno on 25/10/2017.
 */

public class UsuarioDAO {

    private UsuarioSQL helper;
    public UsuarioDAO(Context ctx) {
        helper = new UsuarioSQL(ctx);
    }

    private long inserir(Usuario usuario) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NOME", usuario.getNome());
        cv.put("SENHA", usuario.getSenha());
        cv.put("EMAIL", usuario.getEmail());
        cv.put("CELULAR", usuario.getCelular());
        long id = db.insert("USUARIO", null, cv);
        db.close();
        return id;
    }
    private int atualizar(Usuario usuario) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NOME", usuario.getNome());
        cv.put("SENHA", usuario.getSenha());
        cv.put("EMAIL", usuario.getEmail());
        cv.put("CELULAR", usuario.getCelular());
        int linhasAfetadas = db.update(
                "USUARIO",
                cv,
                "ID = ?",
                new String[]{ String.valueOf(usuario.getId())});
        db.close();
        return linhasAfetadas;
    }
    public void salvar(Usuario usuario) {
        if (usuario.getId() == 0) {
            inserir(usuario);
        } else {
            atualizar(usuario);
        }
    }
    public int excluir(Usuario usuario) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int linhasAfetadas = db.delete(
                "USUARIO",
                "ID = ?",
                new String[]{ String.valueOf(usuario.getId())});
        db.close();
        return linhasAfetadas;
    }


    public void deletarTudo(){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("USUARIO", null, null);
        db.close();

    }

    public Usuario logar(String email, String senha){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM USUARIO ";
        String[] argumentos = null;

        sql += " WHERE EMAIL = ? AND SENHA = ?";
        argumentos = new String[]{email, senha};
        Cursor cursor = db.rawQuery(sql, argumentos);
        Usuario usuario = new Usuario();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndex("ID"));
            String nome = cursor.getString(
                    cursor.getColumnIndex("NOME"));
            String celular = cursor.getString(
                    cursor.getColumnIndex("CELULAR"));
            String em = cursor.getString(
                    cursor.getColumnIndex("EMAIL"));

            usuario.setId(id);
            usuario.setNome(nome);
            usuario.setEmail(em);
            usuario.setCelular(celular);
        }
        return usuario;
    }


    public List<Usuario> buscarUsuario(String filtro) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM USUARIO ";
        String[] argumentos = null;
        if (filtro != null) {
            sql += " WHERE NOME LIKE ?";
            argumentos = new String[]{ filtro };
        }
        sql += " ORDER BY NOME ASC";
        Cursor cursor = db.rawQuery(sql, argumentos);
        List<Usuario> alunos = new ArrayList<Usuario>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndex("ID"));
            String nome = cursor.getString(
                    cursor.getColumnIndex("NOME"));
            String celular = cursor.getString(
                    cursor.getColumnIndex("CELULAR"));
            String email = cursor.getString(
                    cursor.getColumnIndex("EMAIL"));
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setCelular(celular);

            alunos.add(usuario);
        }
        cursor.close();
        db.close();
        return alunos;
    }
}
