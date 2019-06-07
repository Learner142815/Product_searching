package com.example.rockn.connectdatabase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.StringTokenizer;

public class teac extends AppCompatActivity {
    Spinner s;
    String[] sub={"sub1","sub2"};
    Button b1,b2;
    EditText e1,e2;
    String s2,str;
    DatabaseReference ref,myref1;
    long n;
    int i1;

    Boolean f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teac);
        s= (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter adapter= new ArrayAdapter(teac.this,android.R.layout.simple_spinner_item,sub);
        s.setAdapter(adapter);
        b1=(Button)findViewById(R.id.button3);
        b2=(Button)findViewById(R.id.button4);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref=database.getReference().child("Member");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                n=dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        s2=s.getSelectedItem().toString();
        e1=(EditText)findViewById(R.id.editText4);
        e2=(EditText)findViewById(R.id.editText5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(teac.this,"button3 is clicked",Toast.LENGTH_SHORT).show();

                s2=s.getSelectedItem().toString();


                 String s=e1.getText().toString();
                 e1.setText("");
                StringTokenizer st=new StringTokenizer(s,",");


               while(st.hasMoreTokens()){
                    int i=Integer.valueOf(st.nextToken());
                   Toast.makeText(teac.this,String.valueOf(i),Toast.LENGTH_LONG).show();
                    int pres=func1(i,s2);

                    Toast.makeText(teac.this,String.valueOf(pres),Toast.LENGTH_LONG).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            s2=s.getSelectedItem().toString();
            String roll=e2.getText().toString();
            Intent iii=new Intent(teac.this,teach2.class);
            iii.putExtra("sub",s2);
            iii.putExtra("roll",roll);
            startActivity(iii);
            }
        });

    }
    int func1(int i,String strr){
        i1=i;
        str=strr;
        int n11=0;
        Toast.makeText(teac.this,"inside",Toast.LENGTH_LONG).show();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
         myref1=database.getReference().child("Member");

        myref1.addListenerForSingleValueEvent(new ValueEventListener() {
            boolean f=true;
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long n=dataSnapshot.getChildrenCount();
               // for(int j=1;j<=(int)n;j++){
                    if(f){
                        int n11=Integer.valueOf(String.valueOf(dataSnapshot.child(String.valueOf(i1)).child(str).getValue()));
                        Toast.makeText(teac.this,"Attendance marked",Toast.LENGTH_LONG).show();

                        myref1.child(String.valueOf(i1)).child(str).setValue(n11+1);
                        f=false;

                    }
                //}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(teac.this,"n11 value is "+String.valueOf(n11),Toast.LENGTH_LONG).show();
        return (int)n11;
    }

}
