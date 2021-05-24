package com.example.final_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainMenu extends AppCompatActivity
{
    Button btnPlay, btnInstructions, btnLeaderboards, btnQuit;
    MediaPlayer bgm, bgm1, click;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setupButtons();
        click = MediaPlayer.create(this, R.raw.click);
        bgm = MediaPlayer.create(this, R.raw.bgm);
        bgm1 = MediaPlayer.create(this, R.raw.bgm1);
        bgm.start();
        btnPlay.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {playGame();click.start();bgm.pause(); bgm1.start();}});
        btnInstructions.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {openInstructions();click.start();}});
        btnLeaderboards.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {openLeaderboards();click.start();}});
        btnQuit.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {quitGame();click.start();}});
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

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        quitGame();
    }

}