package com.example.final_project;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Intro extends AppCompatActivity
{

    private static final int INTRO_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        navigationBar();

        Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(100);
        fadeOut.setDuration(2000);

        ImageView image = findViewById(R.id.Logo); image.setAnimation(fadeOut);
        TextView title = findViewById(R.id.Title); title.setAnimation(fadeOut);
        TextView credits = findViewById(R.id.Credits); credits.setAnimation(fadeOut);

        new Handler().postDelayed(new Runnable()
        {@Override public void run() { Intent intent = new Intent(Intro.this, MainMenu.class); startActivity(intent); }}, INTRO_TIMEOUT);
    }

    public void navigationBar() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}