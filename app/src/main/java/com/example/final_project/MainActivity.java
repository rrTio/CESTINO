package com.example.final_project;

import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    ImageButton ibtnBattery, ibtnBulbs, ibtnEWaste, ibtnGlass, ibtnMetal, ibtnOrganic, ibtnPaper, ibtnPlastic;
    ImageView player;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
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