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

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnRegister, btnLogin;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        login();
    }

    private void   initView(){
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        auth = FirebaseAuth.getInstance();
    }

    private void  login(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }});

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailUser = edtEmail.getText().toString().trim();
                final String passwordUser = edtPassword.getText().toString().trim();

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
                    auth.signInWithEmailAndPassword(emailUser,passwordUser).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                            else{
                                Bundle bundle = new Bundle();
                                bundle.putString("email", emailUser);
                                bundle.putString("pass", passwordUser);
                                startActivity(new Intent(LoginActivity.this,MainActivity.class).putExtra("emailpass",bundle));
                                finish();
                            }
                        }
                    });
                }

            }
        });
    }
}
