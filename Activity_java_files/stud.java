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

public class stud extends AppCompatActivity {
    EditText e1,e2,e3;
    String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud);
        Intent i=getIntent();
        s1=i.getStringExtra("name");
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference().child("Member");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long n=dataSnapshot.getChildrenCount();
                for(int i1=1;i1<=(int)n;i1++)
                {
                    if(dataSnapshot.child(String.valueOf(i1)).child("name").getValue().equals(s1)){
                      e1.setText(s1);
                      String s2=String.valueOf(dataSnapshot.child(String.valueOf(i1)).child("sub1").getValue());
                      e2.setText(s2);
                      e3.setText(String.valueOf(dataSnapshot.child(String.valueOf(i1)).child("sub2").getValue()));
                      break;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
