package com.example.staffsupportsystem;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {


    private TextView Fullname,email, phone , password;
    FirebaseAuth auth;
    private static final String TAG ="SignupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        auth = FirebaseAuth.getInstance();
        Fullname = findViewById(R.id.name1);
         email = findViewById(R.id.SignEmail);
         phone = findViewById(R.id.mb_no);
          password = findViewById(R.id.SignPassword);


        final Button registerbtn = findViewById(R.id.DosignIN);

       registerbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               //get data from edittxt into String Variable
               final String fullnametxt = Fullname.getText().toString();
               final String emailtxt = email.getText().toString();
               final String phonetxt = phone.getText().toString();
               final String passwordtxt = password.getText().toString();


//               validate Mobile Number using Matcher & Pattern Expressions
              String mobileRegeX = "[6-9][0-9]{9}"; //First no can be {6,8,9}& rest 9 no.s
               Matcher mobileMatcher;
               Pattern mobilePattern = Pattern.compile(mobileRegeX);
               mobileMatcher = mobilePattern.matcher(phonetxt);

               // check if user fill all fields before sending data to firebase
               if (TextUtils.isEmpty(fullnametxt)) {
                   Toast.makeText(SignupActivity.this, "Please enter your full name", Toast.LENGTH_SHORT).show();
                   Fullname.setError("Full Name is Required");
                   Fullname.requestFocus();
               } else if (TextUtils.isEmpty(emailtxt)) {
                   Toast.makeText(SignupActivity.this, "Please enter your Email", Toast.LENGTH_SHORT).show();
                   email.setError("Email is Required");
                   email.requestFocus();
               } else if (!Patterns.EMAIL_ADDRESS.matcher(emailtxt).matches()) {
                   Toast.makeText(SignupActivity.this, "Please re-enter your email", Toast.LENGTH_SHORT).show();
                   email.setError("Valid email is required");
                   email.requestFocus();
               } else if (TextUtils.isEmpty(phonetxt)) {
                   Toast.makeText(SignupActivity.this, "Please enter your Mobile Number", Toast.LENGTH_SHORT).show();
                   phone.setError("Mobile Number is Required");
                   phone.requestFocus();
               } else if(phonetxt.length() != 10){
                   Toast.makeText(SignupActivity.this, "please re-enter your Mobile Number", Toast.LENGTH_SHORT).show();
                   phone.setError("Mobile no. should be 10 digits");
                   phone.requestFocus();
               }else if(!mobileMatcher.find()){
                   Toast.makeText(SignupActivity.this, "please re-enter your Mobile Number", Toast.LENGTH_SHORT).show();
                   phone.setError("Mobile no. is not a Valid");
                   phone.requestFocus();
               }
               else if (TextUtils.isEmpty(passwordtxt)) {
                   Toast.makeText(SignupActivity.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
                   password.setError("Password is Required");
                   password.requestFocus();
               }else {
                   registerUser(fullnametxt, emailtxt,phonetxt ,passwordtxt);
               }
           }
       });


    }
    private void registerUser(String fullnametxt, String emailtxt, String phonetxt, String passwordtxt) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(emailtxt, passwordtxt)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();

//                          enter user data into firebase realtime database
                            ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(fullnametxt, emailtxt, phonetxt);
//
//                         Extracting user reference from database for "registered user"
                            DatabaseReference databasereference = FirebaseDatabase.getInstance().getReference("Registered users");
                            databasereference.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
//                                    send verification email
                                    firebaseUser.sendEmailVerification();

                                    Toast.makeText(SignupActivity.this, "Account Created.!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                    startActivity(intent);
                                    finish();
                                }
                            });
//
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                password.setError("Password is too weak. Kindly use a alphabets,numbers & special characters");
                                password.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                email.setError("This email is Invalid. Kindly re-enter");
                                email.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e) {
                                email.setError("User is already Registered with this email. Use another one ");
                                email.requestFocus();
                            } catch (Exception e) {
                                Log.e(TAG, e.getMessage());
                                Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
//
//                         }
                    }
                });


    }}