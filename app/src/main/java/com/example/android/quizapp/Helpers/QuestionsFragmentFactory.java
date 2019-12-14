package com.example.android.quizapp.Helpers;

import androidx.fragment.app.Fragment;
import com.example.android.quizapp.Data.Question;
import com.example.android.quizapp.InputTextFragment;
import com.example.android.quizapp.MultiCheckFragment;

public class QuestionsFragmentFactory {
    public static Fragment makeQuestionFragment(Question question) {
        switch (question.getType()) {
            case InputText:
                return new InputTextFragment(question);
            case MultiText:
                return new MultiCheckFragment(question);
            case SingleText:
                return null;
        }
        return null;
    }
}
