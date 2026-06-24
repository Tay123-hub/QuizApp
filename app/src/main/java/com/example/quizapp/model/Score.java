package com.example.quizapp.model;

public class Score {
    private int id;
    private String name;
    private int score;
    private String difficulty;
    private String date;

    public Score(int id, String name, int score, String difficulty, String date) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.difficulty = difficulty;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getDate() {
        return date;
    }
}