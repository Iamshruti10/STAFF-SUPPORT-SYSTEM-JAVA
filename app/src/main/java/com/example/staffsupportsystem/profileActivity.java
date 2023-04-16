package com.example.staffsupportsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileActivity extends AppCompatActivity {

        private TextView textViewWelcome, textViewFullname, textViewEmail, textViewphn ;
        private String fullname, email,phoneno2;
//        private ImageView imageView;
        private FirebaseAuth autoProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewWelcome = findViewById(R.id.textview_show_welcome);
        textViewFullname = findViewById(R.id.show_fullname);
        textViewEmail = findViewById(R.id.show_email);
        textViewphn = findViewById(R.id.show_phn_no);

             autoProfile = FirebaseAuth.getInstance();
            FirebaseUser firebaseUser = autoProfile.getCurrentUser();

            if (firebaseUser == null){
                Toast.makeText(profileActivity.this, "Something went wrong! User's details are not available at the moment", Toast.LENGTH_SHORT).show();
            }else{
                showUserProfile(firebaseUser);
            }
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String UserID = firebaseUser.getUid();

//        Extracting user reference from database for "registered Users"
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Registered Users");
        reference.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
               if(readUserDetails != null){
                   fullname = firebaseUser.getDisplayName();
                   phoneno2 = readUserDetails.phone;
                   email = firebaseUser.getEmail();

                   textViewWelcome.setText("Welcome, " + fullname +"!");
                   textViewFullname.setText(fullname);
                   textViewphn.setText(phoneno2);
                   textViewEmail.setText(email);
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });


    }
}