package com.example.staffsupportsystem.ui.progressReport;

import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProgressReportViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ProgressReportViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Progress is one step closer to excellence.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}