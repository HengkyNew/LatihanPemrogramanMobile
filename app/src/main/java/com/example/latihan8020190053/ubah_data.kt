package com.example.latihan8020190053

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ubah_data : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_data)

        val btnUbah = findViewById<Button>(R.id.btnubah)
        val actionBar = supportActionBar
        if(intent.hasExtra("judul")){
            actionBar?.title = intent.getStringExtra("judul")
        }

        getIntentData()

        btnUbah.setOnClickListener {
            val db = DbSaya(this)
            val _id = intent.getStringExtra("id")
            val namabrg = findViewById<EditText>(R.id.txtnama2).text.toString()
            val jenisbrg = findViewById<EditText>(R.id.txtjenis2).text.toString()
            val jumlahbrg = findViewById<EditText>(R.id.txtjumlah2).text.toString()
            db.ubahDataBarang(_id, namabrg, jenisbrg, jumlahbrg)
        }
        val btnhapus = findViewById<Button>(R.id.btnhapus)
        btnhapus.setOnClickListener {
            konfirmasi()
        }
    }

    fun getIntentData() {
        if (
            intent.hasExtra("id") &&
            intent.hasExtra("nama") &&
            intent.hasExtra("jenis") &&
            intent.hasExtra("jumlah")
        )  {

            val _id         = intent.getStringExtra("id")
            val nama        = intent.getStringExtra("nama")
            val jenis       = intent.getStringExtra("jenis")
            val jumlah      = intent.getStringExtra("jumlah")

            val txtnama2    = findViewById<EditText>(R.id.txtnama2)
            val txtjenis2   = findViewById<EditText>(R.id.txtjenis2)
            val txtjumlah2  = findViewById<EditText>(R.id.txtjumlah2)

            txtnama2.setText(nama)
            txtjenis2.setText(jenis)
            txtjumlah2.setText(jumlah)

        } else {
            Toast.makeText(this, "Tidak Ada Data !", Toast.LENGTH_SHORT).show()
        }
    }
    fun konfirmasi(){
        val idbrg = intent.getStringExtra("id")
        val namabrg = intent.getStringExtra("nama")

        val alert = AlertDialog.Builder(this)
        alert.setTitle("Delete " + namabrg + " ?")
        alert.setMessage("Apakah Anda Yakin Ingin Menghapus " + namabrg + " ?")

        alert.setPositiveButton("Iya", { dialog, which ->
            val dbKita = DbSaya(this)
            dbKita.hapusData(idbrg)
                startActivity(Intent(this, MainActivity::class.java))
        })
        alert.setNegativeButton("Tidak", { dialog, which -> })
        alert.create().show()
    }
}