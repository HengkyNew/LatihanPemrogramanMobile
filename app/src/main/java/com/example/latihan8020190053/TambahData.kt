package com.example.latihan8020190053

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class TambahData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_data)

        val nama = findViewById<EditText>(R.id.txtnama)
        val jenis = findViewById<EditText>(R.id.txtjenis)
        val jumlah = findViewById<EditText>(R.id.txtjumlah)
        val simpan = findViewById<Button>(R.id.btnadd)

        simpan.setOnClickListener {
            val db = DbSaya (this)
            db.tambahdata(
                nama.text.toString().trim(),
                jenis.text.toString().trim(),
                Integer.valueOf(jumlah.text.toString().trim())
            )
        }
    }
}