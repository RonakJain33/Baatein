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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button b1;
    EditText et1,et2;
    TextView tv1,tv2;
    private ProgressDialog pb;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1 = (Button) findViewById(R.id.lbtn);
        et1 = (EditText) findViewById(R.id.lname);
        et2 = (EditText) findViewById(R.id.lpw);
        tv1 = (TextView) findViewById(R.id.ltv);
        tv2 = (TextView) findViewById(R.id.ltv2);
        pb = new ProgressDialog(this);

        b1.setOnClickListener(this);
        tv1.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
    }

    private void UserLogin () {
        String email = et1.getText().toString().trim();
        String password = et2.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter your mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        pb.setMessage("Register User");
        pb.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pb.dismiss();
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        }
                    }
                });
    }
        @Override
    public void onClick(View view) {
        if (view ==b1){
            UserLogin ();
        }
        if (view == tv1){
            finish();
            startActivity(new Intent(this , MainActivity.class));
        }
    }
    }

