package com.example.staffsupportsystem.ui.videocall;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VideocallViewModel  extends ViewModel {
    private final MutableLiveData<String> mText;

    public VideocallViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Virtual meetings with real results...");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
