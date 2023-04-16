package com.example.staffsupportsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProgressReport_activity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    ClassAdpter classAdpter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ClassItem> classItems = new ArrayList<>();
    Toolbar toolbar;
    // DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_report);

        // dbHelper = new DbHelper(this);

        fab =  findViewById(R.id.fab_main2);
        fab.setOnClickListener(v-> showDialog());

        //  loadData();

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        classAdpter = new ClassAdpter(this,classItems);
        recyclerView.setAdapter(classAdpter);
        classAdpter.setOnItemClickListener(position -> gotoItemActivity(position));
        setToolbar();
    }

//    private void loadData() {
//        Cursor cursor = dbHelper.getClassTable();
//
//        classItems.clear();
//        while (cursor.moveToNext()){
//            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DbHelper.C_ID));
//            @SuppressLint("Range") String className = cursor.getString(cursor.getColumnIndex(DbHelper.CLASS_NAME_KEY));
//            @SuppressLint("Range") String subjectName = cursor.getString(cursor.getColumnIndex(DbHelper.SUBJECT_NAME_KEY));
//
//            classItems.add(new ClassItem(id,className,subjectName));
//        }
//    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        TextView title = toolbar.findViewById(R.id.title_toolbar);
        TextView subtitle = toolbar.findViewById(R.id.subtitle_toolbar);
        ImageButton back = toolbar.findViewById(R.id.back);
        ImageButton save = toolbar.findViewById(R.id.save);

        title.setText("Progress Report Section");
        subtitle.setVisibility(View.GONE);
        back.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);
    }

    private void gotoItemActivity(int position) {
        Intent intent = new Intent(this, StudentListActivity.class);
        intent.putExtra("className",classItems.get(position).getClassName());
        intent.putExtra("subjectName",classItems.get(position).getSubjectName());
        intent.putExtra("position",position);
        startActivity(intent);

    }

    private void showDialog(){
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),MyDialog.CLASS_ADD_DIALOG);
        dialog.setListener((className,subjectName)->addclass(className,subjectName));

    }

    private void addclass(String className, String subjectName) {

        //   long cid = dbHelper.addClass(className,subjectName);
        ClassItem classItem = new ClassItem(className, subjectName);
        classItems.add(classItem);
        classAdpter.notifyDataSetChanged();

    }}