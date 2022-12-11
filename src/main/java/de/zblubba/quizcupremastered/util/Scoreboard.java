package de.zblubba.quizcupremastered.util;

import de.zblubba.quizcupremastered.QuizCupRemastered;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class Scoreboard {

    public static void setScoreboard(Player p) {
        String title = QuizCupRemastered.config.getString("scoreboard.title"); title = title.replace("&", "§"); title = title.replace("{user}", p.getName());
        ArrayList<String> scores = (ArrayList<String>) QuizCupRemastered.config.getList("scoreboard.scores");

        org.bukkit.scoreboard.Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("QuizCup", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.setDisplayName(title);

        for(int i = 0; i < scores.size(); i++) {
            String score = scores.get(scores.size() - i - 1); score = score.replace("&", "§"); score = score.replace("{user}", p.getName());
            obj.getScore(score).setScore(i);
        }

        p.setScoreboard(board);
    }

    public void setTab(Player p) {
        org.bukkit.scoreboard.Scoreboard board = p.getScoreboard();

        Team admin = board.registerNewTeam("0000Admin");
        Team helper = board.registerNewTeam("0005Helfer");
        Team spieler = board.registerNewTeam("1111Spieler");

        admin.setPrefix("§cAdmin §8| §c");
        helper.setPrefix("§6Helfer §8| §6");
        spieler.setPrefix("§bSpieler §8| §b");

        for(Player players : Bukkit.getOnlinePlayers()) {
            if(players.hasPermission("quizcup.admin")) {
                admin.addEntry(players.getName());
            } else if(players.hasPermission("quizcup.helper")) {
                helper.addEntry(players.getName());
            } else {
                spieler.addEntry(players.getName());
            }
        }
    }

    public void updateTab(Player p) {
        org.bukkit.scoreboard.Scoreboard board = p.getScoreboard();

        Team admin = board.getTeam("0000Admin");
        Team helper = board.getTeam("0005Helfer");
        Team spieler = board.getTeam("1111Spieler");

        if (admin == null || helper == null || spieler == null) {
            setTab(p);
            return;
        }

        for (Player players : Bukkit.getOnlinePlayers()) {
            if (players.hasPermission("quizcup.admin")) {
                admin.addEntry(players.getName());
            } else if (players.hasPermission("quizcup.helper")) {
                helper.addEntry(players.getName());
            } else {
                spieler.addEntry(players.getName());
            }
        }
    }
}
