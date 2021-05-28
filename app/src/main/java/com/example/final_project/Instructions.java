package com.example.final_project;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Instructions extends AppCompatActivity
{
    String strInstructions;
    TextView lblInstructions;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        strInstructions = getResources().getString(R.string.instructions);
        lblInstructions = findViewById(R.id.lblInstructions);
        lblInstructions.setText(strInstructions);
    }
}