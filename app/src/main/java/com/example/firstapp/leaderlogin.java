package com.example.firstapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class leaderlogin extends AppCompatActivity {
    EditText username, password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderlogin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
    }

    public void gotoregister(View view) {
        Intent intent = new Intent(leaderlogin.this, Leader_regist.class);
        startActivity(intent);
    }

    public void login(View view) {
        String emai = username.getText().toString();
        String pwd = password.getText().toString();
        mAuth.signInWithEmailAndPassword(emai, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(leaderlogin.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(leaderlogin.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
