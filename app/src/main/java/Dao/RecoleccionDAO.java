package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Model.RecoleccionModel;

public class RecoleccionDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EcolimDB";
    private static final int DATABASE_VERSION = 1;

    public RecoleccionDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS Recoleccion (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "organico REAL," +
                "plastico REAL," +
                "vidrio REAL," +
                "metal REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Recoleccion");
        onCreate(db);
    }

    // Insertar recolección
    public void insertCollection(RecoleccionModel r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("organico", r.getOrganico());
        cv.put("plastico", r.getPlastico());
        cv.put("vidrio", r.getVidrio());
        cv.put("metal", r.getMetal());
        db.insert("Recoleccion", null, cv);
        db.close();
    }

    // Obtener la última recolección
    public RecoleccionModel getLastCollection() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                "Recoleccion",
                null,
                null,
                null,
                null,
                null,
                "id DESC",
                "1"
        );

        RecoleccionModel recoleccion = null;
        if (cursor.moveToFirst()) {
            recoleccion = new RecoleccionModel(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("organico")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("plastico")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("vidrio")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("metal"))
            );
        }
        cursor.close();
        db.close();
        return recoleccion;
    }
}
