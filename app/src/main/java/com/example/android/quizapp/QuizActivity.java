package com.example.android.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.android.quizapp.Data.DatabaseStore;

public class QuizActivity extends FragmentActivity
        implements QuestionMultiCheck.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);

        // Get the Intent that started this activity and extract the string
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.USERNAME);

//        Question[] list = QuestionsFactory.make();
//        message = list[0].getQuestion();

        // Capture the layout's TextView and set the string as its text
//        TextView textView = findViewById(R.id.username_textview);
//        textView.setText(message);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (findViewById(R.id.fragment_container)!= null) {
            QuestionMultiCheck firstFragment = new QuestionMultiCheck();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, firstFragment)
                    .commit();
        }
    }

    @Override
    public void didSubmit(boolean isCorrect) {
        Log.d("QuizActivity", "didSubmit");
    }
}
