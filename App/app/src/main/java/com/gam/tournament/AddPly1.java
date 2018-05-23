package com.gam.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gam.tournament.DataBase.AppDatabase;
import com.gam.tournament.DataBase.Team;

public class AddPly1 extends AppCompatActivity {

    // Declaramos componentes
    EditText etNamePlayer1, etTeam1ply1, etTeam2ply1, etTeam3ply1;
    Button btPly1;
    Team team1ply1, team2ply1, team3ply1;

    AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ply1);

        //Referenciamos componentes
        etNamePlayer1 = (EditText)findViewById(R.id.player1name);
        etTeam1ply1 = (EditText)findViewById(R.id.team1player1);
        etTeam2ply1 = (EditText)findViewById(R.id.team2player1);
        etTeam3ply1 = (EditText)findViewById(R.id.team3player1);
        btPly1 = (Button)findViewById(R.id.btPlayer1);

        //Cuando se pulsa el boton:
        btPly1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos los objetos Team
                team1ply1 = new Team(1, etTeam1ply1.getText().toString(), etNamePlayer1.getText().toString(),2);
                team2ply1 = new Team(3, etTeam2ply1.getText().toString(), etNamePlayer1.getText().toString(),4);
                team3ply1 = new Team(5, etTeam3ply1.getText().toString(), etNamePlayer1.getText().toString(),6);

                mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

                mDb.teamDAO().insertTeam(team1ply1);
                mDb.teamDAO().insertTeam(team2ply1);
                mDb.teamDAO().insertTeam(team3ply1);

                Intent intent = new Intent(getApplicationContext(), AddPly2.class);
                startActivity(intent);
            }
        });
    }
}
