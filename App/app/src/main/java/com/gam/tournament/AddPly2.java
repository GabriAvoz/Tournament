package com.gam.tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gam.tournament.DataBase.AppDatabase;
import com.gam.tournament.DataBase.Team;

public class AddPly2 extends AppCompatActivity {

    // Declaramos componentes
    EditText etNamePlayer2, etTeam1ply2, etTeam2ply2, etTeam3ply2;
    Button btPly2;
    Team team1ply2, team2ply2, team3ply2;

    AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ply2);

        //Referenciamos componentes
        etNamePlayer2 = (EditText)findViewById(R.id.player2name);
        etTeam1ply2 = (EditText)findViewById(R.id.team1player2);
        etTeam2ply2 = (EditText)findViewById(R.id.team2player2);
        etTeam3ply2 = (EditText)findViewById(R.id.team3player2);
        btPly2 = (Button)findViewById(R.id.btPlayer2);

        //Cuando se pulsa el boton:
        btPly2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos los objetos Team
                team1ply2 = new Team(2, etTeam1ply2.getText().toString(), etNamePlayer2.getText().toString(),1);
                team2ply2 = new Team(4, etTeam2ply2.getText().toString(), etNamePlayer2.getText().toString(),3);
                team3ply2 = new Team(6, etTeam3ply2.getText().toString(), etNamePlayer2.getText().toString(),5);

                mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

                mDb.teamDAO().insertTeam(team1ply2);
                mDb.teamDAO().insertTeam(team2ply2);
                mDb.teamDAO().insertTeam(team3ply2);
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });
    }
}
