package com.example.final_project;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.final_project.database.DBHandler;

import java.util.Arrays;
import java.util.Random;


public class MainActivity extends AppCompatActivity
{
    Drawable draw;
    Button btnStart;
    EditText username;
    DBHandler dbHandler;
    DisplayMetrics displayMetrics;
    Rect playerImage, trashImage, recyclableImage;
    TextView lblScore, trashName, recyclableName, lblTrivia;
    boolean collisionTrash = false, collisionRecyclable = false;
    ImageView player, trashBin, recyclableBin, lives, trash, imageGarbage;
    int score = 0, livesCount = 3, trashIndex, recyclableIndex, livesNull, livesOne, livesTwo, livesThree, screenWidth;
    float xDown = 0,yDown = 0, movedX, movedY, distanceX, distanceY, yCoordinate = 250.0F;
    String trashOut, playerName, strMilk, strBrownPaper, strCheese, strEggshell, strFishbone, strBanana, strApple, strMask, strBattery, strLightbulb, strNewspaper, strStyrofoam, strCan, strPaper, strPlastic, strMineralbottle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        dbHandler = new DBHandler(this);

        btnStart.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                playerName = username.getText().toString();
                if(!playerName.isEmpty()) {startGame();}
                else {Toast.makeText(MainActivity.this, "INPUT USERNAME FIRST", Toast.LENGTH_SHORT).show();}
            }
        });

        player.setOnTouchListener(new View.OnTouchListener()
        {
            @Override public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getActionMasked())
                {
                    case MotionEvent.ACTION_DOWN: xDown = event.getX(); yDown = event.getY(); break;

                    case MotionEvent.ACTION_MOVE:
                        movedX = event.getX(); movedY = event.getY();
                        distanceX = movedX - xDown; distanceY = movedY - yDown;
                        player.setX(player.getX() + distanceX); player.setY(player.getY() + distanceY);
                        break;

                    case MotionEvent.ACTION_UP:

                        //FOR TRASH
                        checkCollisionTrash();
                        if(collisionTrash)
                        {
                            trashTrivia();
                            if (trashOut == "TRASH")
                            {
                                score++;
                                lblScore.setText("Score: " + score);
                                Toast.makeText(MainActivity.this, "CORRECT", Toast.LENGTH_SHORT).show();
                            }
                            else{wrongAnswer(); Toast.makeText(MainActivity.this, "WRONG", Toast.LENGTH_SHORT).show();}
                            trash.setImageDrawable(null); trashType(); trash.setX(screenWidth); trash.setY(yCoordinate);
                        }

                        //FOR RECYCLABLE
                        checkCollisionRecyclable();
                        if(collisionRecyclable)
                        {
                            recyclableTrivia();
                            if (trashOut == "RECYCLABLE")
                            {
                                score++;
                                lblScore.setText("Score: " + score);
                                Toast.makeText(MainActivity.this, "CORRECT", Toast.LENGTH_SHORT).show();
                            }
                            else{wrongAnswer(); Toast.makeText(MainActivity.this, "WRONG", Toast.LENGTH_SHORT).show();}
                            trash.setImageDrawable(null); trashType(); trash.setX(screenWidth); trash.setY(yCoordinate);
                        }

                        if(!collisionTrash || !collisionRecyclable) {trash.setX(screenWidth); trash.setY(yCoordinate);}
                        break;
                }
                return true;
            }
        });
    }

    public void checkCollisionTrash()
    {
        playerImage = new Rect(); trashImage = new Rect();
        player.getHitRect(playerImage); trashBin.getHitRect(trashImage);
        if(playerImage.intersect(trashImage)) {collisionTrash = true;} else {collisionTrash = false;}
    }

    public void checkCollisionRecyclable()
    {
        playerImage = new Rect(); recyclableImage = new Rect();
        player.getHitRect(playerImage); recyclableBin.getHitRect(recyclableImage);
        if(playerImage.intersect(recyclableImage)) {collisionRecyclable = true;} else {collisionRecyclable = false;}
    }

    public void trashType()
    {
        String[] trashType = {"TRASH", "RECYCLABLE"};
        final int trashValue = new Random().nextInt(trashType.length);
        String output = trashType[trashValue];
        if(output.equals("TRASH")) {trash();}
        if(output.equals("RECYCLABLE")) {recyclable();}
        trashOut = output;
        Log.e("values", "TRASH TYPE: " + trashOut);
    }

    public void trash()
    {
        int cheese, eggshell, fishBone, banana, apple, mask, battery, lightBulb;

        mask = R.drawable.mask;
        apple = R.drawable.apple;
        cheese = R.drawable.cheese;
        banana = R.drawable.banana;
        battery = R.drawable.battery;
        eggshell = R.drawable.eggshell;
        fishBone = R.drawable.fishbone;
        lightBulb = R.drawable.lightbulb;

        trash = findViewById(R.id.trash); int[] arrayTrash = {cheese, eggshell, fishBone, banana, apple, mask, battery, lightBulb};
        int randomize = new Random().nextInt(arrayTrash.length);
        draw = getResources().getDrawable(arrayTrash[randomize]);
        trash.setX(screenWidth); trash.setY(yCoordinate);
        trash.setImageDrawable(draw);

        trashIndex = getArrayIndex(arrayTrash, arrayTrash[randomize]);
        String strArray = Arrays.toString(arrayTrash);
        String strElement = String.valueOf(arrayTrash[randomize]);
        Log.e("values", "TRASH OUTPUT INDEX: " + trashIndex);
        Log.e("values", "TRASH ARRAY TO STRING: " + strArray);
        Log.e("values", "TRASH ELEMENT: " + strElement);
    }

    public void recyclable()
    {
        int brownPaper, milk, newspaper, styrofoam, can, trashPaper, plastic, mineralBottle;

        can = R.drawable.can;
        milk = R.drawable.milk;
        plastic = R.drawable.plastic;
        newspaper = R.drawable.newspaper;
        styrofoam = R.drawable.styrofoam;
        brownPaper = R.drawable.brownpaper;
        trashPaper = R.drawable.trashpaper;
        mineralBottle = R.drawable.mineralbottle;

        trash = findViewById(R.id.trash); int[] arrayRecyclable = {brownPaper, milk, newspaper, styrofoam, can, trashPaper, plastic, mineralBottle};
        int randomize = new Random().nextInt(arrayRecyclable.length);
        draw = getResources().getDrawable(arrayRecyclable[randomize]);
        trash.setX(screenWidth); trash.setY(yCoordinate);
        trash.setImageDrawable(draw);

        recyclableIndex = getArrayIndex(arrayRecyclable, arrayRecyclable[randomize]);
        String strArray = Arrays.toString(arrayRecyclable);
        String strElement = String.valueOf(arrayRecyclable[randomize]);
        Log.e("values", "RECYCLABLE OUTPUT INDEX: " + recyclableIndex);
        Log.e("values", "RECYCLABLE ARRAY TO STRING: " + strArray);
        Log.e("values", "RECYCLABLE ELEMENT: " + strElement);
    }
    public static int getArrayIndex(int[] array, int values)
    {
        if (array == null) {return -1;}
        int len = array.length; int i = 0;
        while (i < len) { if (array[i] == values) {return i;} else {i+=1;} } return -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void wrongAnswer()
    {
        livesCount--;
        if(livesCount == 2) {lives.setImageDrawable(getDrawable(livesTwo));}
        if(livesCount == 1) {lives.setImageDrawable(getDrawable(livesOne));}
        if(livesCount == 0) {noLivesLeft();}
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void loseDialog()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.custom_alert_dialog_lose);
        Log.e("status", "LOSE");

        builder.setPositiveButton("YES", (dialog, which) -> {startGame(); dialog.dismiss();});
        builder.setNegativeButton("NO", (dialog, which) -> {returnHome(); addToDatabase(); dialog.dismiss();});

        AlertDialog dialog = builder.create(); dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE); Button btnNegative = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams layoutParamsPositive = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
        LinearLayout.LayoutParams layoutParamsNegative = (LinearLayout.LayoutParams) btnNegative.getLayoutParams();
        btnPositive.setBackground(getDrawable(R.drawable.alert_dialog_yes)); btnNegative.setBackground(getDrawable(R.drawable.alert_dialog_no));
        btnNegative.setTextColor(Color.parseColor("#000000")); btnPositive.setTextColor(Color.parseColor("#000000"));
        btnPositive.setTextSize(35); btnNegative.setTextSize(35);
        layoutParamsPositive.weight = 10; layoutParamsNegative.weight = 10;
        btnPositive.setLayoutParams(layoutParamsPositive); btnNegative.setLayoutParams(layoutParamsNegative);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startGame()
    {
        trashType();
        score = 0;
        livesCount=3;
        lblScore.setText("Score: " + score);
        lives.setVisibility(View.VISIBLE);
        player.setVisibility(View.VISIBLE);
        trashBin.setVisibility(View.VISIBLE);
        trashName.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        recyclableBin.setVisibility(View.VISIBLE);
        recyclableName.setVisibility(View.VISIBLE);
        username.setVisibility(View.INVISIBLE);
        lblScore.setVisibility(View.VISIBLE);
        lives.setImageDrawable(getDrawable(R.drawable.life_three));
        Log.e("values", "COORDINATES:\nX: " + trash.getX() + "\nY: " + trash.getY());
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setup()
    {
        strBrownPaper = getResources().getString(R.string.brownpaper);
        strLightbulb = getResources().getString(R.string.lightbulb);
        strNewspaper = getResources().getString(R.string.newspaper);
        strStyrofoam = getResources().getString(R.string.styrofoam);
        strCan = getResources().getString(R.string.can);
        strPaper = getResources().getString(R.string.paper);
        strPlastic = getResources().getString(R.string.plastic);
        strMineralbottle = getResources().getString(R.string.mineralbottle);
        strEggshell = getResources().getString(R.string.eggshell);
        strBanana = getResources().getString(R.string.banana);
        strApple = getResources().getString(R.string.apple);
        strMask = getResources().getString(R.string.mask);
        strBattery = getResources().getString(R.string.batteries);
        strFishbone = getResources().getString(R.string.fishbones);
        strCheese = getResources().getString(R.string.cheese);
        strMilk = getResources().getString(R.string.milk);
        lives = findViewById(R.id.lives);
        player = findViewById(R.id.trash);
        username = findViewById(R.id.txtName);
        trashBin = findViewById(R.id.binTrash);
        lblScore = findViewById(R.id.lblScore);
        btnStart = findViewById(R.id.StartGame);
        trashName = findViewById(R.id.trashName);
        recyclableBin = findViewById(R.id.binRecyclable);
        recyclableName = findViewById(R.id.recyclableName);
        livesNull = R.drawable.life_null; livesOne = R.drawable.life_one;
        livesTwo = R.drawable.life_two; livesThree = R.drawable.life_three;
        lblScore.setText("Score: " + score);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = (displayMetrics.widthPixels/2) - 45;
    }

    public void returnHome() {Intent intent = new Intent(MainActivity.this, MainMenu.class); startActivity(intent);}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void noLivesLeft()
    {
        trash.setVisibility(View.INVISIBLE);
        lives.setVisibility(View.INVISIBLE);
        trashBin.setVisibility(View.INVISIBLE);
        trashName.setVisibility(View.INVISIBLE);
        recyclableBin.setVisibility(View.INVISIBLE);
        recyclableName.setVisibility(View.INVISIBLE);
        lives.setImageDrawable(getDrawable(livesNull));
        loseDialog();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void trashTrivia()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.custom_alert_dialog_trivia);

        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create(); dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        lblTrivia = dialog.findViewById(R.id.lbl_trivia);
        imageGarbage = dialog.findViewById(R.id.imageIcon);
        imageGarbage.setImageDrawable(draw);

        if (trashIndex == 0) { lblTrivia.setText(strCheese);}
        if (trashIndex == 1) { lblTrivia.setText(strEggshell); }
        if (trashIndex == 2) { lblTrivia.setText(strFishbone); }
        if (trashIndex == 3) { lblTrivia.setText(strBanana);}
        if (trashIndex == 4) { lblTrivia.setText(strApple); }
        if (trashIndex == 5) { lblTrivia.setText(strMask); }
        if (trashIndex == 6) { lblTrivia.setText(strBattery); }
        if (trashIndex == 7) { lblTrivia.setText(strLightbulb); }

        Button btnPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        LinearLayout.LayoutParams layoutParamsPositive = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
        btnPositive.setBackground(getDrawable(R.drawable.alert_dialog_ok));
        btnPositive.setTextSize(35);
        layoutParamsPositive.weight = 10;
        btnPositive.setTextColor(Color.parseColor("#000000"));
        btnPositive.setLayoutParams(layoutParamsPositive);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void recyclableTrivia()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.custom_alert_dialog_trivia);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override public void onClick(DialogInterface dialog, int which) {dialog.dismiss();}
        });

        AlertDialog dialog = builder.create(); dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        lblTrivia = dialog.findViewById(R.id.lbl_trivia);
        imageGarbage = dialog.findViewById(R.id.imageIcon);
        imageGarbage.setImageDrawable(draw);

        if (recyclableIndex == 0) { lblTrivia.setText(strBrownPaper); }
        if (recyclableIndex == 1) { lblTrivia.setText(strMilk); }
        if (recyclableIndex == 2) { lblTrivia.setText(strNewspaper); }
        if (recyclableIndex == 3) { lblTrivia.setText(strStyrofoam); }
        if (recyclableIndex == 4) { lblTrivia.setText(strCan); }
        if (recyclableIndex == 5) { lblTrivia.setText(strPaper); }
        if (recyclableIndex == 6) { lblTrivia.setText(strPlastic); }
        if (recyclableIndex == 7) { lblTrivia.setText(strMineralbottle);}

        Button btnPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        LinearLayout.LayoutParams layoutParamsPositive = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
        btnPositive.setBackground(getDrawable(R.drawable.alert_dialog_ok));
        btnPositive.setTextColor(Color.parseColor("#000000"));
        btnPositive.setTextSize(35);
        layoutParamsPositive.weight = 10;
        btnPositive.setLayoutParams(layoutParamsPositive);
    }

    public void addToDatabase()
    {
        try
        {
            int dbScore = Integer.parseInt(String.valueOf(score));
            boolean status = dbHandler.addUserData(playerName, dbScore);
            if(status) {Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show();}
        }
        catch (Exception e) {Toast.makeText(this, "ADD TO DATABASE FAILED", Toast.LENGTH_SHORT).show();}
    }
}