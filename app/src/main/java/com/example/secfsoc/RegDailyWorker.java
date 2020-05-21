package com.example.secfsoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegDailyWorker extends AppCompatActivity {

    TextView username;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_daily_worker);



        username = (TextView)findViewById(R.id.toolbar);
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
    }
}
