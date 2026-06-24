package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.database.DBHelper;
import com.example.quizapp.model.Question;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    TextView tvQuestion, tvCount, tvTimer;
    RadioButton rbA, rbB, rbC, rbD;
    RadioGroup radioGroup;
    MaterialButton btnNext;
    android.widget.ProgressBar progressBar;
    ImageView imgQuestion;

    ArrayList<Question> list;

    int current = 0;
    int score = 0;

    String name;
    String difficulty;

    DBHelper db;
    CountDownTimer countDownTimer;
    boolean answered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        name = getIntent().getStringExtra("name");
        difficulty = getIntent().getStringExtra("difficulty");

        tvQuestion = findViewById(R.id.tvQuestion);
        tvCount = findViewById(R.id.tvCount);
        tvTimer = findViewById(R.id.tvTimer);

        rbA = findViewById(R.id.rbA);
        rbB = findViewById(R.id.rbB);
        rbC = findViewById(R.id.rbC);
        rbD = findViewById(R.id.rbD);

        radioGroup = findViewById(R.id.radioGroup);
        btnNext = findViewById(R.id.btnNext);
        progressBar = findViewById(R.id.progressBar);
        imgQuestion = findViewById(R.id.imgQuestion);

        db = new DBHelper(this);

        list = db.getQuestionsByDifficulty(difficulty);
        if (list == null || list.size() == 0) {
            list = db.getAllQuestions();
        }

        Collections.shuffle(list);

        progressBar.setMax(list.size());
        showQuestion();

        btnNext.setOnClickListener(v -> {
            if (!answered) {
                if (countDownTimer != null) countDownTimer.cancel();
                checkAnswer();
            }
        });
    }

    private void showQuestion() {
        Question q = list.get(current);

        tvCount.setText("Câu " + (current + 1) + "/" + list.size());
        progressBar.setProgress(current + 1);

        tvQuestion.setText(q.getQuestion());
        rbA.setText("A. " + q.getOptionA());
        rbB.setText("B. " + q.getOptionB());
        rbC.setText("C. " + q.getOptionC());
        rbD.setText("D. " + q.getOptionD());

        String imageName = q.getImage();
        if (imageName != null && !imageName.trim().isEmpty()) {
            int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            if (resId != 0) {
                imgQuestion.setImageResource(resId);
                imgQuestion.setVisibility(ImageView.VISIBLE);
            } else {
                imgQuestion.setVisibility(ImageView.GONE);
            }
        } else {
            imgQuestion.setVisibility(ImageView.GONE);
        }

        radioGroup.clearCheck();
        answered = false;

        rbA.setEnabled(true);
        rbB.setEnabled(true);
        rbC.setEnabled(true);
        rbD.setEnabled(true);

        rbA.setBackgroundColor(Color.TRANSPARENT);
        rbB.setBackgroundColor(Color.TRANSPARENT);
        rbC.setBackgroundColor(Color.TRANSPARENT);
        rbD.setBackgroundColor(Color.TRANSPARENT);

        startTimer();

        if (current == list.size() - 1)
            btnNext.setText("SUBMIT");
        else
            btnNext.setText("NEXT");
    }

    private void startTimer() {
        if (countDownTimer != null) countDownTimer.cancel();

        long timeMillis = 15000; // mặc định medium

        if ("easy".equals(difficulty)) {
            timeMillis = 20000;
        } else if ("medium".equals(difficulty)) {
            timeMillis = 15000;
        } else if ("hard".equals(difficulty)) {
            timeMillis = 10000;
        }

        countDownTimer = new CountDownTimer(timeMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Thời gian: " + millisUntilFinished / 1000 + " giây");
            }

            @Override
            public void onFinish() {
                if (!answered) {
                    checkAnswer();
                }
            }
        };

        countDownTimer.start();
    }

    private int getScorePerQuestion() {
        if ("easy".equals(difficulty)) return 1;
        if ("medium".equals(difficulty)) return 2;
        return 3; // hard
    }

    private String getDifficultyText(String difficulty) {
        if ("easy".equals(difficulty)) return "Dễ";
        if ("medium".equals(difficulty)) return "Trung bình";
        return "Khó";
    }

    private void checkAnswer() {
        if (answered) return;

        answered = true;

        rbA.setEnabled(false);
        rbB.setEnabled(false);
        rbC.setEnabled(false);
        rbD.setEnabled(false);

        int selectedId = radioGroup.getCheckedRadioButtonId();
        String answer = "";

        if (selectedId == R.id.rbA) answer = "A";
        else if (selectedId == R.id.rbB) answer = "B";
        else if (selectedId == R.id.rbC) answer = "C";
        else if (selectedId == R.id.rbD) answer = "D";

        String correctAnswer = list.get(current).getAnswer();

        // tô xanh đáp án đúng
        if (correctAnswer.equals("A")) {
            rbA.setBackgroundColor(getResources().getColor(R.color.correct_green));
        } else if (correctAnswer.equals("B")) {
            rbB.setBackgroundColor(getResources().getColor(R.color.correct_green));
        } else if (correctAnswer.equals("C")) {
            rbC.setBackgroundColor(getResources().getColor(R.color.correct_green));
        } else {
            rbD.setBackgroundColor(getResources().getColor(R.color.correct_green));
        }

        // nếu chọn đúng thì cộng điểm theo độ khó
        if (selectedId != -1) {
            if (answer.equals(correctAnswer)) {
                score += getScorePerQuestion();
            } else {
                if (answer.equals("A")) {
                    rbA.setBackgroundColor(getResources().getColor(R.color.wrong_red));
                } else if (answer.equals("B")) {
                    rbB.setBackgroundColor(getResources().getColor(R.color.wrong_red));
                } else if (answer.equals("C")) {
                    rbC.setBackgroundColor(getResources().getColor(R.color.wrong_red));
                } else if (answer.equals("D")) {
                    rbD.setBackgroundColor(getResources().getColor(R.color.wrong_red));
                }
            }
        }

        new Handler().postDelayed(() -> {
            current++;

            if (current < list.size()) {
                showQuestion();
            } else {
                if (countDownTimer != null) countDownTimer.cancel();

                String currentDate = new SimpleDateFormat(
                        "dd/MM/yyyy HH:mm",
                        Locale.getDefault()
                ).format(new Date());

                db.insertScore(name, score, getDifficultyText(difficulty), currentDate);

                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("score", score);
                intent.putExtra("total", list.size());
                intent.putExtra("difficulty", getDifficultyText(difficulty));
                intent.putExtra("scorePerQuestion", getScorePerQuestion());
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}