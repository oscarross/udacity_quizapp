package com.example.android.quizapp;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.android.quizapp.Data.DatabaseStore;
import com.example.android.quizapp.Data.Question;
import com.example.android.quizapp.Helpers.QuestionsFragmentFactory;

import java.util.List;

public class QuizActivity extends FragmentActivity implements
        MultiCheckFragment.MultiCheckFragmentListener,
        InputTextFragment.InputTextFragmentListener {

    private DatabaseStore databaseStore = new DatabaseStore();
    private int correctAnswerCounter = 0;
    private int wrongAnswerCounter = 0;
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);

        databaseStore.loadData(getApplicationContext());

        if (findViewById(R.id.fragment_container)!= null) {
            showQuestion(databaseStore.getQuestions());
        }
    }

    @Override
    public void didSubmit(boolean isCorrect) {
        Log.d("QuizActivity", "didSubmit");
        Log.d("QuizActivity", String.valueOf(isCorrect));

        if (isCorrect) {
            correctAnswerCounter++;
        } else {
            wrongAnswerCounter++;
        }

        List<Question> questions = databaseStore.getQuestions();

        currentQuestionIndex++;
        if (questions.size() > currentQuestionIndex) {
            showQuestion(questions);
        } else {

        }
    }

    private void showQuestion(List<Question> questions) {
        if (questions.size() > currentQuestionIndex) {
            Question question = questions.get(currentQuestionIndex);

            Fragment fragment = QuestionsFragmentFactory.makeQuestionFragment(question);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

}
