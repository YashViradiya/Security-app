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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewSociety extends AppCompatActivity {

    Button btn;
    TextInputLayout name,area,add;
    FirebaseDatabase root;
    DatabaseReference ref;
    private boolean val = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_society);

        btn = findViewById(R.id.soc_create);
        name = findViewById(R.id.soc_name);
        area = findViewById(R.id.soc_area);
        add = findViewById(R.id.soc_add);
        root = FirebaseDatabase.getInstance();
        ref = root.getReference("Society");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String society_name = name.getEditText().getText().toString().trim();
                final String society_area = area.getEditText().getText().toString().trim();
                final String society_add = add.getEditText().getText().toString().trim();



                if (nsoc()) {
                    Random rnd = new Random();
                    int n = 100000 + rnd.nextInt(900000);
                    String num = Integer.toString(n);
//                    while(!val)
//                    {
//                        num = Integer.toString(n);
//                        Query query = FirebaseDatabase.getInstance().getReference("Society").orderByChild("scode").equalTo(num);
//                        query.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                if(!dataSnapshot.exists())
//                                {
//                                    val=true;
//                                    Log.e("strName","success");
//
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            }
//                        });
//                        n = 100000 + rnd.nextInt(900000);
//                        num = Integer.toString(n);
//                    }
                    Intent j = new Intent(NewSociety.this, home.class);
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    final Society soc = new Society(num,society_name, society_area, society_add, currentUser.getUid());
                    ref.child(num).setValue(soc);
                    startActivity(j);
                    finish();
                }
            }
        });
    }


    private boolean nsoc(){
        String society_name = name.getEditText().getText().toString().trim();
        String society_area = area.getEditText().getText().toString().trim();
        String society_add = add.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(society_name)) {
            name.setError("Field Cannot be empty");
            Toast.makeText(this, "Enter society name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(society_area)) {
            area.setError("Field Cannot be empty");
            Toast.makeText(this, "Enter area of society", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(society_add)) {
            add.setError("Field Cannot be empty");
            Toast.makeText(this, "Enter address", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}