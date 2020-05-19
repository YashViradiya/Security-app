package com.example.secfsoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button sign_btn,login_btn;
    TextInputLayout logemail,logpass;
    private FirebaseAuth mAuth;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        logemail = findViewById(R.id.email);
        logpass = findViewById(R.id.password);
        sign_btn = findViewById(R.id.signup_btn);
        mAuth = FirebaseAuth.getInstance();

        sp = getSharedPreferences("login",MODE_PRIVATE);
        if(sp.getBoolean("logged",false)) {
            loginto();
        }


        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,signup.class);
                startActivity(i);
                finish();
            }
        });
        login_btn = findViewById(R.id.signin_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!valemail())
                    return;
                if(!valpass())
                    return;
                final String userEntermail = logemail.getEditText().getText().toString().trim();
                final String userEnterpass = logpass.getEditText().getText().toString().trim();
                mAuth.signInWithEmailAndPassword(userEntermail,userEnterpass).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            loginto();
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Please Enter Correct Details", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }



    private boolean valemail(){
        String val = logemail.getEditText().getText().toString().trim();
        String pat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            logemail.setError("Field Cannot be empty");
            return false;
        }
        else if(!val.matches(pat)){
            logemail.setError("Invalid Email address");
            return false;
        }
        else
        {
            logemail.setError(null);
            logemail.setErrorEnabled(false);
            return true;
        }
    }



    private boolean valpass(){
        String val = logpass.getEditText().getText().toString().trim();
        if(val.isEmpty()){
            logpass.setError("Field Cannot be empty");
            return false;
        }
        else
        {
            logpass.setError(null);
            return true;
        }
    }


    private void loginto(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = currentUser.getUid();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String s = dataSnapshot.child(userid).child("society").getValue().toString();
                Log.e("strName","1"+s+"2");
                if(s.isEmpty())
                {
                    Intent intent = new Intent(Login.this,JoinSociety.class);
                    sp.edit().putBoolean("logged",true).apply();
                    startActivity(intent);
                    finish();
                    return;
                }
                else
                {
                    Intent i = new Intent(Login.this,home.class);
                    sp.edit().putBoolean("logged",true).apply();
                    startActivity(i);
                    finish();
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Login.this,"Error fetching data",Toast.LENGTH_SHORT).show();
            }
        });
    }
}