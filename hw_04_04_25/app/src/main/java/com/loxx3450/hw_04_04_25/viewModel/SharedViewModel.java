package com.loxx3450.hw_04_04_25.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<String> sharedString = new MutableLiveData<>("Nothing is chosen yet...");

    public LiveData<String> getSharedString() {
        return sharedString;
    }

    public void setSharedString(String value) {
        sharedString.setValue(value);
    }

}
