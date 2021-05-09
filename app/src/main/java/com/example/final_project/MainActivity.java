package com.example.final_project;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.icu.util.Output;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Arrays;
import java.util.Random;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity
{
    Button btnStart;
    EditText username;
    Rect playerImage, trashImage, recyclableImage;
    TextView lblScore, trashName, recyclableName;
    ImageView player, trashBin, recyclableBin, lives, trash;
    boolean collisionTrash = false, collisionRecyclable = false;
    int score = 0, livesCount = 3, trashBounds = 7, trashIndex, recyclableIndex, livesNull, livesOne, livesTwo, livesThree;
    float xDown = 0,yDown = 0, movedX, movedY, distanceX, distanceY, xCoordinate = 460.0F, yCoordinate = 250.0F;
    String trashOut, playerName;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();

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
                            if (trashOut == "TRASH")
                            {score++; lblScore.setText("Score: " + score); Toast.makeText(MainActivity.this, "CORRECT", Toast.LENGTH_SHORT).show();}
                            else{wrongAnswer(); Toast.makeText(MainActivity.this, "WRONG", Toast.LENGTH_SHORT).show();}
                        }
                        //FOR RECYCLABLE
                        checkCollisionRecyclable();
                        if(collisionRecyclable)
                        {
                            if (trashOut == "RECYCLABLE")
                            {score++; lblScore.setText("Score: " + score); Toast.makeText(MainActivity.this, "CORRECT", Toast.LENGTH_SHORT).show();}
                            else{wrongAnswer(); Toast.makeText(MainActivity.this, "WRONG", Toast.LENGTH_SHORT).show();}
                        }
                        trash.setImageDrawable(null); trashType();
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
        Drawable draw = getResources().getDrawable(arrayTrash[randomize]); this.trash.setImageDrawable(draw);
        this.trash.setX(xCoordinate); this.trash.setY(yCoordinate);

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
        Drawable draw = getResources().getDrawable(arrayRecyclable[randomize]); this.trash.setImageDrawable(draw);
        this.trash.setX(xCoordinate); this.trash.setY(yCoordinate);

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

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
        {@Override public void onClick(DialogInterface dialog, int which) {startGame(); dialog.dismiss();}});
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
        {@Override public void onClick(DialogInterface dialog, int which) {returnHome(); dialog.dismiss();}});

        AlertDialog dialog = builder.create(); dialog.show();
        Button btnPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE); Button btnNegative = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams layoutParamsPositive = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
        LinearLayout.LayoutParams layoutParamsNegative = (LinearLayout.LayoutParams) btnNegative.getLayoutParams();
        btnPositive.setBackground(getDrawable(R.drawable.alert_dialog_yes)); btnNegative.setBackground(getDrawable(R.drawable.alert_dialog_no));
        btnNegative.setTextColor(Color.parseColor("#000000")); btnPositive.setTextColor(Color.parseColor("#000000"));
        btnPositive.setTextSize(35); btnNegative.setTextSize(35);
        layoutParamsPositive.weight = 10; layoutParamsNegative.weight = 10;
        btnPositive.setLayoutParams(layoutParamsPositive); btnNegative.setLayoutParams(layoutParamsNegative);
    }

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
        lives.setImageDrawable(getDrawable(R.drawable.life_three));
        Log.e("values", "COORDINATES:\nX: " + trash.getX() + "\nY: " + trash.getY());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setup()
    {
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

    public void addToDatabase()
    {

    }
}