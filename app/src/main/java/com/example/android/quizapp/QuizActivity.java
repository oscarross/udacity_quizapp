package com.example.android.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.quizapp.Data.DatabaseStore;

public class QuizActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.USERNAME);

//        Question[] list = QuestionsFactory.make();
//        message = list[0].getQuestion();

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.username_textview);
        textView.setText(message);
    }
}
