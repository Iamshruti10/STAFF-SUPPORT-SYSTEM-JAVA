package com.example.staffsupportsystem.ui.submissionReport;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.staffsupportsystem.Attendace_Activity;
import com.example.staffsupportsystem.R;
import com.example.staffsupportsystem.SubmissionReportActivity;
import com.example.staffsupportsystem.databinding.FragmentSlideshowBinding;
import com.example.staffsupportsystem.databinding.FragmentSubmissionReportBinding;

public class SubmissionReportFragment extends Fragment {
    Button submission;

    private FragmentSubmissionReportBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SubmissionReportViewModel submissionReportViewModel =
                new ViewModelProvider(this).get(SubmissionReportViewModel.class);

        binding = FragmentSubmissionReportBinding .inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSubmissionReport;
        submissionReportViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        submission = root.findViewById(R.id.sub_report);
        submission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Starting Activity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SubmissionReportActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}