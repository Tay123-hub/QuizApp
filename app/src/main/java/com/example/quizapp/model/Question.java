package com.example.quizapp.model;

public class Question {

    private int id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private String image;
    private String difficulty; // easy / medium / hard

    public Question() {
    }

    public Question(int id,
                    String question,
                    String optionA,
                    String optionB,
                    String optionC,
                    String optionD,
                    String answer,
                    String image,
                    String difficulty) {
        this.id = id;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
        this.image = image;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public String getImage() {
        return image;
    }

    public String getDifficulty() {
        return difficulty;
    }
}