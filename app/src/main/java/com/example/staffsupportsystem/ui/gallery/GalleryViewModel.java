package com.example.staffsupportsystem.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Be Here! Every Day. All Day. All The Way!");
    }

    public LiveData<String> getText() {
        return mText;
    }
}