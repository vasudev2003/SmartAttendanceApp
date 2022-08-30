package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {
  EditText emailBox, pwdBox;
  Button register;
  TextView login;
  String email, password;
  FirebaseAuth mAuth;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    emailBox = findViewById(R.id.email);
    pwdBox = findViewById(R.id.password);
    register = findViewById(R.id.submit);
    login = findViewById(R.id.login);

    mAuth = FirebaseAuth.getInstance();
    register.setOnClickListener(view -> {
      createUser();

    });

    login.setOnClickListener(view ->{
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    });
  }
  private void createUser()
  {
    String email= emailBox.getText().toString();
    String password= pwdBox.getText().toString();
    if (TextUtils.isEmpty(email)){
      emailBox.setError("Email cannot be empty");
      emailBox.requestFocus();
    }else if (TextUtils.isEmpty(password)){
      pwdBox.setError("Password cannot be empty");
      pwdBox.requestFocus();
    }
    else {
      mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
          if(task.isSuccessful()){
            Toast.makeText(RegisterActivity.this,"User Registered Successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
          }
          else {
            Toast.makeText(RegisterActivity.this,"Registration error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();


          }
        }
      });
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    // Check if user is signed in (non-null) and update UI accordingly.
    FirebaseUser currentUser = mAuth.getCurrentUser();
    if(currentUser != null){

    }
  }
}