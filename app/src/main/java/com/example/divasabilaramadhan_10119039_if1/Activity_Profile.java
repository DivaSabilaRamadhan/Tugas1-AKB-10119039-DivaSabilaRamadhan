package com.example.divasabilaramadhan_10119039_if1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
//Nama : Diva Sabila Ramadhan
//NIM  : 10119039
//Kelas: IF-1
//Tanggal : 22/04/2022

public class Activity_Profile extends AppCompatActivity {
    TextView Email, NamaLengkap, NIM, Kelas, Password;
    ProgressBar procces;
    FirebaseAuth fAuth;
    String user;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        //mengambil data user yang sudah ada ke dalam Activity_Profile
        NamaLengkap = findViewById(R.id.NamaLengkap);
        NIM = findViewById(R.id.nim);
        Kelas = findViewById(R.id.kelas);
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = firestore.collection("users").document(user);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                NamaLengkap.setText(documentSnapshot.getString("NamaLengkap"));
                NIM.setText(documentSnapshot.getString("NIM"));
                Kelas.setText(documentSnapshot.getString("Kelas"));
            }
        });
    }
}