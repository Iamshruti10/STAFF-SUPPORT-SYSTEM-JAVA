package com.example.staffsupportsystem.ui.submissionReport;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SubmissionReportViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SubmissionReportViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Submit a report, be a star at work!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}