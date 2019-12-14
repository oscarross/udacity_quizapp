package com.example.android.quizapp.Data;

import android.content.Context;
import android.util.Log;
import com.example.android.quizapp.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseStore {
    public List<Question> getQuestions() {
        return questions;
    }

    private List<Question> questions = null;

    public void loadData(Context context) {
        List<Question> items = new ArrayList<>();

        try {
            JSONObject databaseJSON = new JSONObject(loadJSONFile(context));
            Log.d("JSON", databaseJSON.toString());
            JSONArray databaseJSONArray= databaseJSON.getJSONArray("questions");

            for(int index=0; index<databaseJSONArray.length(); index++) {
                JSONObject itemJSON = databaseJSONArray.getJSONObject(index);

                String questionName = itemJSON.get("question").toString();
                List<String> answers = getStringItems(itemJSON, "answers");
                List<String> correctAnswers = getStringItems(itemJSON, "correctAnswers");
                Question.Type type = Question.Type.valueOf(itemJSON.get("type").toString());

                Question question = new Question(
                        questionName,
                        answers,
                        correctAnswers,
                        type
                );

                items.add(question);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            questions = null;
            return;
        }

        questions = items;
    }

    private String loadJSONFile(Context context) {
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.database);

            int size = inputStream.available();

            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    private List<String> getStringItems(JSONObject jsonObject, String key) throws JSONException {
        JSONArray jsonArray = jsonObject.getJSONArray(key);
        List<String> objects = new ArrayList<String>();
        for(int i = 0; i < jsonArray.length(); i++){
            objects.add(jsonArray.getString(i));
        }

        return objects;
    }
}
