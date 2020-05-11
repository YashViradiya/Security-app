package com.example.secfsoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    public static final String USERID = "com.example.secfsoc.USERID";

    TextInputLayout regfirst,reglast,regemail,regmo,regpass;
    Button regbtn,regtolog;
    FirebaseDatabase root;
    DatabaseReference ref;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        regfirst = findViewById(R.id.first_name);
        reglast = findViewById(R.id.last_name);
        regemail = findViewById(R.id.email);
        regmo = findViewById(R.id.mob_no);
        regpass = findViewById(R.id.password);
        regbtn = findViewById(R.id.regsignup);
        regtolog = findViewById(R.id.regtologin);
        root = FirebaseDatabase.getInstance();
        ref = root.getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!valfirst() || !vallast() || !valemail() ||!valphone() || !valpass())
                    return;
                String a = regfirst.getEditText().getText().toString().trim();
                String b = reglast.getEditText().getText().toString().trim();
                final String c = regemail.getEditText().getText().toString().trim();
                String d = regmo.getEditText().getText().toString().trim();
                String e = regpass.getEditText().getText().toString().trim();
                final User usr = new User(a,b,c,d,e,"","","");
                mAuth.createUserWithEmailAndPassword(c,e).addOnCompleteListener(signup.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            String key = mAuth.getUid();
                            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                            ref.child(key).setValue(usr);
                            Intent intent = new Intent(signup.this,JoinSociety.class);
                            intent.putExtra("Userid",currentUser.getUid());
                            startActivity(intent);
                        }
                        else
                        {
                            task.getException();
                            Toast.makeText(signup.this, "Sign up Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
        regtolog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
    private boolean valfirst(){
        String val = regfirst.getEditText().getText().toString();
        if(val.isEmpty()){
            regfirst.setError("Field Cannot be empty");
            return false;
        }
        else
        {
            regfirst.setError(null);
            return true;
        }
    }
    private boolean vallast(){
        String val = reglast.getEditText().getText().toString();
        if(val.isEmpty()){
            reglast.setError("Field Cannot be empty");
            return false;
        }
        else
        {
            reglast.setError(null);
            return true;
        }
    }
    private boolean valemail(){
        String val = regemail.getEditText().getText().toString();
        String pat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            regemail.setError("Field Cannot be empty");
            return false;
        }
        else if(!val.matches(pat)){
            regemail.setError("Invalid Email address");
            return false;
        }
        else
        {
            regemail.setError(null);
            regemail.setErrorEnabled(false);
            return true;
        }
    }
    private boolean valphone(){
        String val = regmo.getEditText().getText().toString();
        if(val.isEmpty()){
            regmo.setError("Field Cannot be empty");
            return false;
        }
        else
        {
            regmo.setError(null);
            return true;
        }
    }
    private boolean valpass(){
        String val = regpass.getEditText().getText().toString();
        if(val.isEmpty()){
            regpass.setError("Field Cannot be empty");
            return false;
        }
        else if(val.length()<8)
        {
            regpass.setError("Password should be minimum 8 characters");
            return false;
        }
        else
        {
            regpass.setError(null);
            return true;
        }
    }




}