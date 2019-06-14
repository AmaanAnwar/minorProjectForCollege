package com.np.wastewatersih;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        BottomNavigationView bottomNavigationView= findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.navigation_report:
                        Intent intent=new Intent(getApplicationContext(),forUser.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        UserProfile.this.startActivity(intent);
                        break;
                    case R.id.navigation_status:
                        Intent intent1=new Intent(getApplicationContext(),UserStatus.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        UserProfile.this.startActivity(intent1);
                        break;
                    case R.id.navigation_account:
                        Intent intent2=new Intent(getApplicationContext(),UserProfile.class);
                        //  startActivity(intent2);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        UserProfile.this.startActivity(intent2);
                        break;
                }
                return true;
            }
        });

    }
}