
package com.example.staffsupportsystem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SheetProgressReport extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<StudentItem> studentItems = new ArrayList<>();
    Toolbar toolbar;
    DbHelper dbHelper;

    long cid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_report);

        dbHelper = new DbHelper(this);

        fab =  findViewById(R.id.fab_main);
        fab.setOnClickListener(v-> showDialog());

        loadData();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        studentAdapter = new StudentAdapter(this,studentItems);
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.setOnItemClickListener(position -> gotoItemActivity(position));
        setToolbar();
    }

    private void loadData() {
        Cursor cursor = dbHelper.getStudentTable(cid);
        Log.i("1234567890","loadData: "+ cid);
        studentItems.clear();
        while (cursor.moveToNext()){
            long sid = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.S_ID));
            int roll = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.STUDENT_ROLL_KEY));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.STUDENT_NAME_KEY));
            studentItems.add(new StudentItem(sid,roll,name));
        }
        cursor.close();
    }


    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        TextView title = toolbar.findViewById(R.id.title_toolbar);
        TextView subtitle = toolbar.findViewById(R.id.subtitle_toolbar);
        ImageButton back = toolbar.findViewById(R.id.back);
        ImageButton save = toolbar.findViewById(R.id.save);

        title.setText("Student List");
        subtitle.setVisibility(View.GONE);
        back.setVisibility(View.INVISIBLE);
        save.setVisibility(View.INVISIBLE);
    }

    private void gotoItemActivity(int position) {
        Intent intent = new Intent(this,StudentProgressList.class);
        intent.putExtra("className",studentItems.get(position).getRoll());
        intent.putExtra("subjectName",studentItems.get(position).getName());
        intent.putExtra("position",position);
        intent.putExtra("eid",studentItems.get(position).getSid());
        startActivity(intent);

    }

    private void showDialog(){
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),MyDialog.STUDENT_ADD_DIALOG);
        dialog.setListener((roll,name)->addStudent(roll,name));
    }


    private void addStudent(String roll_string, String name) {
        int roll = Integer.parseInt(roll_string);
        long sid = dbHelper.addStudent(cid,roll,name);
        StudentItem studentItem = new StudentItem(sid , roll , name);
        studentItems.add(studentItem);
        studentAdapter.notifyDataSetChanged();
    }



    private void showUpdateStudentDialog(int position) {
        MyDialog dialog = new MyDialog(studentItems.get(position).getRoll(),studentItems.get(position).getName());
        dialog.show(getSupportFragmentManager(),MyDialog.STUDENT_UPDATE_DIALOG);
        dialog.setListener((roll_string,name)->updateStudent(position,name));
    }

    private void updateStudent(int position,  String name) {

        dbHelper.updateStudent(studentItems.get(position).getSid(),name);
        studentItems.get(position).setName(name);
        studentAdapter.notifyItemChanged(position);
    }

    private void deleteStudent(int position) {
//        dbHelper.deleteStudent(studentItems.get(position).getSid());
//        studentItems.remove(position);
//        adapter.notifyItemRemoved(position);
        dbHelper.deleteStudent(studentItems.get(position).getSid());
        studentItems.remove((position));
        studentAdapter.notifyItemRemoved(position);
    }
}