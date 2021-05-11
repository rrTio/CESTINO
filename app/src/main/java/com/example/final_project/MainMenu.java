package com.example.final_project;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainMenu extends AppCompatActivity
{

    Button btnPlay, btnInstructions, btnLeaderboards, btnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setupButtons();

        btnPlay.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {playGame();}});
        btnInstructions.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {openInstructions();}});
        btnLeaderboards.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {openLeaderboards();}});
        btnQuit.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {quitGame();}});
    }

    public void setupButtons()
    {
        btnPlay = findViewById(R.id.btnPlay);
        btnInstructions = findViewById(R.id.btnInstructions);
        btnLeaderboards = findViewById(R.id.btnLeaderboards);
        btnQuit = findViewById(R.id.btnQuit);
    }

    public void playGame() {Intent intent = new Intent(this, MainActivity.class); startActivity(intent);}
    public void openInstructions() {Intent intent = new Intent(this, Instructions.class); startActivity(intent);}
    public void openLeaderboards() {Intent intent = new Intent(this, Leaderboard.class); startActivity(intent);}
    public void quitGame()
    {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory( Intent.CATEGORY_HOME ); intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}