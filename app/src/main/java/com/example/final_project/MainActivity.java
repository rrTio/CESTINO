package com.example.final_project;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
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
    Rect playerImage, trashImage, recyclableImage;
    TextView lblScore, trashName, recyclableName;
    ImageView player, trashBin, recyclableBin, lives, trash;

    boolean collisionTrash = false, collisionRecyclable = false;
    int score = 0, livesCount = 3, livesNull, livesOne, livesTwo, livesThree;
    int trashBounds = 7;
    float xDown = 0,yDown = 0, movedX, movedY, distanceX, distanceY;
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
                trashBin.setVisibility(View.VISIBLE);
                recyclableBin.setVisibility(View.VISIBLE);
                player.setVisibility(View.VISIBLE);
                trashName.setVisibility(View.VISIBLE);
                recyclableName.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.INVISIBLE);
                Log.e("values", "X: " + trash.getX() + "Y: " + trash.getY());

            }
        });

        //PLAYER'S MOTION
        player.setOnTouchListener(new View.OnTouchListener()
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
                        checkCollisionTrash();
                        if(collisionTrash == true)
                        {
                            if (trashOut == "TRASH")
                            {
                                score++; lblScore.setText("Score: " + score);
                                Toast.makeText(MainActivity.this, "CORRECT", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                wrongAnswer();
                                Toast.makeText(MainActivity.this, "CORRECT", Toast.LENGTH_SHORT).show();
                            }
                        }

                        checkCollisionRecyclable();
                        //NOT FINAL FOR WRONG EVENTS
                        if(collisionRecyclable == true)
                        {
                            if (trashOut == "RECYCLABLE")
                            {
                                score++; lblScore.setText("Score: " + score);
                                Toast.makeText(MainActivity.this, "CORRECT", Toast.LENGTH_SHORT).show();
                                trash.setImageDrawable(null);
                                trashType();
                            }
                            else
                            {
                                wrongAnswer();
                                Toast.makeText(MainActivity.this, "WRONG", Toast.LENGTH_SHORT).show();
                                trash.setImageDrawable(null);
                                trashType();
                            }
                        }
                        break;
                }
                return true;
            }
        });
        }

    public void checkCollisionTrash()
    {
        //SETUP RECT FOR IMAGEVIEWS
        playerImage = new Rect(); trashImage = new Rect();
        player.getHitRect(playerImage); trashBin.getHitRect(trashImage);

        //COLLISION CHECKER FOR IMAGEVIEWS
        if(playerImage.intersect(trashImage)) { collisionTrash = true;}
        else { collisionTrash = false;}
    }

    public void checkCollisionRecyclable()
    {
        //SETUP RECT FOR IMAGEVIEWS
        playerImage = new Rect(); recyclableImage = new Rect();
        player.getHitRect(playerImage); recyclableBin.getHitRect(recyclableImage);

        //COLLISION CHECKER FOR IMAGEVIEWS
        if(playerImage.intersect(recyclableImage)) {collisionRecyclable = true;}
        else {collisionRecyclable = false;}
    }

    //ARRAYS FOR IMAGES AND VALUES
    public String trashType()
    {
        final int trashValue = new Random().nextInt(1);
        int randomize = new Random().nextInt(trashBounds);
        String[] trashType = {"TRASH", "RECYCLABLE"};
        String output = trashType[trashValue];

        if(output == "TRASH") {trash(randomize);}
        if(output == "RECYCLABLE") {recyclable(randomize);}

        return output;
    }

    //ARRAYS FOR RANDOM IMAGES
    public void trash(int randomize)
    {
        int cheese, eggshell, fishBone, banana, apple, mask, battery, lightBulb; //DECLARE VARIABLES



        cheese = R.drawable.cheese; //INSTANTIATE IMAGE TO VARIABLE
        eggshell = R.drawable.eggshell;
        fishBone = R.drawable.fishbone;
        banana = R.drawable.banana;
        apple = R.drawable.apple;
        mask = R.drawable.mask;
        battery = R.drawable.battery;
        lightBulb = R.drawable.lightbulb;


        trash = findViewById(R.id.trash); int[] bioDegradable = {cheese, eggshell, fishBone, banana, apple, mask, battery, lightBulb}; //ADD TO ARRAY
        Drawable draw = getResources().getDrawable(bioDegradable[randomize]); trash.setImageDrawable(draw); trash.setX(315.0F); trash.setY(0.0F);
    }

    public void recyclable(int randomize)
    {
        int brownPaper, milk, newspaper, styrofoam, can, trashPaper, plastic, mineralBottle; //DECLARE VARIABLES
        brownPaper = R.drawable.brownpaper; //INSTANTIATE IMAGE TO VARIABLE
        milk = R.drawable.milk;
        newspaper = R.drawable.newspaper;
        can = R.drawable.can;
        styrofoam = R.drawable.styrofoam;
        trashPaper = R.drawable.trashpaper;
        plastic = R.drawable.plastic;
        mineralBottle = R.drawable.mineralbottle;

        trash = findViewById(R.id.trash); int[] recyclable = {brownPaper, milk, newspaper, styrofoam, can, trashPaper, plastic, mineralBottle}; //ADD TO ARRAY
        Drawable draw = getResources().getDrawable(recyclable[randomize]); trash.setImageDrawable(draw);
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

        lives = findViewById(R.id.lives);
        trashBin = findViewById(R.id.binTrash);
        recyclableBin = findViewById(R.id.binRecyclable);
        player = findViewById(R.id.trash);

        livesNull = R.drawable.life_null;
        livesOne = R.drawable.life_one;
        livesTwo = R.drawable.life_two;
        livesThree = R.drawable.life_three;

        trashName = findViewById(R.id.trashName);
        recyclableName = findViewById(R.id.recyclableName);
        lblScore = findViewById(R.id.lblScore);
        lblScore.setText("Score: " + score);
    }
}