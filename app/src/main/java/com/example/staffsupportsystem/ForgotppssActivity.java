package com.example.staffsupportsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgotppssActivity extends AppCompatActivity {


     private Button btnpwdreset;
     private EditText editTextpwdresetemailtext;
     private FirebaseAuth authprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotppss);

        editTextpwdresetemailtext = findViewById(R.id.editText_reset_password_email);
        btnpwdreset = findViewById(R.id.button_reset_password);


        btnpwdreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = editTextpwdresetemailtext.getText().toString();

                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(ForgotppssActivity.this, "Please Enter Registered Email", Toast.LENGTH_SHORT).show();
                    editTextpwdresetemailtext.setError("Email is Required");
                    editTextpwdresetemailtext.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    Toast.makeText(ForgotppssActivity.this, "Please enter Valid email", Toast.LENGTH_SHORT).show();
                    editTextpwdresetemailtext.setError("Valid email is required");
                    editTextpwdresetemailtext.requestFocus();
                }else{
                    resetPassword(Email);
                }
            }
        });


            }

    private void resetPassword(String Email) {
        authprofile = FirebaseAuth.getInstance();
        authprofile.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotppssActivity.this, "Please Check your Inbox for password reset link", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ForgotppssActivity.this,LoginActivity.class);

//                    clear stack to prevent user coming back to forgot password activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();   //close profile activity
                }else{
                    Toast.makeText(ForgotppssActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

