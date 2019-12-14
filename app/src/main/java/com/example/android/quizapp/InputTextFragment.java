package com.example.android.quizapp;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.android.quizapp.Data.Question;

public class InputTextFragment extends Fragment {
    private Question question;
    private Button submitButton;
    private EditText answerEditText;
    private InputTextFragmentListener listener;

    public interface InputTextFragmentListener {
        void didSubmit(boolean isCorrect);
    }

    public InputTextFragment(Question question) {
        this.question = question;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_text, container, false);

        TextView questionName = view.findViewById(R.id.input_text_question_name);
        questionName.setText(question.getQuestion());

        answerEditText = view.findViewById(R.id.input_text_answer_edit_text);

        submitButton = view.findViewById(R.id.input_text_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.didSubmit(validate());
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InputTextFragmentListener) {
            listener = (InputTextFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InputTextFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private boolean validate() {
        String userAnswer = String.valueOf(answerEditText.getText());
        String correctAnswer = question.getCorrectAnswers().get(0);

        return userAnswer.equals(correctAnswer);
    }
}
