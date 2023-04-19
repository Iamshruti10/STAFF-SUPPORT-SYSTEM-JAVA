package com.example.staffsupportsystem.ui.progressReport;

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

import com.example.staffsupportsystem.ProgressReport_activity;
import com.example.staffsupportsystem.R;
import com.example.staffsupportsystem.databinding.FragmentProgressReportBinding;

public class ProgressReportFragment extends Fragment {
    Button report;


    private FragmentProgressReportBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProgressReportViewModel progressReportViewModel =
                new ViewModelProvider(this).get(ProgressReportViewModel.class);

        binding = FragmentProgressReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textProgressReport;
        progressReportViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        report = root.findViewById(R.id.Progress_report);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Starting Activity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), ProgressReport_activity.class);
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