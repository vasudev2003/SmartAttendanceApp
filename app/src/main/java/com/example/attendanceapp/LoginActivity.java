package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
  private long pressedTime;

  EditText etLoginEmail, etLoginPassword;
  Button login;
  TextView register;
  String email, password;
  FirebaseAuth mAuth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    etLoginEmail = findViewById(R.id.email);
    etLoginPassword = findViewById(R.id.password);
    login = findViewById(R.id.submit);
    register = findViewById(R.id.register);

    mAuth = FirebaseAuth.getInstance();
    login.setOnClickListener(view -> {
      loginUser();

    });

    register.setOnClickListener(view ->  {
      startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

    });
  }
  private void loginUser()
  {
    String email= etLoginEmail.getText().toString();
    String password= etLoginPassword.getText().toString();
    if (TextUtils.isEmpty(email)){
      etLoginEmail.setError("Email cannot be empty");
      etLoginEmail.requestFocus();
    }else if (TextUtils.isEmpty(password)){
      etLoginPassword.setError("Password cannot be empty");
      etLoginPassword.requestFocus();
    }
    else {
      mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
         if (task.isSuccessful())
         {
           savedLoginCred();
            Toast.makeText(LoginActivity.this,"User logged in successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
         }
         else{
           Toast.makeText(LoginActivity.this,"Log in error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

         }
        }
      });
    }
  }
  private void savedLoginCred()
  {

    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);


    SharedPreferences.Editor myEdit = sharedPreferences.edit();


    myEdit.putBoolean("isLogin",true);



    myEdit.commit();

  }
}
