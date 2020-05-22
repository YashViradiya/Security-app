package com.example.secfsoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComSecOpt extends AppCompatActivity {

    Button sec,com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_sec_opt);

        com = (Button)findViewById(R.id.com_btn);
        sec = (Button)findViewById(R.id.sec_btn);

        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ComSecOpt.this,Login.class);
                startActivity(i);
            }
        });

        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ComSecOpt.this,SecurityLogin.class);
                startActivity(i);
            }
        });

    }
}
