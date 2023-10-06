package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    private Button SendEmailBtn,SignInMove;
    private EditText EmailId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_forget_password);
        SendEmailBtn=findViewById(R.id.ForgetPasswordBtn);
        EmailId=findViewById(R.id.ForgetEmailId);
        SignInMove=findViewById(R.id.ForgetToSignInBtn);
        SendEmailBtn.setOnClickListener(this);
        SignInMove.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.ForgetToSignInBtn){
            Intent intent = new Intent(ForgetPassword.this, SignIn.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.ForgetPasswordBtn){
            String email = EmailId.getText().toString().trim();

            if(email==""){
                Toast.makeText(getApplicationContext(), "Please fill up email Id", Toast.LENGTH_LONG).show();
                return;
            }
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Password reset email sent and reset password in your email.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to send password reset email.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}