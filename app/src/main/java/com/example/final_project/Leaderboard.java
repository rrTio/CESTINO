package com.example.final_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.final_project.database.DBHandler;
import com.example.final_project.database.DBOutput;

import java.nio.Buffer;

public class Leaderboard extends AppCompatActivity
{
    ListView listView;
    Button btnReturn;
    ArrayAdapter arrayAdapter;
    DBHandler dbHandler;
    MediaPlayer click;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        dbHandler = new DBHandler(this);
        click = MediaPlayer.create(this, R.raw.click);

        btnReturn = findViewById(R.id.btnReturnToMenu);
        btnReturn.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View v) { startActivity(new Intent(Leaderboard.this, MainMenu.class));}});

        listView = findViewById(R.id.listScores);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                click.start();
                try
                {
                    DBOutput dbOutput = (DBOutput) parent.getItemAtPosition(position);
                    dbHandler.deleteUserData(dbOutput);
                    updateUserData(dbHandler);
                    Toast.makeText(Leaderboard.this, "DELETED\n" + dbOutput.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {Toast.makeText(Leaderboard.this, "DELETION FAILED", Toast.LENGTH_SHORT).show();}
            }
        }); updateUserData(dbHandler);
    }

    private void updateUserData(DBHandler dbHandler1)
    {
        arrayAdapter = new ArrayAdapter<DBOutput>(Leaderboard.this, R.layout.custom_listview, dbHandler.getUserData());
        listView.setAdapter(arrayAdapter);
    }
}