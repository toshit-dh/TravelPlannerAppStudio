package com.example.travelplanner.fragments;



import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.travelplanner.R;
import com.example.travelplanner.ui.login.Login;
import com.google.firebase.auth.FirebaseAuth;


public class ShareFragment extends Fragment {
    private FirebaseAuth mAuth;
    private Button yesbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Link Copied !", Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        //View view =  inflater.inflate(R.layout.fragment_share, container, false);
//        yesbtn = view.findViewById(R.id.btnyes);
//        ,nobtn = view.findViewById(R.id.btnno);
//        yesbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), Login.class));
//            }
//        });
//        nobtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fragmentManager = getParentFragmentManager();
//            fragmentManager.popBackStack();
//            }
//        });
        return null;
    }
}