package com.example.staffsupportsystem.ui.gallery;

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
import com.example.staffsupportsystem.ForgotppssActivity;
import com.example.staffsupportsystem.LoginActivity;
import com.example.staffsupportsystem.R;
import com.example.staffsupportsystem.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    Button take_attendance;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        take_attendance = root.findViewById(R.id.take_attendace1);
        take_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(getContext(), "Starting Activity", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(getContext(),Attendace_Activity.class);
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