package SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bruno on 03/11/2017.
 */

public class BancoSQL extends SQLiteOpenHelper{

    ContentValues cv;

    public BancoSQL(Context context) {
        super(context, "dbJoinService4", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE USUARIO (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "NOME TEXT NOT NULL, "+
                        "EMAIL TEXT NOT NULL, "+
                        "SENHA TEXT NOT NULL, "+
                        "CELULAR TEXT)");

        sqLiteDatabase.execSQL(
                "CREATE TABLE SERVICO (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "DESCRICAO TEXT NOT NULL, "+
                        "PRAZO INTEGER NOT NULL, "+
                        "LONGITUDE TEXT NOT NULL, "+
                        "LATITUDE TEXT NOT NULL, "+
                        "USUARIO_ID INTEGER NOT NULL," +
                        "CATEGORIA_ID INTEGER NOT NULL,"+
                        "STATUS TEXT NOT NULL,"+
                        "DATA_INSERCAO TEXT NOT NULL,"+
                        "FOREIGN KEY(USUARIO_ID) REFERENCES USUARIO(ID)," +
                        "FOREIGN KEY(CATEGORIA_ID) REFERENCES CATEGORIA(ID))");

        sqLiteDatabase.execSQL(
                "CREATE TABLE CATEGORIA_SERVICO (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "DESCRICAO TEXT NOT NULL, "+
                        "CAMINHO_IMAGEM TEXT)");

        sqLiteDatabase.execSQL(
                "CREATE TABLE USUARIO_LOGADO ( " +
                        "ID INTEGER PRIMARY KEY," +
                        "LONGITUDE TEXT, "+
                        "LATITUDE TEXT, "+
                        "TIPO TEXT NOT NULL)");

        sqLiteDatabase.execSQL(
                "CREATE TABLE SERVICO_USUARIO ( " +
                        "USUARIO_ID INTEGER NOT NULL," +
                        "SERVICO_ID INTEGER NOT NULL," +
                        "VALOR_OFERTADO REAL NOT NULL," +
                        "DATA_OFERTA TEXT NOT NULL," +
                        "DESCRICAO TEXT," +
                        "PRIMARY KEY (USUARIO_ID, SERVICO_ID))");

        //INSERT USUÁRIO ADMINISTRADOR
        cv = new ContentValues();
        cv.put("NOME", "A");
        cv.put("SENHA","A");
        cv.put("EMAIL", "A");
        cv.put("CELULAR", "81995782171");
        sqLiteDatabase.insert("USUARIO", null, cv);

        //INSERT DE 5 CATEGORIAS DE SERVIÇO
        cv = new ContentValues();
        cv.put("DESCRICAO", "Celulares e Telefones");
        sqLiteDatabase.insert("CATEGORIA_SERVICO", null, cv);

        cv = new ContentValues();
        cv.put("DESCRICAO", "Eletrodomésticos");
        sqLiteDatabase.insert("CATEGORIA_SERVICO", null, cv);

        cv = new ContentValues();
        cv.put("DESCRICAO", "Móveis e Decoração");
        sqLiteDatabase.insert("CATEGORIA_SERVICO", null, cv);

        cv = new ContentValues();
        cv.put("DESCRICAO", "Administrativos");
        sqLiteDatabase.insert("CATEGORIA_SERVICO", null, cv);

        cv = new ContentValues();
        cv.put("DESCRICAO", "Reformas");
        sqLiteDatabase.insert("CATEGORIA_SERVICO", null, cv);

        //INSERT USUÁRIO SERVICO
        cv = new ContentValues();
        cv.put("DESCRICAO", "CONSERTO DE CHUVEIRO ELÉTRICO");
        cv.put("PRAZO","2 SEMANAS");
        cv.put("LONGITUDE", "60");
        cv.put("LATITUDE", "30");
        cv.put("USUARIO_ID", "1");
        cv.put("CATEGORIA_ID", "1");
        sqLiteDatabase.insert("SERVICO", null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch(oldVersion) {
            case 1:
                //upgrade logic from version 1 to 2;
            case 2:
                //upgrade logic from version 2 to 3;
            case 3:
                //upgrade logic from version 3 to 4;
            case 4:
                //upgrade logic from version 4 to 5
            case 5:
                //upgrade logic from version 5 to 6
            case 6:
                //upgrade logic from version 6 to 7
                break;
            default:
                throw new IllegalStateException(
                        "onUpgrade() with unknown oldVersion " + oldVersion);
        }
    }
}
