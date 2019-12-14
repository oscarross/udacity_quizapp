package com.example.android.quizapp.Data;

import java.util.List;

public final class Question {
    public enum Type {
        MultiText,
        SingleText,
        InputText;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public Type getType() {
        return type;
    }

    private String question;
    private List<String> answers;
    private List<String> correctAnswers;
    private Type type;

    Question(String question, List<String> answears, List<String> correctAnswers, Type type) {
        this.answers = answears;
        this.correctAnswers = correctAnswers;
        this.question = question;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", correctAnswers=" + correctAnswers +
                ", answers=" + answers +
                ", type=" + type +
                '}';
    }
}