package com.np.wastewatersih;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;

public class Admin_Registration extends AppCompatActivity {
    EditText et1, et2, et3, et4,et5;
    Button bt;
    FirebaseAuth firebaseAuth;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Spinner sp1, sp2;
    String name, email, pass, phone, gender, finalCity, finalState,idno;
    String[] s = {"Bhopal"};
    String[] s2 = {"Madhya Pradesh"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__registration);
        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.em);
        et3 = findViewById(R.id.editText2);
        et4 = findViewById(R.id.phn);
        et5 = findViewById(R.id.valid);
        radioGroup = findViewById(R.id.rg);
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        sp1 = findViewById(R.id.spin);
        sp2 = findViewById(R.id.spin2);
        ArrayAdapter<String> a1 = new ArrayAdapter<>(Admin_Registration.this, android.R.layout.simple_spinner_dropdown_item, s);
        sp1.setAdapter(a1);
        ArrayAdapter<String> a2 = new ArrayAdapter<>(Admin_Registration.this, android.R.layout.simple_spinner_dropdown_item, s2);
        sp2.setAdapter(a2);
        bt = findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Admin_Registration.this, "Registering Admin Please Wait", Toast.LENGTH_SHORT).show();
                getdat();
                dbent(name, email, phone, gender, finalCity, finalState, pass,idno);
            }
        });

    }

    final public void dbent(final String name, final String email, final String phone, final String gender, final String city, final String state, final String pass,final String idno) {
        // TODO: 03-04-2019 check for empty edittext
        if (isValidEmail(email) && !(TextUtils.isEmpty(pass)) && !(TextUtils.isEmpty(name)) && !(TextUtils.isEmpty(phone))) {

            final DatabaseReference dbs;
            dbs = FirebaseDatabase.getInstance().getReference();
            dbs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    HashMap<String, Object> userdatamap = new HashMap<>();
                    userdatamap.put("Phone", phone);
                    userdatamap.put("Name", name);
                    userdatamap.put("Email", email);
                    userdatamap.put("Gender", gender);
                    userdatamap.put("City", city);
                    userdatamap.put("State", state);
                    userdatamap.put("IdNo",idno);
                    userdatamap.put("imageurl", "default");
                    dbs.child("Admin").child(phone).updateChildren(userdatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                 Toast.makeText(getApplicationContext(),"Registration in Process",Toast.LENGTH_SHORT).show();
                                createuser(email, pass);
                            } else {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else {
            Toast.makeText(Admin_Registration.this, "Fields Cannot be Empty", Toast.LENGTH_LONG).show();
        }
    }

    public void getdat() {
        name = et1.getText().toString().trim();
        email = et2.getText().toString().trim();
        pass = et3.getText().toString().trim();
        phone = et4.getText().toString().trim();
        idno = et5.getText().toString().trim();
        String city = null;
        String state = null;
        int sel = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(sel);
        gender = radioButton.getText().toString().trim();
        for (int i = 0; i < 4; i++) {
            if (sp1.getSelectedItemPosition() == i) {
                city = s[i];
            }
            if (sp2.getSelectedItemPosition() == i) {
                state = s2[i];
            }


        }
        finalCity = city;
        finalState = state;
    }

    public void createuser(String email, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(Admin_Registration.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Email Registered", Toast.LENGTH_SHORT).show();
                            rese();

                        } else {
                            Toast.makeText(Admin_Registration.this, "FAILED ! Use different email", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void rese() {
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
    }
}