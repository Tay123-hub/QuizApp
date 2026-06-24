package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText edtName;
    MaterialButton btnStart;
    RadioGroup rgDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        btnStart = findViewById(R.id.btnStart);
        rgDifficulty = findViewById(R.id.rgDifficulty);

        rgDifficulty.check(R.id.rbEasy);

        btnStart.setOnClickListener(v -> {
            String name = "";

            if (edtName.getText() != null) {
                name = edtName.getText().toString().trim();
            }

            if (TextUtils.isEmpty(name)) {
                edtName.setError("Vui lòng nhập tên người chơi");
                return;
            }

            String difficulty = "easy";
            int checkedId = rgDifficulty.getCheckedRadioButtonId();

            if (checkedId == R.id.rbEasy) {
                difficulty = "easy";
            } else if (checkedId == R.id.rbMedium) {
                difficulty = "medium";
            } else if (checkedId == R.id.rbHard) {
                difficulty = "hard";
            }

            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("difficulty", difficulty);
            startActivity(intent);
        });
    }
}