package com.dev5151.careof.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dev5151.careof.Activities.MainActivity;
import com.dev5151.careof.Models.UserModel;
import com.dev5151.careof.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BasicFragment extends Fragment {

    DatabaseReference basicRef;
    DatabaseReference userRef;
    EditText edt;
    TextView question;
    List<String> questionsList;
    ImageButton btnSubmit;
    String name;
    String email;
    String age;
    String gender;
    private int questionCounter = 0;
    private int questionCountTotal;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic, container, false);
        initViews(view);
        fetchBasicQuestions();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitInfo();
            }
        });
        return view;
    }

    private void initViews(View view) {
        edt = view.findViewById(R.id.edt);
        questionsList = new ArrayList<>();
        basicRef = FirebaseDatabase.getInstance().getReference().child("questions").child("basic");
        userRef = FirebaseDatabase.getInstance().getReference().child("users");
        btnSubmit = view.findViewById(R.id.btn_submit);
        question = view.findViewById(R.id.question);
    }

    private void fetchBasicQuestions() {
        basicRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                questionsList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String question = postSnapshot.getValue(String.class);
                    questionsList.add(question);
                }
                questionCountTotal = questionsList.size();
                question.setText(questionsList.get(questionCounter));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void nextQuestion() {
        if (questionCounter < questionCountTotal) {
            question.setText(questionsList.get(questionCounter));
        } else {
            MainActivity.flag = 1;
            MainActivity.getCategoryInterface().switchToFragmentCategory();
        }
    }

    private void submitInfo() {
        if (questionCounter == 0) {
            name = edt.getText().toString();
            edt.setText(null);
            questionCounter++;
            nextQuestion();
        } else if (questionCounter == 1) {
            email = edt.getText().toString();
            edt.setText(null);
            questionCounter++;
            nextQuestion();
        } else if (questionCounter == 2) {
            age = edt.getText().toString();
            edt.setText(null);
            questionCounter++;
            nextQuestion();
        } else if (questionCounter == 3) {
            gender = edt.getText().toString();
            edt.setText(null);
            userRef.child(email).setValue(new UserModel(name, email, age, gender));
            questionCounter++;
            nextQuestion();
        }
    }

}
