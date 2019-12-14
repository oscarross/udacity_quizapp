package com.example.android.quizapp;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.android.quizapp.Data.Question;
import java.util.ArrayList;
import java.util.List;

public final class MultiCheckFragment extends Fragment {
    private Question question;
    private Button submitButton;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private MultiCheckFragmentListener listener;

    public interface MultiCheckFragmentListener {
        void didSubmit(boolean isCorrect);
    }

    public MultiCheckFragment(Question question) {
        this.question = question;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_multi_check, container, false);

        TextView questionName = view.findViewById(R.id.multi_check_question_name);
        questionName.setText(question.getQuestion());

        checkBox1 = view.findViewById(R.id.multi_check_answear_1);
        checkBox1.setText(question.getAnswers().get(0));
        checkBox2 = view.findViewById(R.id.multi_check_answear_2);
        checkBox2.setText(question.getAnswers().get(1));
        checkBox3 = view.findViewById(R.id.multi_check_answear_3);
        checkBox3.setText(question.getAnswers().get(2));
        checkBox4 = view.findViewById(R.id.multi_check_answear_4);
        checkBox4.setText(question.getAnswers().get(3));

        submitButton = view.findViewById(R.id.multi_check_submit_button);
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
        if (context instanceof MultiCheckFragmentListener) {
            listener = (MultiCheckFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private boolean validate() {
        List<String> checkedAnswers = new ArrayList<>();

        if (checkBox1.isChecked()) {
            checkedAnswers.add(String.valueOf(checkBox1.getText()));
        }

        if (checkBox2.isChecked()) {
            checkedAnswers.add(String.valueOf(checkBox2.getText()));
        }

        if (checkBox3.isChecked()) {
            checkedAnswers.add(String.valueOf(checkBox3.getText()));
        }

        if (checkBox4.isChecked()) {
            checkedAnswers.add(String.valueOf(checkBox4.getText()));
        }

        return question.getCorrectAnswers().equals(checkedAnswers);
    }
}
