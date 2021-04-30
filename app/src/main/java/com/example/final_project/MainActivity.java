package com.example.final_project;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    ImageButton ibtnBattery, ibtnBulbs, ibtnEWaste, ibtnGlass, ibtnMetal, ibtnOrganic, ibtnPaper, ibtnPlastic;
    ImageView player;
    TextView lblScore;
    int score = 0;
    Drawable battery, bulbs, eWaste, glass, metal, organic, paper, plastic;
    float xDown = 0,yDown = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();

        player.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getActionMasked())
                {
                    //USER'S TOUCH
                    case MotionEvent.ACTION_DOWN:

                        xDown = event.getX();
                        yDown = event.getY();

                        break;

                    //USER'S MOTION
                    case MotionEvent.ACTION_MOVE:
                        float movedX, movedY, distanceX, distanceY;

                        movedX = event.getX();
                        movedY = event.getY();

                        distanceX = movedX - xDown;
                        distanceY = movedY - yDown;

                        player.setX(player.getX() + distanceX);
                        player.setY(player.getY() + distanceY);

                        break;
                }

                return true;
            }
        });

        ibtnBattery.setOnClickListener(new View.OnClickListener()   {@Override public void onClick(View v) {player.setImageDrawable(battery);}});
        ibtnBulbs.setOnClickListener(new View.OnClickListener()     {@Override public void onClick(View v) {player.setImageDrawable(bulbs);}});
        ibtnEWaste.setOnClickListener(new View.OnClickListener()    {@Override public void onClick(View v) {player.setImageDrawable(eWaste);}});
        ibtnGlass.setOnClickListener(new View.OnClickListener()     {@Override public void onClick(View v) {player.setImageDrawable(glass);}});
        ibtnMetal.setOnClickListener(new View.OnClickListener()     {@Override public void onClick(View v) {player.setImageDrawable(metal);}});
        ibtnOrganic.setOnClickListener(new View.OnClickListener()   {@Override public void onClick(View v) {player.setImageDrawable(organic);}});
        ibtnPaper.setOnClickListener(new View.OnClickListener()     {@Override public void onClick(View v) {player.setImageDrawable(paper);}});
        ibtnPlastic.setOnClickListener(new View.OnClickListener()   {@Override public void onClick(View v) {player.setImageDrawable(plastic);}});

    }

    public void setup()
    {
        ibtnBattery = findViewById(R.id.ibtn_battery);
        ibtnBulbs = findViewById(R.id.ibtn_bulbs);
        ibtnEWaste = findViewById(R.id.ibtn_ewaste);
        ibtnGlass = findViewById(R.id.ibtn_glass);
        ibtnMetal = findViewById(R.id.ibtn_metal);
        ibtnOrganic = findViewById(R.id.ibtn_organic);
        ibtnPaper = findViewById(R.id.ibtn_paper);
        ibtnPlastic = findViewById(R.id.ibtn_plastic);

        battery = ibtnBattery.getDrawable();
        bulbs = ibtnBulbs.getDrawable();
        eWaste = ibtnEWaste.getDrawable();
        glass = ibtnGlass.getDrawable();
        metal = ibtnMetal.getDrawable();
        organic = ibtnOrganic.getDrawable();
        paper = ibtnPaper.getDrawable();
        plastic = ibtnPlastic.getDrawable();

        player = findViewById(R.id.player);

        lblScore = findViewById(R.id.lblScore);
        lblScore.setText("Score: " + score);
    }
}