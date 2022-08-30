package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    CardView take_attendance, details, report, calendar,unit_test,logout;
    private long pressedTime;
    FirebaseAuth mAuth;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        take_attendance = findViewById(R.id.take_attendance);
        calendar = findViewById(R.id.calendar);
        details = findViewById(R.id.details);
        report = findViewById(R.id.report);
        unit_test = findViewById(R.id.unit_test);
        logout=findViewById(R.id.logout);
        mAuth=FirebaseAuth.getInstance();
        boolean isLogin= checkLogin();
        System.out.println("isLogin");
        if (!isLogin)
        {

            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
       logout .setOnClickListener(view -> {
           mAuth.signOut();
           Toast.makeText(MainActivity.this,"Logout successfully",Toast.LENGTH_SHORT).show();
           startActivity(new Intent(MainActivity.this, LoginActivity.class));
           finish();



       });

        try {
            take_attendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), TakeAttendance.class);
                    startActivity(i);
                }
            });

//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), CreateTable.class);
//                startActivity(i);
//            }
//        });


            report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), ReportActivity.class);
                    startActivity(i);
                }
            });


            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), DetailsUI.class);
                    startActivity(i);
                }
            });

            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), ContactsActivity.class);
                    startActivity(i);
                }
            });

            unit_test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Unit_Test.class);
                    startActivity(i);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if (user==null)
        {
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }
    private boolean checkLogin()
{

    @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);


     boolean s1 = sh.getBoolean("isLogin",false);
    return s1;

}

}
