package com.example.secfsoc;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewSociety extends AppCompatActivity {

    Button btn;
    TextInputLayout name,area,add;
    FirebaseDatabase root;
    DatabaseReference ref;

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

                String society_name = name.getEditText().getText().toString().trim();
                String society_area = area.getEditText().getText().toString().trim();
                String socirty_add = add.getEditText().getText().toString().trim();

                if (nsoc()) {
                    Intent j = new Intent(NewSociety.this, home.class);
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String key = ref.push().getKey();
                    final Society soc = new Society(society_name, society_area, socirty_add, currentUser.getUid());
                    ref.child(key).setValue(soc);

                    startActivity(j);
                }
            }
        });
    }


        private boolean nsoc(){
            String society_name = name.getEditText().getText().toString().trim();
            String society_area = area.getEditText().getText().toString().trim();
            String socirty_add = add.getEditText().getText().toString().trim();

            if (TextUtils.isEmpty(society_name)) {
                name.setError("Field Cannot be empty");
                Toast.makeText(this, "Enter society name", Toast.LENGTH_SHORT).show();
                return false;
            } else if (TextUtils.isEmpty(society_area)) {
                area.setError("Field Cannot be empty");
                Toast.makeText(this, "Enter area of society", Toast.LENGTH_SHORT).show();
                return false;
            } else if (TextUtils.isEmpty(socirty_add)) {
                add.setError("Field Cannot be empty");
                Toast.makeText(this, "Enter address", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return true;
            }
        }
}
