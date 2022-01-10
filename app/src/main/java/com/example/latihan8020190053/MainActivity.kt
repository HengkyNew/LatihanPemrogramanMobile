package com.example.latihan8020190053

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    val barang_id: ArrayList<String>       = arrayListOf()
    val barang_nama: ArrayList<String>     = arrayListOf()
    val barang_jenis: ArrayList<String>    = arrayListOf()
    val barang_jumlah: ArrayList<String>   = arrayListOf()

    fun SimpanDataQuery(){
        val db = DbSaya(this)
        val dataSaya:Cursor = db.bacaSemuaData()

        if(dataSaya.count == 0) {
            Toast.makeText(this,"Data Barang Tidak Ada ! ", Toast.LENGTH_SHORT).show()
        }
        else{
            while(dataSaya.moveToNext()){
                barang_id.add(dataSaya.getString(0))
                barang_nama.add(dataSaya.getString(1))
                barang_jenis.add(dataSaya.getString(2))
                barang_jumlah.add(dataSaya.getString(3))
            }
        }
    }

    fun simpanDataDiArray(){
        val db = DbSaya(this)
        val dataSaya: Cursor = db.bacaSemuaData()

        if(dataSaya.count == 0) {
            Toast.makeText(this,"Data Barang Tidak Ada ! ", Toast.LENGTH_SHORT).show()
        }
        else{
            while(dataSaya.moveToNext()){
                barang_id.add(dataSaya.getString(0))
                barang_nama.add(dataSaya.getString(1))
                barang_jenis.add(dataSaya.getString(2))
                barang_jumlah.add(dataSaya.getString(3))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tombol = findViewById<FloatingActionButton>(R.id.add)
        tombol.setOnClickListener {
            val tambah = Intent(this, TambahData::class.java)
            startActivity(tambah)
        }

        simpanDataDiArray()

        val rv_data = findViewById<RecyclerView>(R.id.rv_data)

        val Adapter = AdapterViewBarang(this, barang_id, barang_nama, barang_jenis, barang_jumlah)
        rv_data.adapter = Adapter
        rv_data.layoutManager = LinearLayoutManager(this)
        rv_data.itemAnimator = DefaultItemAnimator()
    }
}