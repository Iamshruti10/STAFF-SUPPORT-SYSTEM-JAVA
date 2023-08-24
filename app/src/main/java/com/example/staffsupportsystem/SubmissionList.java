package com.example.staffsupportsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SubmissionList extends AppCompatActivity {

    FloatingActionButton fab;

    private ArrayList<submissionItem> submissionItems = new ArrayList<>();
    DbHelper dbHelper;
    Toolbar toolbar;

    private   MyCalendar calendar ;

    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_list);

        dbHelper = new DbHelper(this);

        fab =  findViewById(R.id.fab_main);
        fab.setOnClickListener(v-> showDialog());

        setToolbar();

//        studentAdapter = new StudentAdapter(this, submissionItem);
//        recyclerView.setAdapter(studentAdapter);
//        studentAdapter.setOnItemClickListener(position -> changeStatus(position));
//        loadStatusData();
    }

    private void loadStatusData() {
    }

    private void showDialog(){
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),MyDialog.ADD_SUBMISSION_DIALOG);
        dialog.setListener((sub_Name,date)->addSubmission(sub_Name,date));
    }

    private void addSubmission(String Sub_name, String date) {
//        long  cid =  dbHelper.addSubmission(sub_Name,date);
//        submissionItems = new submissionItem(sub_Name,date);
//
//        submissionItems.add(submissionItem);
//        submissionItems.notifyDataSetChanged();

    }
//    private void changeStatus(int position) {
//        String status = studentItems.get(position).getStatus();
//
//        if (status.equals("P")) status ="A";
//        else status ="P";
//
//        studentItems.get(position).setStatus(status);
//        studentAdapter.notifyItemChanged(position);
//    }



    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        TextView title = toolbar.findViewById(R.id.title_toolbar);
        TextView subtitle = toolbar.findViewById(R.id.subtitle_toolbar);
        ImageButton back = toolbar.findViewById(R.id.back);
        ImageButton save = toolbar.findViewById(R.id.save);


        title.setText("Submission Status");
        subtitle.setVisibility(View.GONE);
        back.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);

    }


}