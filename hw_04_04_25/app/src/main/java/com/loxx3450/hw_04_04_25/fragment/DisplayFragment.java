package com.loxx3450.hw_04_04_25.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.loxx3450.hw_04_04_25.R;
import com.loxx3450.hw_04_04_25.databinding.DisplayFragmentBinding;
import com.loxx3450.hw_04_04_25.viewModel.SharedViewModel;

public class DisplayFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DisplayFragmentBinding binding = DisplayFragmentBinding.inflate(inflater, container, false);

        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        return binding.getRoot();
    }
}
