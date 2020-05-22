package com.example.secfsoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SecurityLogin extends AppCompatActivity {

    Button sign_btn,login_btn;
    TextInputLayout logemail,logpass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_login);

        logemail = findViewById(R.id.email);
        logpass = findViewById(R.id.password);
        sign_btn = findViewById(R.id.signup_btn);
        mAuth = FirebaseAuth.getInstance();

        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecurityLogin.this,SecuritySignup.class);
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
                mAuth.signInWithEmailAndPassword(userEntermail,userEnterpass).addOnCompleteListener(SecurityLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent i = new Intent(SecurityLogin.this,SecurityHome.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(SecurityLogin.this, "Please Enter Correct Details", Toast.LENGTH_SHORT).show();
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



}
