package com.pab.unpar.pklmobilekelompok;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Jual extends AppCompatActivity implements View.OnClickListener{
//    private DataManipulator dh;
    private SharedPreferences sp;
    static final int DIALOG_ID = 0;

    private String namaProduk;
    private String hargaProduk;
    private String kuantitas;
    private String totalHarga;

    private String sessionId;
    private String[] produk;
    private Soap soap = new Soap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jual);
        sp=getSharedPreferences("dataProduk",MODE_PRIVATE);
        sessionId = sp.getString("sessionId","");

        Button batal = (Button) findViewById(R.id.buttonBatal);
        batal.setOnClickListener(this);
        Button proses = (Button) findViewById(R.id.buttonProses);
        proses.setOnClickListener(this);

        viewDetail();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void viewDetail(){
//        dh = new DataManipulator(this);
//        String[] list = dh.select1FromProduk(sp.getInt("idProduk",-1));
        produk = soap.getDetailProduk(sessionId,sp.getString("produk",""));

        if(produk.length>0 && produk!=null) {
            EditText nama = (EditText) findViewById(R.id.jualNamaProduk);
            EditText hargaJual = (EditText) findViewById(R.id.jualHargaJual);
            nama.setText(produk[0]);
            hargaJual.setText(produk[2]);
        }
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.buttonBatal:
                i = new Intent(Jual.this, Transaksi.class);
                startActivity(i);
                break;
            case R.id.buttonProses:
                View kuantitasJual = (View) findViewById(R.id.jualKuantitas);
                View nama = (View) findViewById(R.id.jualNamaProduk);
                View hargaJual = (View) findViewById(R.id.jualHargaJual);

                this.namaProduk = ((EditText) nama).getText().toString();
                this.kuantitas = ((EditText) kuantitasJual).getText().toString();
                this.hargaProduk = ((EditText) hargaJual).getText().toString();
                this.totalHarga = (Integer.parseInt(kuantitas)*Integer.parseInt(hargaProduk))+"";

//                dh = new DataManipulator(this);

                showDialog(DIALOG_ID);
                break;
        }
    }
    protected final Dialog onCreateDialog(final int id){
        Dialog dialog = null;
        switch(id){
            case DIALOG_ID:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Terima kasih anda telah bertransaksi produk "+this.namaProduk+" sebanyak "+this.kuantitas+" seharga "+this.totalHarga)
                        .setCancelable(false)
                        .setNegativeButton("No", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Calendar c = Calendar.getInstance();
                                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                                String hariIni = df.format(c.getTime());
//                                dh.insertTransaksi(sp.getInt("idUser",-1),sp.getInt("idProduk",-1),Integer.parseInt(kuantitas),totalHarga,hariIni);
                                boolean res = soap.setAddTransaksi(sessionId,namaProduk,hargaProduk,kuantitas,hariIni);
                                if(res){
                                    Log.d("Jual",res+" "+hariIni);
                                }
                                Jual.this.finish();
                            }
                        });
                AlertDialog alert = builder.create();
                dialog = alert;
                break;
            default:

        }
        return dialog;
    }
}