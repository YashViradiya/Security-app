package com.example.secfsoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class JoinSociety extends AppCompatActivity {

    TextInputLayout code, wingname, hnum;
    Button contbtn, regbtn;
    String userid;
    int check = 0;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_join_society);

            code = findViewById(R.id.soc_code);
            wingname = findViewById(R.id.street_wing);
            hnum = findViewById(R.id.house_no);
            contbtn = (Button) findViewById(R.id.cont);
            regbtn = (Button) findViewById(R.id.reg_soc);


            regbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(JoinSociety.this, home.class);
                    startActivity(i);
                }
            });

            contbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (jsoc()) {
                        Intent i = new Intent(JoinSociety.this, home.class);
                        startActivity(i);
                    }
                }
            });
        }


    private boolean jsoc() {
        String society_code = code.getEditText().getText().toString().trim();
        String street_wing = wingname.getEditText().getText().toString().trim();
        String house_no = hnum.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(society_code)) {
            code.setError("Field Cannot be empty");
            Toast.makeText(this, "Enter Society Code", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(street_wing)) {
            wingname.setError("Field Cannot be empty");
            Toast.makeText(this, "Enter Street/Wing Detail", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(house_no)) {
            hnum.setError("Field Cannot be empty");
            Toast.makeText(this, "Enter House Number", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            userid = getIntent().getStringExtra("Userid");
            //            Log.e("strName", userid);

            ref = FirebaseDatabase.getInstance().getReference("Users");
            ref.child(userid).child("society").setValue(society_code);
            ref.child(userid).child("str_wing").setValue(street_wing);
            ref.child(userid).child("house").setValue(house_no);

            Toast.makeText(this, userid, Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
