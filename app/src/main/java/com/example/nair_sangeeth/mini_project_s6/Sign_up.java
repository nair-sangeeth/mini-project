package com.example.nair_sangeeth.mini_project_s6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by nair_sangeeth on 3/1/17.
 */

public class Sign_up extends AppCompatActivity implements View.OnClickListener{
    private Button RegisterButton;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextFName;
    private EditText editTextLName;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth; // Authentication Object of Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        firebaseAuth=FirebaseAuth.getInstance();// Authentication Object Initialised
        RegisterButton = (Button) findViewById(R.id.signupbutton);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextFName = (EditText) findViewById(R.id.firstname);
        editTextLName = (EditText) findViewById(R.id.lastname);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextConfirmPassword = (EditText) findViewById(R.id.conpassword);
        textViewSignup = (TextView) findViewById(R.id.textViewSignUp);
        progressDialog=new ProgressDialog(this);


        RegisterButton.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }

    private void RegisterUser(){
        String email = editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        String confirmpassword=editTextConfirmPassword.getText().toString().trim();
        String fname=editTextFName.getText().toString().trim();
        String lname=editTextLName.getText().toString().trim();

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
        if(TextUtils.isEmpty(fname)) {
            //no password entered
            Toast.makeText(this, "Please Enter a First Name", Toast.LENGTH_SHORT).show();
            // stop execution
            return;
        }
        if(TextUtils.isEmpty(lname)) {
            //no password entered
            Toast.makeText(this, "Please Enter a Last Name", Toast.LENGTH_SHORT).show();
            // stop execution
            return;
        }
        if (!password.equals(confirmpassword)){
            //passwords dont match
            Toast.makeText(this, "Passwords dont match", Toast.LENGTH_SHORT).show();
            //stop execution
            return;
        }
        //if valid email and password is entered
        //we show a progress bar becasue it's an internet operation and may take time

        progressDialog.setMessage("Please Wait while we Register You...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //if the user gets registered successfully
                    //a profile is created successfully for the user
                    //display a success message as well
                    Toast.makeText(Sign_up.this,"Registration Successfull..!!",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),First_Time_User_HomeScreenActivity.class));
                }
                else{
                    Toast.makeText(Sign_up.this,"Registration Error...",Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == RegisterButton){
            RegisterUser();
        }
    }
}
