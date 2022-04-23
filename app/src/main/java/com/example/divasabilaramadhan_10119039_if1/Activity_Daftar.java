package com.example.divasabilaramadhan_10119039_if1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
//Nama : Diva Sabila Ramadhan
//NIM  : 10119039
//Kelas: IF-1
//Tanggal : 22/04/2022

public class Activity_Daftar extends AppCompatActivity {
    TextView Email, NamaLengkap, NIM, Kelas, Password;
    Button Daftar;
    ProgressBar procces;
    FirebaseAuth fAuth;
    String user;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        Email = findViewById(R.id.text1);
        NamaLengkap = findViewById(R.id.NamaLengkap);
        NIM = findViewById(R.id.nim);
        Kelas = findViewById(R.id.kelas);
        Password = findViewById(R.id.Pass);
        Daftar = findViewById(R.id.b1_regis);
        procces = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        if (fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        //mengaktifkan tombol daftar pada Form Daftar
        Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String Nama = NamaLengkap.getText().toString();
                String nim = NIM.getText().toString();
                String kelas = Kelas.getText().toString();
                String pass = Password.getText().toString().trim();
                //memvalidasi inputan yang dimasukkan
                if (TextUtils.isEmpty(email)){
                    Email.setError("Email Harus Diisi!");
                    return;
                }
                if (TextUtils.isEmpty(Nama)){
                    NamaLengkap.setError("Nama Lengkap Harus Diisi!");
                    return;
                }
                if (TextUtils.isEmpty(nim)){
                    NIM.setError("NIM Harus Diisi!");
                    return;
                }
                if (TextUtils.isEmpty(kelas)){
                    Kelas.setError("Kelas Harus Diisi!");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    Password.setError("Password Harus Diisi!");
                    return;
                }
                procces.setVisibility(View.VISIBLE);

                //memasukkan data yang di inputkan oleh user
                fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Activity_Daftar.this,"Akun Anda Berhasil Dibuat", Toast.LENGTH_SHORT).show();
                            user = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firestore.collection("users").document(user);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Email", Email);
                            user.put("NamaLenkap", Nama);
                            user.put("NIM", nim);
                            user.put("Kelas", kelas);
                            user.put("Password", pass);
                            documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(Activity_Daftar.this, "Data Berhasil Dimasukkan" + user, Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else
                        {
                            Toast.makeText(Activity_Daftar.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}