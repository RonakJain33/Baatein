package com.example.asus.baatein;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private Button pbt;
    private TextView ptv,ptv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){      //to check wheather user is logged in or not
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        ptv  = (TextView) findViewById(R.id.ptv);
        ptv2  = (TextView) findViewById(R.id.ptv2);
        ptv.setText("Welcome :"+firebaseUser.getEmail());
        pbt = (Button) findViewById(R.id.pbtn);
        pbt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            if (view == pbt){
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this,LoginActivity.class));
            }
    }
}
