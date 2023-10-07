package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private Button saveBtn,moveBtn,moveForget;
    private EditText EmailText,PasswordText;
    private ProgressBar progressBar;
    boolean isProgressVisible = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.ProgressBarId);
        saveBtn = findViewById(R.id.SignInSaveBtn);
        moveBtn = findViewById(R.id.SignInMoveSignUpButton);
        EmailText=findViewById(R.id.SignInEmailId);
        PasswordText=findViewById(R.id.SignInPassword);
        moveForget=findViewById(R.id.SignInMoveForgetButton);
        saveBtn.setOnClickListener(this);
        moveBtn.setOnClickListener(this);
        moveForget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.SignInMoveSignUpButton){
            Intent intent = new Intent(SignIn.this, SignUp.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.SignInMoveForgetButton){
            Intent intent = new Intent(SignIn.this, ForgetPassword.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.SignInSaveBtn){
            progressBar.setVisibility(View.VISIBLE);
            String email = EmailText.getText().toString().trim();
            String password = PasswordText.getText().toString().trim();
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "SignIn successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignIn.this, Profile.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "SignIn not successfully", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }
    }
}