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

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Member m;
    Member1 m1;
    int flag=0,flag1=0,flag2=0;
    DatabaseReference myref,ref1,ref2,ref3;
    Spinner s;
    String[] type={"Teacher","Student"};
    long counter=0;
    long counter1=0;
    Intent ii,j;
    String name1,password1,type1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myref = database.getReference().child("Member");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    counter=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref1=database.getReference().child("hello");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    counter1=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        s= (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter= new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,type);
        s.setAdapter(adapter);
        Button b1=(Button)findViewById(R.id.button);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);

         m=new Member();
         m1=new Member1();
         flag2=0;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 name1=e1.getText().toString();
                 password1=e2.getText().toString();
                 type1=s.getSelectedItem().toString();


                if(type1=="Student") {
                    flag2=0;
                    myref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            long n;
                            if(dataSnapshot.exists()) {
                                 n = dataSnapshot.getChildrenCount();
                            }else
                            {
                                n=0;
                            }

                            for (int i1 = 1; i1 <= (int) n; i1++) {

                                //String s1 = dataSnapshot.child(String.valueOf(i1)).child("name").getValue().toString();
                                  flag2=0;
                                if(String.valueOf(dataSnapshot.child(String.valueOf(i1)).child("name").getValue()).equals(name1)) {
                                    flag2 = 1;
                                  //  Toast.makeText(MainActivity.this, "Username already taken"+String.valueOf(flag2), Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            if(flag2==0) {
                                Toast.makeText(MainActivity.this,String.valueOf(flag2)+" blah ",Toast.LENGTH_SHORT).show();
                                m.setName(name1);
                                m.setPass(password1);
                                m.setType(type1);
                                m.setSub1(0);
                                m.setSub2(0);
                                myref.child(String.valueOf(counter + 1)).setValue(m);
                                Toast.makeText(MainActivity.this,"Registered successully",Toast.LENGTH_SHORT).show();
                            }

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




                }
                else {

                    m1.setName(name1);
                    m1.setPass(password1);
                    m1.setType(type1);
                    ref1.child(String.valueOf(counter1+1)).setValue(m1);
                }
                Toast.makeText(MainActivity.this,"Registered Successfully now login",Toast.LENGTH_LONG).show();
            }
        });
        Button b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Button2 login clicked",Toast.LENGTH_LONG).show();
                  ii = new Intent(MainActivity.this,stud.class);
                  j=new Intent(MainActivity.this,teac.class);
                final String name11=e1.getText().toString();
                final String password11=e2.getText().toString();
                String type1=s.getSelectedItem().toString();
                if(type1=="Student")
                {
                    ref2=FirebaseDatabase.getInstance().getReference().child("Member");
                    ref2.addValueEventListener(new ValueEventListener() {
                        boolean fl=true;
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            long n=dataSnapshot.getChildrenCount();
                            for(int i1=1;i1<=(int)n;i1++) {

                                String s1 = dataSnapshot.child(String.valueOf(i1)).child("name").getValue().toString();
                                String s2 = dataSnapshot.child(String.valueOf(i1)).child("pass").getValue().toString();
                                if (s1.equals(name11) && s2.equals(password11) && fl) {
                                    ii.putExtra("name",s1);
                                    startActivity(ii);
                                    flag = 1;
                                    fl=false;
                                }
                            }
                            if(flag==0)
                            { //System.out.print(name11+" "+password11+" "+s1+" "+s2);
                                Toast.makeText(MainActivity.this,"Invalid credentials ",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else {

                    ref3=FirebaseDatabase.getInstance().getReference().child("hello");
                    ref3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            long n=dataSnapshot.getChildrenCount();
                            for(int i1=1;i1<=(int)n;i1++) {

                                String s1 = dataSnapshot.child(String.valueOf(i1)).child("name").getValue().toString();
                                String s2 = dataSnapshot.child(String.valueOf(i1)).child("pass").getValue().toString();
                                if (s1.equals(name11) && s2.equals(password11)) {
                                    startActivity(j);
                                    flag1 = 1;
                                }
                            }
                            if(flag1==0)
                            { //System.out.print(name11+" "+password11+" "+s1+" "+s2);
                                Toast.makeText(MainActivity.this,"Invalid credentials ",Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

    }
}
