package com.hamzajbr.fannak_user.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamzajbr.fannak_user.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoOrdersFragment extends Fragment {

    public NoOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_orders, container, false);
    }
}
