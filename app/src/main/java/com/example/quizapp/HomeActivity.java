package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class HomeActivity extends AppCompatActivity {

    MaterialButton btnPlay, btnScore, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnPlay = findViewById(R.id.btnPlay);
        btnScore = findViewById(R.id.btnScore);
        btnExit = findViewById(R.id.btnExit);

        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        btnScore.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ScoreActivity.class);
            startActivity(intent);
        });

        btnExit.setOnClickListener(v -> finishAffinity());
    }
}