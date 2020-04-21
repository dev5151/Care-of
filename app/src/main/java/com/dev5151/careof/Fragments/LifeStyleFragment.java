package com.dev5151.careof.Fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev5151.careof.Adapters.LifeStyleAdapter;
import com.dev5151.careof.Interfaces.OnClickInterface;
import com.dev5151.careof.Models.LifeStyleModel;
import com.dev5151.careof.Models.OptionsModel;
import com.dev5151.careof.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class LifeStyleFragment extends Fragment implements OnClickInterface {
    private RecyclerView recyclerView;
    private TextView tvQues, tvSubText;
    private DatabaseReference questionRef;
    LifeStyleAdapter lifeStyleAdapter;
    private List<LifeStyleModel> lifeStyleModelList;
    List<OptionsModel> optionsModelList;
    private int questionCounter;
    private int questionCountTotal;
    static OnClickInterface onClickInterface;
    ProgressBar loading, progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lifestyle, container, false);
        initViews(view);
        fetchQuestion();

        progressBar.setMax(questionCountTotal);
        onClickInterface = new OnClickInterface() {
            @Override
            public void onLifeStyleClick(OptionsModel optionsModel) {
                showNextQuestion();
            }
        };


        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_options);
        tvQues = view.findViewById(R.id.question);
        tvSubText = view.findViewById(R.id.sub_text);
        lifeStyleModelList = new ArrayList<>();
        optionsModelList = new ArrayList<>();
        questionCounter = 1;
        loading = view.findViewById(R.id.loading);
        progressBar = view.findViewById(R.id.progress_bar);
        questionRef = FirebaseDatabase.getInstance().getReference().child("questions").child("lifestyle");

    }

    private void fetchQuestion() {
        questionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lifeStyleModelList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    LifeStyleModel lifeStyleModel = dataSnapshot1.getValue(LifeStyleModel.class);
                    lifeStyleModelList.add(lifeStyleModel);
                }
                setData(lifeStyleModelList, 0);
                optionsModelList = lifeStyleModelList.get(0).getOptionsList();
                loading.setVisibility(View.GONE);
                lifeStyleAdapter = new LifeStyleAdapter(optionsModelList, getActivity(), lifeStyleModelList.get(0).getViewType(), onClickInterface);
                recyclerView.setAdapter(lifeStyleAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                questionCountTotal = lifeStyleModelList.size();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void setData(List<LifeStyleModel> lifeStyleModelList, int position) {
        String ques = lifeStyleModelList.get(position).getQues();
        String subText = lifeStyleModelList.get(position).getSubText();
        tvQues.setText(ques);
        tvSubText.setText(subText);
    }

    private void initAdapter(List<OptionsModel> optionsModelList) {
        loading.setVisibility(View.GONE);
        lifeStyleAdapter = new LifeStyleAdapter(optionsModelList, getActivity(), lifeStyleModelList.get(questionCounter).getViewType(), onClickInterface);
        recyclerView.setAdapter(lifeStyleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        lifeStyleAdapter.notifyDataSetChanged();
    }

    private void showNextQuestion() {
        if (questionCounter < questionCountTotal) {
            setData(lifeStyleModelList, questionCounter);
            optionsModelList = lifeStyleModelList.get(questionCounter).getOptionsList();
            initAdapter(optionsModelList);
            questionCounter++;
            progressBar.setProgress(questionCounter);
        } else {
            progressBar.setMax(questionCountTotal);
            finishQuiz();
        }
    }


    @Override
    public void onLifeStyleClick(OptionsModel optionsModel) {
        onClickInterface.onLifeStyleClick(optionsModel);
    }

    private void finishQuiz() {
        getActivity().finish();
    }
}
