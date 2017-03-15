package com.pab.unpar.pklmobilekelompok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button katalog = (Button) findViewById(R.id.katalogButton);
        katalog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent i = new Intent(Home.this, Katalog.class);
                startActivity(i);
            }
        });

        Button transaksi = (Button) findViewById(R.id.transaksiButton);
        transaksi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent i = new Intent(Home.this, Transaksi.class);
                startActivity(i);
            }
        });
        
        Button tmbhProduk = (Button) findViewById(R.id.tmbhProdukButton);
        tmbhProduk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent i = new Intent(Home.this, Tambah.class);
                startActivity(i);
            }
        });

        Button rekap = (Button) findViewById(R.id.rekapButton);
        rekap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent i = new Intent(Home.this, Rekap.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.logout){
            Intent i = new Intent(Home.this, Login.class);
            startActivity(i);
            finish();
        }
        else if(item.getItemId() == R.id.keluar){
            Intent i = new Intent(Home.this, SplashKeluar.class);
            startActivity(i);
            finish();
        }
        return true;
    }

}
