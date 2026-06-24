package com.example.quizapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.model.Score;

import java.util.ArrayList;

public class ScoreAdapter extends ArrayAdapter<Score> {

    Activity context;
    ArrayList<Score> list;

    public ScoreAdapter(Activity context, ArrayList<Score> list) {
        super(context, R.layout.item_score, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View row = inflater.inflate(R.layout.item_score, null, true);

        TextView tvRank = row.findViewById(R.id.tvRank);
        TextView tvPlayerName = row.findViewById(R.id.tvPlayerName);
        TextView tvScore = row.findViewById(R.id.tvScore);
        TextView tvDate = row.findViewById(R.id.tvDate);
        TextView tvDifficulty = row.findViewById(R.id.tvDifficulty);

        Score s = list.get(position);

        if (position == 0) {
            tvRank.setText("🥇");
        } else if (position == 1) {
            tvRank.setText("🥈");
        } else if (position == 2) {
            tvRank.setText("🥉");
        } else {
            tvRank.setText(String.valueOf(position + 1));
        }

        tvPlayerName.setText(s.getName());
        tvScore.setText("Điểm: " + s.getScore());
        tvDifficulty.setText("Độ khó: " + s.getDifficulty());
        tvDate.setText("Ngày: " + s.getDate());

        return row;
    }
}