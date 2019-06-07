package com.example.rockn.connectdatabase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class teach2 extends AppCompatActivity {
    String s,roll;
    int r;
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach2);
        Intent i=getIntent();
        s=i.getStringExtra("sub");
        roll=i.getStringExtra("roll");
        r=Integer.valueOf(roll);
        e1=(EditText)findViewById(R.id.editText7);
        e2=(EditText)findViewById(R.id.editText9);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference db=database.getReference().child("Member");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long n=dataSnapshot.getChildrenCount();
               e1.setText(dataSnapshot.child(roll).child("name").getValue().toString());
               e2.setText(dataSnapshot.child(roll).child(s).getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
