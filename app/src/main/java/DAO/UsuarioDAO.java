package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import SQL.BancoSQL;
import basica.Usuario;

/**
 * Created by Bruno on 25/10/2017.
 */

public class UsuarioDAO {

    private BancoSQL helper;
    public UsuarioDAO(Context ctx) {
        helper = new BancoSQL(ctx);
    }

    @NonNull
    private ContentValues pegaDadosDoUsuario(Usuario usuario) {
        ContentValues cv = new ContentValues();
        cv.put("NOME", usuario.getNome());
        cv.put("SENHA", usuario.getSenha());
        cv.put("EMAIL", usuario.getEmail());
        cv.put("CELULAR", usuario.getCelular());
        return cv;
    }

    private long inserir(Usuario usuario) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = pegaDadosDoUsuario(usuario);
        long id = db.insert("USUARIO", null, cv);
        db.close();
        return id;
    }

    public void atualizar(Usuario usuario) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = pegaDadosDoUsuario(usuario);
        String [] params = new String[]{ String.valueOf(usuario.getId())};
        db.update("USUARIO",cv, "ID = ?",params);
        db.close();
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

    public void excluirLogado() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("USUARIO_LOGADO", null, null);
        db.close();
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
            usuario.setTipo("Consumidor");
        }

        atualizarUsuarioLogado(usuario);

        return usuario;
    }

    public void atualizarUsuarioLogado(Usuario usuario){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ID", usuario.getId());
        cv.put("TIPO", usuario.getTipo());
        long id = db.insert("USUARIO_LOGADO", null, cv);
        db.close();
    }

    public Usuario Logado(){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM USUARIO_LOGADO ";
        String[] argumentos = null;
        Cursor cursor = db.rawQuery(sql, argumentos);
        Usuario usuario = new Usuario();

        while (cursor.moveToNext()) {
            int idUsuario = cursor.getInt(
                    cursor.getColumnIndex("ID"));
            String tipo = cursor.getString(
                    cursor.getColumnIndex("TIPO"));

            SQLiteDatabase db2 = helper.getReadableDatabase();
            String sql2 = "SELECT * FROM USUARIO ";
            sql2 += " WHERE ID = ?";
            String[] argumentos2 = {Integer.toString(idUsuario)};
            Cursor cursor2 = db2.rawQuery(sql2, argumentos2);

            while (cursor2.moveToNext()) {
                int id = cursor2.getInt(
                        cursor2.getColumnIndex("ID"));
                String nome = cursor2.getString(
                        cursor2.getColumnIndex("NOME"));
                String celular = cursor2.getString(
                        cursor2.getColumnIndex("CELULAR"));
                String em = cursor2.getString(
                        cursor2.getColumnIndex("EMAIL"));

                usuario.setId(id);
                usuario.setNome(nome);
                usuario.setEmail(em);
                usuario.setCelular(celular);
                usuario.setTipo(tipo);
            }
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
        List<Usuario> usuarios= new ArrayList<Usuario>();
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

            usuarios.add(usuario);
        }
        cursor.close();
        db.close();
        return usuarios;
    }
}
