package com.example.android.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.android.quizapp.Data.DatabaseStore;

public class MainActivity extends AppCompatActivity {

    private DatabaseStore databaseStore = new DatabaseStore();
    public static final String USERNAME= "com.example.android.quizapp.username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        databaseStore.loadData(getApplicationContext());
    }

    /** Called when the user taps the Send button */
    public void startQuiz(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        EditText editText = findViewById(R.id.username_edit_text);
        String message = editText.getText().toString();
        intent.putExtra(USERNAME, message);
        startActivity(intent);
    }

}
