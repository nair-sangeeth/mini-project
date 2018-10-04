package com.example.nair_sangeeth.mini_project_s6;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class First_Time_User_HomeScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAddCar;
    private Button buttonlogout;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_time_user_homescreenactivity);

        firebaseAuth=FirebaseAuth.getInstance();
        buttonlogout=(Button) findViewById(R.id.logout);
        buttonAddCar=(Button) findViewById(R.id.addcar);


    }

    @Override
    public void onClick(View v) {
        if (v.equals(buttonAddCar)){
            finish();
            startActivity(new Intent(First_Time_User_HomeScreenActivity.this,AddCar.class));
        }
        else if (v.equals(buttonlogout)){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(First_Time_User_HomeScreenActivity.this,Login.class));
        }
    }
}
