package com.dev5151.careof.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.dev5151.careof.Activities.MainActivity;
import com.dev5151.careof.R;

public class CategoryFragment extends Fragment {

    LottieAnimationView animationView1, animationView2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);

        animationView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.flag == 0) {
                    MainActivity.getCategoryInterface().switchToFragmentBasic();
                } else {
                    Toast.makeText(getActivity(), "Already Entered the Basic info, move to Lifestyle quiz", Toast.LENGTH_LONG).show();
                }
            }
        });

        animationView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.flag == 1) {
                    MainActivity.getCategoryInterface().switchToFragmentLifeStyle();
                } else {
                    Toast.makeText(getActivity(), "Attempt Basic first", Toast.LENGTH_LONG).show();

                }
            }
        });

        return view;
    }

    private void initView(View view) {
        animationView1 = view.findViewById(R.id.animation1);
        animationView2 = view.findViewById(R.id.animation2);
    }

}
