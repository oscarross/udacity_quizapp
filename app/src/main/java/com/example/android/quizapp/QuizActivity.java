package com.example.android.quizapp;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;

import com.example.android.quizapp.Data.DatabaseStore;
import com.example.android.quizapp.Data.Question;
import com.example.android.quizapp.Helpers.QuestionsFragmentFactory;

public class QuizActivity extends FragmentActivity implements MultiCheckFragment.MultiCheckFragmentListener {

    private DatabaseStore databaseStore = new DatabaseStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);

        databaseStore.loadData(getApplicationContext());

        if (findViewById(R.id.fragment_container)!= null) {
            Question question = databaseStore.getQuestions().get(0);
            MultiCheckFragment firstFragment = (MultiCheckFragment) QuestionsFragmentFactory.makeQuestionFragment(question);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, firstFragment)
                    .commit();
        }
    }

    @Override
    public void didSubmit(boolean isCorrect) {
        Log.d("QuizActivity", "didSubmit");
        Log.d("QuizActivity", String.valueOf(isCorrect));
    }
}
