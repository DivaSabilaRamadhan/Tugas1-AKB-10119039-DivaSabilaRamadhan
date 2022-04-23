package com.example.divasabilaramadhan_10119039_if1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//Nama : Diva Sabila Ramadhan
//NIM  : 10119039
//Kelas: IF-1
//Tanggal : 22/04/2022

public class Activity_Login extends AppCompatActivity {
    TextView Email, Password;
    Button Login;
    ProgressBar procces;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.text1);
        Password = findViewById(R.id.text2);
        Login = findViewById(R.id.button1);
        procces = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String pass = Password.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Email.setError("Ussername Harus Diisi1");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    Password.setError("Passsword Harus Diisi!");
                    return;
                }

                procces.setVisibility(View.VISIBLE);

                // autentikasi Data dari user
                fAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else{
                        Toast.makeText(Activity_Login.this, "Error" + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                        procces.setVisibility(View.GONE);
                    }
                    }
                });
            }
        });

        getSupportActionBar().hide();
        TextView daftar = findViewById(R.id.Daftar);

        //menggaris bawahi kalimat/ membuat link pada kalimat
        SpannableString content = new SpannableString("Daftar Sekarang");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        daftar.setText(content);

        daftar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Activity_Login.this, Activity_Daftar.class));
            }
        });
    }
}