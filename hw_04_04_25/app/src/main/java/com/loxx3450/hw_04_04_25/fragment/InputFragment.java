package com.loxx3450.hw_04_04_25.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.loxx3450.hw_04_04_25.R;
import com.loxx3450.hw_04_04_25.databinding.DisplayFragmentBinding;
import com.loxx3450.hw_04_04_25.viewModel.SharedViewModel;

public class InputFragment extends Fragment {

    private SharedViewModel viewModel;
    private EditText stringInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_fragment, container, false);

        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        stringInput = view.findViewById(R.id.stringInput);

        Button updateStringButton = view.findViewById(R.id.updateStringButton);
        updateStringButton.setOnClickListener(v -> updateViewModel());

        return view;
    }

    private void updateViewModel() {
        String newValue = stringInput.getText().toString();
        viewModel.setSharedString(newValue);
    }


}
