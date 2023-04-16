package com.example.staffsupportsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;

public class LoginActivity extends AppCompatActivity {
     private TextView Email, Password;
      Button loginbtn;
      TextView gotoSignUppage;
      private FirebaseAuth auth;
    private static final String TAG ="LoginActivity";
    DatabaseReference databasereference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://staff-support-system-a659a-default-rtdb.firebaseio.com/");
    @Override
    protected  void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.LoginEmail);
        Password = findViewById(R.id.LoginPassword);
        loginbtn = findViewById(R.id.LOGIN);
        gotoSignUppage = findViewById(R.id.gotosignup);
        TextView forgot_pwd;


        //Goto SignUp page On click Listner
        gotoSignUppage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

//        go to forgot pwd
        forgot_pwd = findViewById(R.id.forpwd);
        forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotppssActivity.class);
                startActivity(intent);

            }
        });


        auth = FirebaseAuth.getInstance();



//        login activity
        loginbtn.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                String Emailtxt = Email.getText().toString().trim();
                String pswdtxt = Password.getText().toString().trim();

            if (TextUtils.isEmpty(Emailtxt)) {
                    Toast.makeText(LoginActivity.this, "Please enter your Email", Toast.LENGTH_SHORT).show();
                    Email.setError("Email is Required");
                    Email.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(Emailtxt).matches()) {
                    Toast.makeText(LoginActivity.this, "Please re-enter your email", Toast.LENGTH_SHORT).show();
                    Email.setError("Valid email is required");
                    Email.requestFocus();
                }else if (TextUtils.isEmpty(pswdtxt)) {
                Toast.makeText(LoginActivity.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
                Password.setError("Password is Required");
                Password.requestFocus();
            }
            loginUser(Emailtxt,pswdtxt);
            }

            private void loginUser(String emailtxt, String pswdtxt) {
                auth.signInWithEmailAndPassword(emailtxt, pswdtxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity1.class);
                            startActivity(intent);
                            finish();
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                Email.setError("User does not exit, please Register First");
                                Email.requestFocus();
                            } catch (Exception e) {
                                Log.e(TAG, e.getMessage());
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }

                    }

                });


            }
       });
    }
}