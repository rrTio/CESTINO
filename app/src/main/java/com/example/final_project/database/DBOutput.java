package com.example.final_project.database;

public class DBOutput
{
    private String username;
    private int score;

    public DBOutput(String username, int score) {this.username = username; this.score = score;}

    @Override
    public String toString() {return "PLAYER NAME: '" + username + '\n' + "SCORE: " + score;}

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }
}
