package com.example.secfsoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

class offuser {
    public String firstName, lastName, email, mobnumber, password, society, str_wing, house;

    public offuser() {

    }
}

public class home extends AppCompatActivity {

    Button btn;
    DatabaseReference ref;
    TextView username;
    String fname,lname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn = (Button)findViewById(R.id.log_out);
        username = (TextView)findViewById(R.id.uname);

//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        String Userid = currentUser.getUid();
//
//        ref = FirebaseDatabase.getInstance().getReference();
//        ref.child("Users").child(Userid).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                offuser user = dataSnapshot.getValue(offuser.class);
//                username.setText("Hi," + user.firstName + " " + user.lastName);
//                Toast.makeText(home.this,user.firstName,Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


//        Toast.makeText(home.this,Userfname,Toast.LENGTH_SHORT).show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = settings.edit();
                editor.remove("login");

                Intent i=new Intent(home.this,Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
