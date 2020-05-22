package com.example.secfsoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SecuritySignup extends AppCompatActivity {

    TextInputLayout regfirst,reglast,regemail,regmo,regpass;
    Button regbtn,regtolog;
    FirebaseDatabase root;
    DatabaseReference ref;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_signup);

        regbtn= (Button)findViewById(R.id.regsignup);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SecuritySignup.this,SecurityHome.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
