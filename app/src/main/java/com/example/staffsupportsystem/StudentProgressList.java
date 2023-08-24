package com.example.staffsupportsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class StudentProgressList extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8;
    Button btn1,btn2;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_progress_list);



        ed1 = findViewById(R.id.studentname);
        ed2 = findViewById(R.id.rollno);
        ed3 = findViewById(R.id.ut1marks);
        ed4 = findViewById(R.id.midsemmarks);
        ed5 = findViewById(R.id.ut2marks);
        ed6 = findViewById(R.id.total);
        ed7 = findViewById(R.id.percent);
        ed8 = findViewById(R.id.cgpa);

        btn1 = findViewById(R.id.progress_report);
        btn2 = findViewById(R.id.progress_report2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                markscal();



            }
        });
    }


    public void markscal()
    {
        int ut1,mid,ut2,total;
        double percent,cgpa;

        ut1 = Integer.parseInt(ed3.getText().toString());
        mid = Integer.parseInt(ed4.getText().toString());
        ut2 = Integer.parseInt(ed5.getText().toString());

        total = ut1 + mid + ut2;
        ed6.setText(String.valueOf(total));

        percent = total*1.67;
        ed7.setText(String.valueOf(percent));

        cgpa = percent/9.8;
        ed8.setText(String.valueOf(cgpa));

        //Toast.makeText(StudentProgressList.this, "Please fill the all fields", Toast.LENGTH_SHORT).show();

    }

    public void clear()
    {
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        ed5.setText("");
        ed6.setText("");
        ed7.setText("");
        ed8.setText("");
        ed1.requestFocus();

        Toast.makeText(this, "All data has been cleared", Toast.LENGTH_SHORT).show();

    }
}