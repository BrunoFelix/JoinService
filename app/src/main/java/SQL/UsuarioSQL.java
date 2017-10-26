package SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bruno on 21/10/2017.
 */

public class UsuarioSQL extends SQLiteOpenHelper{

    public UsuarioSQL(Context context) {
        super(context, "dbAluno", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE ALUNO (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "NOME TEXT NOT NULL, "+
                        "EMAIL TEXT NOT NULL, "+
                        "SENHA TEXT NOT NULL, "+
                        "CELULAR TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
