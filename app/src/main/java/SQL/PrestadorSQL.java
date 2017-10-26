package SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TJ on 26/10/2017.
 */

public class PrestadorSQL extends SQLiteOpenHelper {

    public PrestadorSQL(Context context) {
        super(context, "dbPrestador", null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE PRESTADOR (" +
                        "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "NOME TEXT NOT NULL, " +
                        "EMAIL TEXT NOT NULL, " +
                        "SENHA TEXT NOT NULL, " +
                        "CELULAR TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch(oldVersion) {
            case 1:
                ContentValues cv = new ContentValues();
                cv.put("NOME", "ADMIN");
                cv.put("SENHA","ADMIN");
                cv.put("EMAIL", "ADMIN");
                cv.put("CELULAR", "81995782171");
                db.insert("PRESTADOR", null, cv);
            case 2:
                //upgrade logic from version 2 to 3
            case 3:
                //upgrade logic from version 3 to 4
                break;
            default:
                throw new IllegalStateException(
                        "onUpgrade() with unknown oldVersion " + oldVersion);
        }
    }

}
