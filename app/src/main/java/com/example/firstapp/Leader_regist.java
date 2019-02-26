package com.example.firstapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.nio.InvalidMarkException;

public class Leader_regist extends AppCompatActivity {
    ImageView mImageview;
    Button mupload;
    EditText email,password,name,confirmpassword,aadhar_no,phone_no ;
    private FirebaseAuth mAuth;

    private static final int IMAGE_PICK_CODE =1000;
    private static final int PERMISSION_CODE =1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name=findViewById(R.id.a1);
        email=findViewById(R.id.a2);
        password=findViewById(R.id.a3);
        confirmpassword=findViewById(R.id.a4);
        aadhar_no=findViewById(R.id.a5);
        phone_no=findViewById(R.id.a6);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_leader_regist);
         mImageview=findViewById(R.id.image_view);
         mupload=findViewById(R.id.image_button);
         mupload.setOnClickListener(new View.OnClickListener() {
             @RequiresApi(api = Build.VERSION_CODES.M)
             @Override
             public void onClick(View v) {
                 if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                     if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                         String[] permissions ={ Manifest.permission.READ_EXTERNAL_STORAGE};
                         requestPermissions(permissions,PERMISSION_CODE);



                     } else {
                         pickImageFromGallery();

                     }
                 }
                  else {

                     pickImageFromGallery();
                 }

             }
         });
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:{
                if ((grantResults.length > 0) && (grantResults[0])==PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(this,"PERMISSION DENIED..!",Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode==RESULT_OK) &&(requestCode== IMAGE_PICK_CODE)){
            mImageview.setImageURI(data.getData());
        }
    }

    public void details(View view) {
        String mname=name.getText().toString();
        String mpassword=password.getText().toString();
        String memail=email.getText().toString();
        String mconfirmpassword=confirmpassword.getText().toString();
        String maadhar=aadhar_no.getText().toString();
        String mphoneno=phone_no.getText().toString();
        mAuth.createUserWithEmailAndPassword(memail,mpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Leader_regist.this, "User register", Toast.LENGTH_LONG).show();
                }
            }
        });
        Toast.makeText(Leader_regist.this,"Details saved successfully",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Leader_regist.this,leaderlogin.class);
        startActivity(intent);

    }
}
