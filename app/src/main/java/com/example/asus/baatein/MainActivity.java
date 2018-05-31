package com.example.asus.baatein;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b1;
    EditText et1,et2,et3,et4;
    TextView tv1,tv;
    private ProgressDialog pb;
    RadioButton rb1,rb2;

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    b1 = (Button) findViewById(R.id.btn);
    et1 = (EditText) findViewById(R.id.name);
    et2 = (EditText) findViewById(R.id.pw);
    et3 = (EditText) findViewById(R.id.phn);
    et4 = (EditText) findViewById(R.id.add);
    tv1 = (TextView) findViewById(R.id.tv);
    tv = (TextView) findViewById(R.id.tv2);
    //rb1 = (RadioButton) findViewById(R.id.mrb);
   // rb2 = (RadioButton) findViewById(R.id.frb);
    pb = new ProgressDialog(this);
    firebaseAuth = FirebaseAuth.getInstance();
    database = FirebaseDatabase.getInstance();
    reference = FirebaseDatabase.getInstance().getReference("Users");

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }

    b1.setOnClickListener(this);
    tv1.setOnClickListener(this);
      /*  RadioGroup rg = (RadioGroup) findViewById(R.id.rgrp);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.mrb:
                        String male = rb1.getText().toString();
                        break;
                    case R.id.frb:
                        String female = rb1.getText().toString();
                }
            }
        });  */


    }

    private void registerUser (){
        String Email = et1.getText().toString().trim();
        String Password = et2.getText().toString().trim();
        String Phone = et3.getText().toString().trim();
        String Address = et4.getText().toString().trim();


        SaveData saveData = new SaveData(Email,Password,Phone,Address);

        reference.push().setValue(saveData);    //push() will help to update data with previous data 
						//if we dont use it then new data will come at the place of old data

        if (TextUtils.isEmpty(Email)){
            Toast.makeText(this, "Please Enter your mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)){
            Toast.makeText(this, "Please Enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Phone)) {
            Toast.makeText(this, "Please Enter your contact no", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Address)) {
            Toast.makeText(this, "Please Enter your address", Toast.LENGTH_SHORT).show();
            return;
        }
      /*  if (TextUtils.isEmpty(male)) {
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(female)) {
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show();
            return;
        }  */

        pb.setMessage("Register User");
        pb.show();

        firebaseAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            if (firebaseAuth.getCurrentUser() != null){
                                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "Could not Register", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view ==b1){
            registerUser ();

        }
        if (view == tv1){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
