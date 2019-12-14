package com.example.android.quizapp.Helpers;

import androidx.fragment.app.Fragment;
import com.example.android.quizapp.Data.Question;
import com.example.android.quizapp.InputTextFragment;
import com.example.android.quizapp.MultiCheckFragment;
import com.example.android.quizapp.SingleCheckFragment;

public class QuestionsFragmentFactory {
    public static Fragment makeQuestionFragment(Question question) {
        switch (question.getType()) {
            case InputText:
                return new InputTextFragment(question);
            case MultiCheck:
                return new MultiCheckFragment(question);
            case SingleCheck:
                return new SingleCheckFragment(question);
        }
        return null;
    }
}
