package com.example.final_project;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.final_project.database.DBHandler;
import com.example.final_project.database.DBOutput;

public class Leaderboards extends AppCompatActivity
{
    ListView listView;
    Button btnReturn;
    ArrayAdapter arrayAdapter;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        btnReturn = findViewById(R.id.btnReturnToMenu);
        btnReturn.setOnClickListener(new View.OnClickListener()
        {@Override  public void onClick(View v) {startActivity(new Intent(Leaderboards.this, MainMenu.class));}});

        listView = findViewById(R.id.listScores);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try
                {
                    DBOutput dbOutput = (DBOutput) parent.getItemAtPosition(position);
                    dbHandler.deleteUserData(dbOutput);
                    updateUserData(dbHandler);
                    Toast.makeText(Leaderboards.this, "DELETED\n" + dbOutput.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {Toast.makeText(Leaderboards.this, "DELETION FAILED", Toast.LENGTH_SHORT).show();}
            }
        }); updateUserData(dbHandler);
    }

    private void updateUserData(DBHandler dbHandler1)
    {
        arrayAdapter = new ArrayAdapter<DBOutput>(Leaderboards.this, R.layout.custom_listview, dbHandler.getUserData());
        listView.setAdapter(arrayAdapter);
    }
}