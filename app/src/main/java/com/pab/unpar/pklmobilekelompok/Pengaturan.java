package com.pab.unpar.pklmobilekelompok;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Pengaturan extends AppCompatActivity {

    private Button editWarna, editPass, back;

    private SharedPreferences sp;
    private SharedPreferences.Editor ed;

    private AlertDialog dialog;
    private AlertDialog.Builder builder;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_pengaturan);

        sp = getSharedPreferences("dataProduk", MODE_PRIVATE);
        ed = sp.edit();

        editWarna = (Button) findViewById(R.id.buttonUbahWarna);
        editWarna.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showPopUpEditWarna();
            }

        });

        editPass = (Button) findViewById(R.id.buttonUbahPass);
        editPass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showPopUpEditPass();
            }

        });


        back = (Button) findViewById(R.id.buttonKembaliPengaturan);
        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(Pengaturan.this, Home.class);
                startActivity(i);

            }

        });

    }

    private void showPopUpEditWarna() {

        builder = new AlertDialog.Builder(Pengaturan.this);
        view = getLayoutInflater().inflate(R.layout.activity_ubahwarna, null);

        TextView def, gray, green, blue;

        def = (TextView) view.findViewById(R.id.warnaDefault);
        gray = (TextView) view.findViewById(R.id.warnaAbuAbu);
        green = (TextView) view.findViewById(R.id.warnaHijau);
        blue = (TextView) view.findViewById(R.id.warnaBiru);

        def.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                changeTheme(Utils.THEME_DEFAULT);
            }

        });

        gray.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                changeTheme(Utils.THEME_GRAY);
            }

        });

        green.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                changeTheme(Utils.THEME_GREEN);
            }

        });

        blue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                changeTheme(Utils.THEME_BLUE);
            }

        });

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }

    private void changeTheme(int code) {
        Utils.changeToTheme(Pengaturan.this, code);
        ed.putString("themeCode", code + "");
        ed.commit();
        Log.d("Status", "Changed");
        Toast.makeText(getApplicationContext(), "Mengganti warna tampilan",
                Toast.LENGTH_LONG).show();
        dialog.dismiss();
    }

    private void showPopUpEditPass() {

        builder = new AlertDialog.Builder(Pengaturan.this);
        view = getLayoutInflater().inflate(R.layout.activity_editpassword, null);

        Button confirm = (Button) view.findViewById(R.id.buttonKonfUbahPass);
        confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                EditText currPass, newPass, confNewPass;

                currPass = (EditText) view.findViewById(R.id.currentPass);
                newPass = (EditText) view.findViewById(R.id.newPass);
                confNewPass = (EditText) view.findViewById(R.id.confirmNewPass);

                if(currPass.getText().toString().isEmpty() ||
                        newPass.getText().toString().isEmpty() ||
                        confNewPass.getText().toString().isEmpty()) {
                    if(currPass.getText().toString().isEmpty()) {
                        currPass.setError("Field tidak boleh kosong");
                    } else if (newPass.getText().toString().isEmpty()) {
                        newPass.setError("Field tidak boleh kosong");
                    } else {
                        confNewPass.setError("Field tidak boleh kosong");
                    }
                } else {

                    String password = "contoh"; // "contoh" diganti jadi kode untuk ambil password saat ini dari server / DB
                    if (!currPass.getText().toString().equals(password)) {
                        currPass.setError("Password anda salah");
                    } else {
                        if (!newPass.getText().toString().equals(confNewPass.getText().toString())) {
                            confNewPass.setError("Password konfirmasi berbeda");
                        } else {
                            // diisi kode untuk simpan password baru ke server/ DB
                            Toast.makeText(getApplicationContext(), "Mengubah password",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }

                }

            }

        });

        Button batal = (Button) view.findViewById(R.id.buttonBatalEditPass);
        batal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }

        });

        builder.setView(view);
        dialog = builder.create();
        dialog.show();


    }

}
