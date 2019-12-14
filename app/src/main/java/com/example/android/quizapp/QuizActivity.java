package com.example.android.quizapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.android.quizapp.Data.DatabaseStore;
import com.example.android.quizapp.Data.Question;
import com.example.android.quizapp.Helpers.QuestionsFragmentFactory;

import java.util.List;

public class QuizActivity extends FragmentActivity implements
        MultiCheckFragment.MultiCheckFragmentListener,
        SingleCheckFragment.SingleCheckFragmentListener,
        InputTextFragment.InputTextFragmentListener {

    private DatabaseStore databaseStore = new DatabaseStore();
    private int correctAnswerCounter = 0;
    private int wrongAnswerCounter = 0;
    private int currentQuestionIndex = 0;
    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);

        databaseStore.loadData(getApplicationContext());

        scoreTextView = findViewById(R.id.score_text_view);
        updateScoreTextView();

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
            updateScoreTextView();
            showQuestion(questions);
        } else {

        }
    }

    private void updateScoreTextView() {
        String message = "Correct: " + correctAnswerCounter + " / Wrong: " + wrongAnswerCounter;
        scoreTextView.setText(message);
    }

    private void showQuestion(List<Question> questions) {
        if (questions.size() > currentQuestionIndex) {
            Question question = questions.get(currentQuestionIndex);

            Fragment fragment = QuestionsFragmentFactory.makeQuestionFragment(question);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }

}
