package com.pab.unpar.pklmobilekelompok;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Tambah extends AppCompatActivity implements View.OnClickListener{
//    private DataManipulator dh;
    private SharedPreferences sp;
//    private int idUser;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        sp=getSharedPreferences("dataProduk",MODE_PRIVATE);
        this.sessionId = sp.getString("sessionId","");

        Button batal = (Button) findViewById(R.id.buttonBatal);
        Button simpan = (Button) findViewById(R.id.buttonSimpan);
        simpan.setOnClickListener(this);
        batal.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClick(View view){
        Intent i;
        switch (view.getId()){
            case R.id.buttonBatal:
                i = new Intent(Tambah.this, Katalog.class);
                startActivity(i);
                finish();
                break;
            case R.id.buttonSimpan:
                View inputNama = (EditText) findViewById(R.id.detailNamaProduk);
                View inputHargaPokok = (EditText) findViewById(R.id.detailHargaPokok);
                View inputHargaJual = (EditText) findViewById(R.id.detailHargaJual);

                String nama = ((TextView) inputNama).getText().toString();
                String hargaPokok = ((TextView) inputHargaPokok).getText().toString();
                String hargaJual = ((TextView) inputHargaJual).getText().toString();

//                dh = new DataManipulator(this);
//                dh.insertProduk(nama,hargaPokok,hargaJual,this.idUser);

                Soap soap = new Soap();
                boolean res = soap.setAddProduk(sessionId,nama,hargaPokok,hargaJual);
                if(res){
                    Toast.makeText(getApplicationContext(),"Produk berhasil ditambahkan!",Toast.LENGTH_SHORT).show();
                    i = new Intent(Tambah.this, Katalog.class);
                    startActivity(i);
                    finish();
                }
                break;
        }
    }
}