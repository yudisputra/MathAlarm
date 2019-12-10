package com.example.mathalarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnRegister;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        registerUser();
    }

    private void   initView(){
        edtEmail = findViewById(R.id.edt_email_register);
        edtPassword = findViewById(R.id.edt_password_register);
        btnRegister = findViewById(R.id.btn_register);
        auth = FirebaseAuth.getInstance();
    }

    private void  registerUser(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = edtEmail.getText().toString().trim();
                String passwordUser = edtPassword.getText().toString().trim();

                if(emailUser.isEmpty()){
                    edtEmail.setError("Email harus diisi");
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()){
                    edtEmail.setError("Email tidak valid");
                }
                else if(passwordUser.isEmpty()){
                    edtPassword.setError("Password harus diisi");
                }
                else if(passwordUser.length() < 8){
                    edtPassword.setError("Password harus minimal 8 karakter");
                }
                else{
                    auth.createUserWithEmailAndPassword(emailUser,passwordUser).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                            else{
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            }
                        }
                    });
                }
            }
        });
    }


}
