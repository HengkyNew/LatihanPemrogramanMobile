package com.example.latihan8020190053;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbSaya extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Databarang.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME          = "barang";
    private static final String COLUMN_ID           = "_id";
    private static final String COLUMN_NAMA         = "nama_barang";
    private static final String COLUMN_JENIS        = "jenis_barang";
    private static final String COLUMN_JUMLAH       = "jumlah_barang";

    public DbSaya(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    void tambahdata(String nama, String jenis, int jumlah){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAMA, nama);
        cv.put(COLUMN_JENIS, jenis);
        cv.put(COLUMN_JUMLAH, jumlah);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Data Ditambahkan", Toast.LENGTH_SHORT).show();
        }
    }

    void ubahDataBarang(String _id, String nama, String jenis, String jumlah) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues databrg = new ContentValues();
        databrg.put(COLUMN_NAMA, nama);
        databrg.put(COLUMN_JENIS, jenis);
        databrg.put(COLUMN_JUMLAH, jumlah);

        long hasil = db.update(TABLE_NAME, databrg, "_id=?", new String[]{_id});

        if (hasil == -1) {
            Toast.makeText(context, "Ada Gangguan Berhasil di Ubah !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil di Ubah !", Toast.LENGTH_SHORT).show();
        }
    }

    void hapusData(String row_id){

        SQLiteDatabase dbKita = this.getWritableDatabase();
        long result = dbKita.delete(TABLE_NAME, "_id=?", new String[]{row_id});

        if (result == -1) {
            Toast.makeText(context, "Gagal Delete !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Delete !", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAMA + " TEXT, " +
                    COLUMN_JENIS + " TEXT, " +
                    COLUMN_JUMLAH + " INTEGER " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    Cursor bacaSemuaData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor datasaya = null;
        if(db != null){
            datasaya = db.rawQuery(query, null);
        }
        return datasaya;
    }
}
