package com.gam.tournament;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

import com.gam.tournament.DataBase.AppDatabase;
import com.gam.tournament.DataBase.Team;

import java.util.ArrayList;
import java.util.List;

public class Table extends AppCompatActivity {

    private TableLayout tableLayout;
    private String[] header = {"Pos","Name","Player","PJ","G","E","P","Pts","GF","GC","DG"};
    private ArrayList<String[]> rows = new ArrayList<>();
    List<Team> teams = new ArrayList<>();
    ArrayList<Team> teamsByPts = new ArrayList<>();
    AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        tableLayout = (TableLayout) findViewById(R.id.table);

        TableDynamic tableDynamic = new TableDynamic(tableLayout, getApplicationContext());
        tableDynamic.setHeader(header);
        tableDynamic.setData(getTeams());
    }

    private ArrayList<String[]> getTeams() {

        mDb = AppDatabase.getDatabase(getApplicationContext());
        teams = mDb.teamDAO().getAllTeams();
        teamsByPts = teamsOrder(list2ArrayList(teams));
        rows = fillRows(teamsByPts);
        return rows;
    }

    private ArrayList<Team> teamsOrder(ArrayList<Team> allTeams){
        int arrayPos;
        ArrayList<Team> teamsOrderByPts = new ArrayList<>();

        while (allTeams.size() >= 1){
            arrayPos = getTeamMorePts(allTeams);
            teamsOrderByPts.add(allTeams.get(arrayPos));
            allTeams.remove(arrayPos);
        }
        return teamsOrderByPts;
    }

    private int getTeamMorePts(ArrayList<Team> allTeams){
        int morePts, pos;
        morePts = allTeams.get(0).getPoints();
        pos = 0;
        for (int i = 1; i < allTeams.size(); i++){
            if (allTeams.get(i).getPoints() > morePts){
                morePts = allTeams.get(i).getPoints();
                pos = i;
            }
            else if(allTeams.get(i).getPoints() == morePts){
                if (allTeams.get(i).getGD() > allTeams.get(pos).getGD()){
                    pos = i;
                }
            }
        }

        return pos;
    }

    private ArrayList<String[]> fillRows(ArrayList<Team> teamsByPts) {
        ArrayList<String[]> row = new ArrayList<>();

        for(int i = 0; i < teamsByPts.size(); i++){
            Team team = teamsByPts.get(i);
            row.add(new String[]
                    {String.valueOf(i + 1), team.getName(), team.getPlayer(), String.valueOf(team.getPlayed()),
                            String.valueOf(team.getWon()), String.valueOf(team.getDrawn()),
                            String.valueOf(team.getLost()), String.valueOf(team.getPoints()),
                            String.valueOf(team.getGf()), String.valueOf(team.getGa()),
                            String.valueOf(team.getGD())});
        }

        return row;
    }

    private ArrayList<Team> list2ArrayList(List<Team> list){
        ArrayList<Team> arrayList = new ArrayList<>();
        arrayList.addAll(list);
        return arrayList;
    }

}
