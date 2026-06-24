package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ResultActivity extends AppCompatActivity {

    TextView tvResult, tvPlayer, tvDifficulty;
    MaterialButton btnPlayAgain, btnScoreBoard, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResult = findViewById(R.id.tvResult);
        tvPlayer = findViewById(R.id.tvPlayer);
        tvDifficulty = findViewById(R.id.tvDifficulty);

        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnScoreBoard = findViewById(R.id.btnScoreBoard);
        btnHome = findViewById(R.id.btnHome);

        String name = getIntent().getStringExtra("name");
        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 0);
        String difficulty = getIntent().getStringExtra("difficulty");
        int scorePerQuestion = getIntent().getIntExtra("scorePerQuestion", 1);

        int maxScore = total * scorePerQuestion;

        tvPlayer.setText("Người chơi: " + name);
        tvDifficulty.setText("Độ khó: " + difficulty);
        tvResult.setText("Bạn đạt " + score + "/" + maxScore + " điểm");

        btnPlayAgain.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btnScoreBoard.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, ScoreActivity.class);
            startActivity(intent);
        });

        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}