package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private Button logOut,add,show,home,addAlarm,deleteAlarm,updateAlarm;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        logOut=findViewById(R.id.LogOutId);
        add=findViewById(R.id.AddActivityId);
        show=findViewById(R.id.ShowActivityId);
        home=findViewById(R.id.HomeId);
        addAlarm=findViewById(R.id.AddAlarmId);
        deleteAlarm=findViewById(R.id.DeleteAlarmId);
        updateAlarm=findViewById(R.id.UpdateId);
        updateAlarm.setOnClickListener(this);
        deleteAlarm.setOnClickListener(this);
        logOut.setOnClickListener(this);
        add.setOnClickListener(this);
        show.setOnClickListener(this);
        home.setOnClickListener(this);
        addAlarm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.LogOutId){
            FirebaseAuth.getInstance().signOut();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user!=null){
                Toast.makeText(getApplicationContext(), "SignOut not successfully", Toast.LENGTH_LONG).show();
            }
            else {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        }
        if(v.getId()==R.id.HomeId){
            Intent intent = new Intent(Profile.this, MainActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.ShowActivityId){
            Intent intent = new Intent(Profile.this, ShowActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.AddActivityId){
            Intent intent = new Intent(Profile.this, AddActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.AddAlarmId) {
            Intent intent = new Intent(Profile.this, MainActivity2.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.DeleteAlarmId){
            Intent intent = new Intent(Profile.this, DeleteAlarm.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.UpdateId){
            Intent intent = new Intent(Profile.this, UpdateAlarm.class);
            startActivity(intent);
        }
    }
}