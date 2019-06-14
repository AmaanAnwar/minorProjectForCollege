package com.np.wastewatersih;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class forAdmn extends AppCompatActivity {
Button bt;
EditText et1,et2;
String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_admn);
        bt=findViewById(R.id.Sub);
        et1=findViewById(R.id.nuum);
        et2=findViewById(R.id.idnuum);
        Intent i=getIntent();
       final String email,password;
        email= i.getStringExtra("Email");
        password=i.getStringExtra("Password");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=et1.getText().toString();
                s2=et2.getText().toString();
                if(true){
                Toast.makeText(getApplicationContext(),"Logging In Please wait",Toast.LENGTH_SHORT).show();
                success(email,password);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Input",Toast.LENGTH_SHORT).show();
                    et1.setText("");
                    et2.setText("");
                }
            }
        });



    }

    public  void success(String email,String password)
    {
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(forAdmn.this,
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(forAdmn.this,AdminWork.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    forAdmn.this.startActivity(i);
                    Toast.makeText(getApplicationContext(),"Downloading Files",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(forAdmn.this,LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    forAdmn.this.startActivity(i);
                }
            }

        });
    }
}
