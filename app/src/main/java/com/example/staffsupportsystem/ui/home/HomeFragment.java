package com.example.staffsupportsystem.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.staffsupportsystem.About_us;
import com.example.staffsupportsystem.Diet_info;
import com.example.staffsupportsystem.Programs;
import com.example.staffsupportsystem.R;
import com.example.staffsupportsystem.databinding.FragmentHomeBinding;
import com.example.staffsupportsystem.infoSubmission;
import com.example.staffsupportsystem.infoVideoCall;
import com.example.staffsupportsystem.staff_members;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
       imageView = root.findViewById(R.id.att1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Diet_info.class));
            }
        });

        imageView = root.findViewById(R.id.lsspln1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Programs.class));
            }
        });

        imageView = root.findViewById(R.id.progress1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), About_us.class));
            }
        });


        imageView = root.findViewById(R.id.staffmembr1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), staff_members.class));
            }
        });

        imageView = root.findViewById(R.id.video1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), infoVideoCall.class));
            }
        });

        imageView = root.findViewById(R.id.submsn1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), infoSubmission.class));
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