package com.example.final_project;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    Button btnStart;
    Rect playerImage, trashImage;
    TextView lblScore, trashName;
    ImageView player, trash, lives;
    Drawable battery, bulbs, eWaste, glass, metal, organic, paper, plastic;
    ImageButton ibtnBattery, ibtnBulbs, ibtnEWaste, ibtnGlass, ibtnMetal, ibtnOrganic, ibtnPaper, ibtnPlastic;

    boolean collision = false;
    int score = 0, livesCount = 3, random, randomType, livesNull, livesOne, livesTwo, livesThree;
    int  boundsValue = 4, trashBounds = 4;
    float xDown = 0,yDown = 0, movedX, movedY, distanceX, distanceY;
    String strbatteries, strbulbs, strEWaste, strGlass, strMetals, strOrganic, strPapers, strPlastics;
    String bio, nonBio, recycle, trashOut;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup(); //SETTING UP OF COMPONENTS

        btnStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                trashOut = trashType();
            }
        });

        //PLAYER'S MOTION
        trash.setOnTouchListener(new View.OnTouchListener()
        {
            @Override public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getActionMasked())
                {
                    //USER'S TOUCH
                    case MotionEvent.ACTION_DOWN: xDown = event.getX(); yDown = event.getY(); break;

                    //USER'S MOTION
                    case MotionEvent.ACTION_MOVE:
                        movedX = event.getX(); movedY = event.getY();
                        distanceX = movedX - xDown; distanceY = movedY - yDown;
                        player.setX(player.getX() + distanceX); player.setY(player.getY() + distanceY);

                        break;

                    //USER'S TOUCH RELEASED
                    case MotionEvent.ACTION_UP:
                        checkCollision();
                        if(collision == true)
                        {
                            /*if(trashOut == trashBin)
                            {
                                score++; lblScore.setText("Score: " + String.valueOf(score));
                                Toast.makeText(MainActivity.this, "CORRECT", Toast.LENGTH_SHORT).show();
                            }
                            else {wrongAnswer();}*/
                        }

                        //NOT FINAL FOR WRONG EVENTS
                        if(collision == false)
                        {

                        }
                        break;
                }
                return true;
            }
        });

        //CHANGE PLAYER'S IMAGE & SET THE BIN'S NAME
        ibtnBattery.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {player.setImageDrawable(battery); trashName.setText(strbatteries);}});
        ibtnBulbs.setOnClickListener(new View.OnClickListener()   {@Override public void onClick(View v) {player.setImageDrawable(bulbs);   trashName.setText(strbulbs);}});
        ibtnEWaste.setOnClickListener(new View.OnClickListener()  {@Override public void onClick(View v) {player.setImageDrawable(eWaste);  trashName.setText(strEWaste);}});
        ibtnGlass.setOnClickListener(new View.OnClickListener()   {@Override public void onClick(View v) {player.setImageDrawable(glass);   trashName.setText(strGlass);}});
        ibtnMetal.setOnClickListener(new View.OnClickListener()   {@Override public void onClick(View v) {player.setImageDrawable(metal);   trashName.setText(strMetals);}});
        ibtnOrganic.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {player.setImageDrawable(organic); trashName.setText(strOrganic);}});
        ibtnPaper.setOnClickListener(new View.OnClickListener()   {@Override public void onClick(View v) {player.setImageDrawable(paper);   trashName.setText(strPapers);}});
        ibtnPlastic.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {player.setImageDrawable(plastic); trashName.setText(strPlastics);}});
    }

    public void checkCollision()
    {
        //SETUP RECT FOR IMAGEVIEWS
        playerImage = new Rect(); trashImage = new Rect();
        player.getHitRect(playerImage); trash.getHitRect(trashImage);

        //COLLISION CHECKER FOR IMAGEVIEWS
        if(playerImage.intersect(trashImage)) {collision = true;}
        else {collision = false;}
    }

    //RANDOMIZER FOR TRASH AND VALUES
    public int random() {final int randomValue = new Random().nextInt(boundsValue); return randomValue;}

    //ARRAYS FOR IMAGES AND VALUES
    public static String trashType()
    {
        String[] trashType = {"BIODEGRADABLE", "NON-BIODEGRADABLE", "RECYCLABLE"};
        final int trashValue = new Random().nextInt(2);

        return trashType[trashValue];
    }

    //ARRAYS FOR RANDOM IMAGES
    public void bioDegradable()
    {
        trash = findViewById(R.id.trash);
        int[] bioDegradable = {};
        Drawable draw = getResources().getDrawable(bioDegradable[random]); trash.setImageDrawable(draw);
    }

    public void nonBioDegradable()
    {
        trash = findViewById(R.id.trash);
        int[] nonBioDegradable = {};
        Drawable draw = getResources().getDrawable(nonBioDegradable[random]); trash.setImageDrawable(draw);
    }

    public void recyclable()
    {
        trash = findViewById(R.id.trash);
        int[] recyclable = {};
        Drawable draw = getResources().getDrawable(recyclable[random]); trash.setImageDrawable(draw);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void wrongAnswer()
    {
        livesCount--;
        if(livesCount == 2) {lives.setImageDrawable(getDrawable(livesTwo));}
        if(livesCount == 1) {lives.setImageDrawable(getDrawable(livesOne));}
        if(livesCount == 0) {lives.setImageDrawable(getDrawable(livesNull));}
    }

    //SETUP COMPONENTS
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setup()
    {
        //INSTANTIATION
        btnStart = findViewById(R.id.StartGame);

        ibtnBulbs = findViewById(R.id.ibtn_bulbs);
        ibtnGlass = findViewById(R.id.ibtn_glass);
        ibtnMetal = findViewById(R.id.ibtn_metal);
        ibtnPaper = findViewById(R.id.ibtn_paper);
        ibtnEWaste = findViewById(R.id.ibtn_ewaste);
        ibtnBattery = findViewById(R.id.ibtn_battery);
        ibtnOrganic = findViewById(R.id.ibtn_organic);
        ibtnPlastic = findViewById(R.id.ibtn_plastic);

        bulbs = ibtnBulbs.getDrawable();
        glass = ibtnGlass.getDrawable();
        metal = ibtnMetal.getDrawable();
        paper = ibtnPaper.getDrawable();
        eWaste = ibtnEWaste.getDrawable();
        battery = ibtnBattery.getDrawable();
        organic = ibtnOrganic.getDrawable();
        plastic = ibtnPlastic.getDrawable();

        lives = findViewById(R.id.lives);
        trash = findViewById(R.id.trash);
        player = findViewById(R.id.player);

        livesNull = R.drawable.life_null;
        livesOne = R.drawable.life_one;
        livesTwo = R.drawable.life_two;
        livesThree = R.drawable.life_three;

        trashName = findViewById(R.id.trashName);
        lblScore = findViewById(R.id.lblScore);
        lblScore.setText("Score: " + score);

        strbulbs = "BULBS";
        strPapers = "PAPERS";
        strGlass = "GLASSES";
        strMetals = "METALS";
        strEWaste = "E-WASTES";
        strOrganic = "ORGANICS";
        strPlastics = "PLASTICS";
        strbatteries = "BATTERIES";
    }
}