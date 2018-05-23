package com.gam.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gam.tournament.DataBase.AppDatabase;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void play(View view){
        AppDatabase mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        int dbSize = mDb.teamDAO().getAllTeams().size();
        if (dbSize < 3){
            Intent intent = new Intent(this, AddPly1.class);
            startActivity(intent);
        }
        else if(dbSize < 6){
            Intent intent =new Intent(this, AddPly2.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, Play.class);
            startActivity(intent);
        }
    }

    public void showTable(View view){
        Intent intent = new Intent(this, Table.class);
        startActivity(intent);
    }

    public void finish(View view){

        AppDatabase mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        mDb.teamDAO().deleteAll();
    }

    public void goOut(View view) {
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
