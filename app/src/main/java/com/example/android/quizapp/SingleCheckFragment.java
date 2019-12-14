package com.example.android.quizapp;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.android.quizapp.Data.Question;

public final class SingleCheckFragment extends Fragment {

    private Question question;
    private Button submitButton;
    private RadioButton yesRadioButton;
    private RadioButton noRadioButton;

    public interface SingleCheckFragmentListener {
        void didSubmit(boolean isCorrect);
    }

    private SingleCheckFragmentListener listener;

    public SingleCheckFragment(Question question) {
        this.question = question;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_single_check, container, false);

        TextView questionName = view.findViewById(R.id.single_check_question_name);
        questionName.setText(question.getQuestion());

        yesRadioButton = view.findViewById(R.id.single_check_radio_button_yes);
        noRadioButton = view.findViewById(R.id.single_check_radio_button_no);

        submitButton = view.findViewById(R.id.single_check_submit_button);
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
        if (context instanceof SingleCheckFragmentListener) {
            listener = (SingleCheckFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SingleCheckFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private boolean validate() {
        String correctAnswer = question.getCorrectAnswers().get(0);

        if (correctAnswer.equals("YES")) {
            return yesRadioButton.isChecked();
        } else {
            return noRadioButton.isChecked();
        }
    }
}
