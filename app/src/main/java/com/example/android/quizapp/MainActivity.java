package com.example.android.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public final class MainActivity extends AppCompatActivity {

    public static final String USERNAME= "com.example.android.quizapp.username";
    public static final String SCORE= "com.example.android.quizapp.score";
    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        scoreTextView = findViewById(R.id.main_score_text_view);
    }

    public void startQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        EditText editText = findViewById(R.id.username_edit_text);
        String insertedUsername = editText.getText().toString();
        intent.putExtra(USERNAME, insertedUsername);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String score = data.getStringExtra(MainActivity.SCORE);
                String username = data.getStringExtra(MainActivity.USERNAME);

                if (!score.isEmpty()) {
                    String result = "Last result for user " + username + " - " + score;
                    scoreTextView.setText(result);
                }
            }
        }
    }
}
