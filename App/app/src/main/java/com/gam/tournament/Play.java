package com.gam.tournament;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gam.tournament.DataBase.AppDatabase;
import com.gam.tournament.DataBase.Team;

import java.util.ArrayList;
import java.util.List;

public class Play extends AppCompatActivity {

    TextView tvTeamL, tvTeamV;
    EditText etResL, etResV;
    Button btEnd;
    AppDatabase mDb;
    Team local, visitor;
    int gL, gV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Referenciamos componentes
        tvTeamL = (TextView) findViewById(R.id.playTeamL);
        tvTeamV = (TextView) findViewById(R.id.playTeamV);
        etResL = (EditText) findViewById(R.id.resLocal);
        etResV = (EditText) findViewById(R.id.resVisitor);
        btEnd = (Button) findViewById(R.id.btEndMatch);

        //Mostramos los equipos que tienen que jugar
        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        nextMatch();

        //Una vez escrito el resultado:

        btEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gL = Integer.parseInt(etResL.getText().toString());
                gV = Integer.parseInt(etResV.getText().toString());
                if(gL < gV){
                    winner(visitor, gV, gL);
                    loser(local, gL, gV);
                }
                else if(gL > gV){
                    winner(local, gL, gV);
                    loser(visitor, gV, gL);
                }
                else {
                    drawn(local, gL);
                    drawn(visitor, gV);
                }

                mDb.teamDAO().updateTeam(local);
                mDb.teamDAO().updateTeam(visitor);
                etResL.setText("0");
                etResV.setText("0");
                nextMatch();
            }
        });
    }

    private void nextMatch() {
        //Coger los equipos a enfrentarse

        int next = teamsLessMatchtes(mDb.teamDAO().getPlayerTeams(mDb.teamDAO().getTeamById(1).getPlayer()));

        local = mDb.teamDAO().getTeamById(next + 1);
        visitor = mDb.teamDAO().getTeamById(local.getNextMatch());

        //Los mostramos
        tvTeamL.setText(local.getName());
        tvTeamV.setText(visitor.getName());

        //Modificar los siguientes partidos
        int nextLocal = local.getNextMatch() + 2;
        if (nextLocal == 8){
            nextLocal = 2;
        }
        local.setNextMatch(nextLocal);

        int nextVisitor = visitor.getNextMatch() - 2;
        if (nextVisitor == -1){
            nextVisitor = 5;
        }
        visitor.setNextMatch(nextVisitor);
    }

    private int teamsLessMatchtes(List<Team> allTeams) {
        int played = allTeams.get(0).getPlayed();
        int pos = 0;
        for (int i = 1; i < allTeams.size(); i++){
            if(played > allTeams.get(i).getPlayed()){
                played = allTeams.get(i).getPlayed();
                pos = 2 * i;
            }
        }
        return pos;
    }

    private void winner(Team team, int gF, int gA){
        team.setWon(team.getWon() + 1);
        team.setGf(team.getGf() + gF);
        team.setGa(team.getGa() + gA);
    }

    private void loser(Team team, int gF, int gA){
        team.setLost(team.getLost() + 1);
        team.setGf(team.getGf() + gF);
        team.setGa(team.getGa() + gA);
    }

    private void drawn (Team team, int g){
        team.setDrawn(team.getDrawn() + 1);
        team.setGf(team.getGf() + g);
        team.setGa(team.getGa() + g);

    }
}
