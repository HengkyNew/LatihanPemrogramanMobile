package com.example.latihan8020190053;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterViewBarang extends RecyclerView.Adapter<AdapterViewBarang.ViewHolderSaya> {
    private Context context;
    private ArrayList barang_id, barang_nama, barang_jenis, barang_jumlah;

    AdapterViewBarang(
            Context context,
            ArrayList barang_id,
            ArrayList barang_nama,
            ArrayList barang_jenis,
            ArrayList barang_jumlah
    ) {
        this.context        = context;
        this.barang_id      = barang_id;
        this.barang_nama    = barang_nama;
        this.barang_jenis   = barang_jenis;
        this.barang_jumlah  = barang_jumlah;
    }

    @NonNull
    @Override
    public ViewHolderSaya onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflaterKita = LayoutInflater.from(context);
        View view = inflaterKita.inflate(R.layout.view_barang_hengky_wijaya_8020190053, parent, false);
        return new ViewHolderSaya(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSaya holder, @SuppressLint("RecyclerView") int position) {

        holder.txt_id.setText(String.valueOf(barang_id.get(position)));
        holder.txt_nama.setText(String.valueOf(barang_nama.get(position)));
        holder.txt_jenis.setText(String.valueOf(barang_jenis.get(position)));
        holder.txt_jumlah.setText(String.valueOf(barang_jumlah.get(position)));
        holder.LayoutUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentkita = new Intent(context, ubah_data.class);
                intentkita.putExtra("id", String.valueOf(barang_id.get(position)));
                intentkita.putExtra("nama", String.valueOf(barang_nama.get(position)));
                intentkita.putExtra("jenis", String.valueOf(barang_jenis.get(position)));
                intentkita.putExtra("jumlah", String.valueOf(barang_jumlah.get(position)));
                context.startActivity(intentkita);
            }
        });
    }

    @Override
    public int getItemCount() {
        return barang_id.size();
    }

    public class ViewHolderSaya extends RecyclerView.ViewHolder {

        TextView txt_id, txt_nama, txt_jenis, txt_jumlah;
        LinearLayout LayoutUtama;

        public ViewHolderSaya(@NonNull View itemView) {
            super(itemView);

            txt_id = itemView.findViewById(R.id.txtid);
            txt_nama = itemView.findViewById(R.id.txt_nama);
            txt_jenis = itemView.findViewById(R.id.txt_jenis);
            txt_jumlah = itemView.findViewById(R.id.txt_jumlah);
            LayoutUtama = itemView.findViewById(R.id.layout_utama);
        }
    }
}
