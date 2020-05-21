package com.example.secfsoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Security_JoinSociety extends AppCompatActivity {
    TextInputLayout code;
    Button contbtn;
    String userid;
    DatabaseReference ref,socref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security__join_society);
        code = findViewById(R.id.soc_code);
        contbtn = (Button) findViewById(R.id.cont);

        socref = FirebaseDatabase.getInstance().getReference("Society");
        ref = FirebaseDatabase.getInstance().getReference("Security");

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        userid = currentUser.getUid();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String soc = dataSnapshot.child(userid).child("society").getValue().toString();

                if(!soc.isEmpty())
                {
                    Intent i = new Intent(Security_JoinSociety.this,Security_home.class);
                    startActivity(i);
                    Toast.makeText(Security_JoinSociety.this, "Society details already registered", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Security_JoinSociety.this,"Error fetching data",Toast.LENGTH_SHORT).show();
            }
        });


        contbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(jsoc())
                {

                }
            }
        });
    }
    private boolean jsoc() {
        final String society_code = code.getEditText().getText().toString().trim().toLowerCase();


        if (TextUtils.isEmpty(society_code)) {
            code.setError("Field Cannot be empty");
            Toast.makeText(this, "Enter Society Code", Toast.LENGTH_SHORT).show();
            return false;
        }else {


            socref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(society_code).exists()) {
//                        Log.e("strName","exists");
                        ref.child(userid).child("society").setValue(society_code);


                        Intent intent = new Intent(Security_JoinSociety.this, Security_home.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(Security_JoinSociety.this,"Wrong society code",Toast.LENGTH_SHORT).show();
//                        Log.e("strName","error1213");
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Security_JoinSociety.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }
    }

}
