package com.example.quizapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.adapter.ScoreAdapter;
import com.example.quizapp.database.DBHelper;
import com.example.quizapp.model.Score;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    private ListView listViewScore;
    private MaterialButton btnDelete, btnBack;

    private ArrayList<Score> list;
    private DBHelper db;
    private ScoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        listViewScore = findViewById(R.id.listViewScore);
        btnDelete = findViewById(R.id.btnDelete);
        btnBack = findViewById(R.id.btnBack);

        db = new DBHelper(this);
        loadScores();

        btnBack.setOnClickListener(v -> finish());

        btnDelete.setOnClickListener(v -> {
            db.deleteAllScores();
            Toast.makeText(ScoreActivity.this, "Đã xóa bảng xếp hạng!", Toast.LENGTH_SHORT).show();
            loadScores();
        });
    }

    private void loadScores() {
        list = db.getAllScores();
        adapter = new ScoreAdapter(this, list);
        listViewScore.setAdapter(adapter);
    }
}