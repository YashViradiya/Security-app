package com.example.secfsoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Security_community_option extends AppCompatActivity {
    Button sec,com;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_community_option);
        sec = (Button)findViewById(R.id.sec_btn);
        com = (Button)findViewById(R.id.com_btn);

        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Security_community_option.this,Security_login.class);
                startActivity(i);
                finish();
            }
        });
        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Security_community_option.this,Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
