package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;
    private Button save,move;
    private EditText EmailText,PasswordText;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        save=findViewById(R.id.SignUpSaveBtn);
        move=findViewById(R.id.SignUpMoveSignInButton);
        EmailText=findViewById(R.id.SignUpEmailId);
        PasswordText=findViewById(R.id.SignUpPassword);
        progressBar=findViewById(R.id.ProgressBarId);
        save.setOnClickListener(this);
        move.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.SignUpMoveSignInButton){
            Intent intent = new Intent(SignUp.this, SignIn.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.SignUpSaveBtn) {
            progressBar.setVisibility(View.VISIBLE);
            String email = EmailText.getText().toString().trim();
            String password = PasswordText.getText().toString().trim();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Account is created successfully", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(SignUp.this, SignIn.class);
                            startActivity(intent);
                        } else {
                            Exception exception = task.getException();
                            if (exception != null) {
                                exception.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Account can not create: " + exception.getMessage(), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Account can not create", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }

    }
}