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
import android.widget.LinearLayout;
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

public class home extends AppCompatActivity {

    Button btn;
    LinearLayout dw;
    DatabaseReference ref;
    TextView username;
    String fname,lname;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        btn = (Button)findViewById(R.id.log_out);
        dw = (LinearLayout)findViewById(R.id.ll2);
        username = (TextView)findViewById(R.id.uname);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String Userid = currentUser.getUid();

        ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Users").child(Userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                username.setText("Hi," + user.getFirstName() + " " + user.getLastName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(home.this,Login.class);
//                startActivity(i);
//                sp.edit().putBoolean("logged",false).apply();
//                finish();
//            }
//        });

        dw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(home.this,RegDailyWorker.class);
                startActivity(i);

            }
        });
    }
}
