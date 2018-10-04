package com.example.nair_sangeeth.mini_project_s6;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button loginbutton;
    private EditText editTextemail;
    private EditText editTextpassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextemail=(EditText) findViewById(R.id.Email);
        editTextpassword=(EditText) findViewById(R.id.Password);
        loginbutton=(Button) findViewById(R.id.login);
        textViewSignup=(TextView) findViewById(R.id.textViewSignUp);
        progressDialog=new ProgressDialog(this);
        loginbutton.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        firebaseAuth= FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() !=null){
            //user is already logged in
            firebaseAuth.signOut();

        }
    }
    public String email;
    private void UserLogin(){
        email=editTextemail.getText().toString().trim();
        String password=editTextpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //no Email entered
            Toast.makeText(this, "Enter a Valid Email",Toast.LENGTH_SHORT).show();
            // stop execution
            return;
        }
        if(TextUtils.isEmpty(password)){
            //no password entered
            Toast.makeText(this, "Invalid Password",Toast.LENGTH_SHORT).show();
            // stop execution
            return;
        }
        //if valid email and password is entered
        //we show a progress bar becasue it's an internet operation and may take time
        progressDialog.setMessage("You are logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()){
                    //login was successfull by the user
                    //user is cleared to the profile
                    Toast.makeText(Login.this,"Logged in",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,First_Time_User_HomeScreenActivity.class));
                    finish();

                }
                else{
                    //login was not succsfull
                    //username may not exist
                    Toast.makeText(Login.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view==loginbutton){
            UserLogin();//Login Function
;
        }
        if(view==textViewSignup){
            finish();//finish current activity
            startActivity(new Intent(this,Sign_up.class));
        }
    }
}
