package com.example.staffsupportsystem;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    Button hodlogin;
    Button stafflogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         hodlogin= findViewById(R.id.hod_login);
         hodlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

         stafflogin = findViewById(R.id.staff_login);
        stafflogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
    }

}




