package com.example.staffsupportsystem.ui.videocall;

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

import com.example.staffsupportsystem.Online_meet;
import com.example.staffsupportsystem.R;
import com.example.staffsupportsystem.databinding.FragmentVideocallBinding;

public class VideocallFragment extends Fragment {

    private FragmentVideocallBinding binding;
    Button start_videocall;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VideocallViewModel videocallViewModel  =
                new ViewModelProvider(this).get(VideocallViewModel.class);

        binding =  FragmentVideocallBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textVideocall;
        videocallViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        start_videocall= root.findViewById(R.id.start_videocall);
        start_videocall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Starting Activity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), Online_meet.class);
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