package com.example.final_project;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    ImageButton ibtnBattery, ibtnBulbs, ibtnEWaste, ibtnGlass, ibtnMetal, ibtnOrganic, ibtnPaper, ibtnPlastic;
    ImageView player;
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
                        xDown=movedX;
                        yDown=movedY;

                        break;
                }

                return true;
            }
        });
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

        player = findViewById(R.id.player);
    }
}