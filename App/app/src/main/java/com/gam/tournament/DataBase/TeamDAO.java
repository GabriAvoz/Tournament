package com.gam.tournament.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface TeamDAO {

    @Insert(onConflict = IGNORE)
    void insertTeam(Team team);

    @Delete
    void deleteTeam(Team team);

    @Query("DELETE FROM Team")
    void deleteAll();

    @Query("SELECT * FROM Team ORDER BY id ASC")
    List<Team> getAllTeams();

    @Update(onConflict = REPLACE)
    void updateTeam(Team team);

    @Query("SELECT * FROM Team WHERE id = :id")
    Team getTeamById(int id);

    @Query("SELECT * FROM Team WHERE player = :player")
    List<Team> getPlayerTeams(String player);

}
