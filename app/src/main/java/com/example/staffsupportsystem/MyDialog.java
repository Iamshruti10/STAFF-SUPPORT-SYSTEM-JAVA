package com.example.staffsupportsystem;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialog extends DialogFragment {
    public static final String CLASS_ADD_DIALOG = "addClass";
    public static final String CLASS_UPDATE_DIALOG = "updateClass";
    public static final String STUDENT_ADD_DIALOG = "addstudent";
    public static final String STUDENT_UPDATE_DIALOG ="updateStudent" ;

    public static final String ADD_SUBMISSION_DIALOG ="addsubmission" ;


    private OnclickListener Listener;
    private int roll;
    private String name;

    public MyDialog(int roll, String name) {

        this.roll = roll;
        this.name = name;
    }

    public MyDialog() {

    }

    public interface  OnclickListener {
        void onClick(String text1,String text2);
    }
    public void setListener(OnclickListener listener){
        this.Listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    Dialog dialog = null;
    if(getTag().equals(CLASS_ADD_DIALOG))dialog =  getAddClassDialog();
    if(getTag().equals(STUDENT_ADD_DIALOG))dialog =  getAddStudentDialog();
    if(getTag().equals(CLASS_UPDATE_DIALOG))dialog =  getUpdateClassDialog();
    if(getTag().equals(STUDENT_UPDATE_DIALOG))dialog =  getUpdateStudentDialog();
    if(getTag().equals(ADD_SUBMISSION_DIALOG))dialog =  getSubmissionDialog();
    return dialog;
    }

    private Dialog getSubmissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);


        TextView title =  view.findViewById(R.id.titleDialog);
        title.setText("New Submission");

        EditText  Sub_name= view.findViewById(R.id.est01);
        EditText date = view.findViewById(R.id.edt02);

        Sub_name .setHint("Submission Name");
        date.setHint("Submtting On");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add =  view.findViewById(R.id.Add_btn);

        cancel.setOnClickListener(v-> dismiss());
        add.setOnClickListener(v ->{
            String roll = Sub_name .getText().toString();
            String name = date.getText().toString();
            Sub_name.setText("");
            date.setText("");
            Listener.onClick(roll,name);
        });

        return builder.create();
    }

    private Dialog getUpdateStudentDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);


        TextView title =  view.findViewById(R.id.titleDialog);
        title.setText("Update Student");

        EditText roll_edt = view.findViewById(R.id.est01);
        EditText name_edt = view.findViewById(R.id.edt02);

        roll_edt .setHint("Roll No");
        name_edt.setHint("Student Name");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add =  view.findViewById(R.id.Add_btn);
        add.setText("Update");
        roll_edt.setText(roll+"");
        roll_edt.setEnabled(false);
        name_edt.setText(name);
        cancel.setOnClickListener(v-> dismiss());
        add.setOnClickListener(v ->{
            String roll = roll_edt.getText().toString();
            String name = name_edt.getText().toString();
            Listener.onClick(roll,name);
        });

        return builder.create();

    }

    private Dialog getUpdateClassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);


        TextView title =  view.findViewById(R.id.titleDialog);
        title.setText("Update Class");

        EditText class_edt = view.findViewById(R.id.est01);
        EditText subject_edt = view.findViewById(R.id.edt02);

        class_edt.setHint("Class Name");
        subject_edt.setHint("Subject Name");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add =  view.findViewById(R.id.Add_btn);
        add.setText("Update");

        cancel.setOnClickListener(v-> dismiss());
        add.setOnClickListener(v ->{
            String className = class_edt.getText().toString();
            String subName = subject_edt.getText().toString();
            Listener.onClick(className,subName );
            dismiss();
        });
        return builder.create();
    }


    private Dialog getAddStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);


        TextView title =  view.findViewById(R.id.titleDialog);
        title.setText("Add New Student");

        EditText roll_edt = view.findViewById(R.id.est01);
        EditText name_edt = view.findViewById(R.id.edt02);

        roll_edt .setHint("Roll No");
        name_edt.setHint("Student Name");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add =  view.findViewById(R.id.Add_btn);

        cancel.setOnClickListener(v-> dismiss());
        add.setOnClickListener(v ->{
            String roll = roll_edt.getText().toString();
            String name = name_edt.getText().toString();
            roll_edt.setText("");
            name_edt.setText("");
            Listener.onClick(roll,name);
        });

        return builder.create();

    }

    private Dialog getAddClassDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        builder.setView(view);


        TextView title =  view.findViewById(R.id.titleDialog);
        title.setText("Add New Class");

        EditText class_edt = view.findViewById(R.id.est01);
        EditText subject_edt = view.findViewById(R.id.edt02);

        class_edt.setHint("Class Name");
        subject_edt.setHint("Subject Name");
        Button cancel = view.findViewById(R.id.cancel_btn);
        Button add =  view.findViewById(R.id.Add_btn);

        cancel.setOnClickListener(v-> dismiss());
        add.setOnClickListener(v ->{
            String className = class_edt.getText().toString();
            String subName = subject_edt.getText().toString();
           Listener.onClick(className,subName );
            dismiss();
        });
        return builder.create();
    }
}
