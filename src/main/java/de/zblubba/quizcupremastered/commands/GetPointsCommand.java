package de.zblubba.quizcupremastered.commands;

import de.zblubba.quizcupremastered.QuizCupRemastered;
import de.zblubba.quizcupremastered.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class GetPointsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("quizcup.points")) {
            if(args.length == 1) {
                int value = Integer.parseInt(args[0]);
                sender.sendMessage(QuizCupRemastered.getPrefix + "Spieler mit §c" + value + " §7Punkten");

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(MessageCollection.getMySQLHost() + MessageCollection.getMySQLDatabase(),MessageCollection.getMySQLUser(), MessageCollection.getMySQLPassword());

                    Statement statement = conn.createStatement();
                    String query = "select * from points where points = " + value;
                    ResultSet resultSet = statement.executeQuery(query);

                    while (resultSet.next()) {
                        sender.sendMessage("§a" + Bukkit.getPlayer(resultSet.getString("uuid")).getName() + " §8- " + resultSet.getInt("points"));
                    }

                    resultSet.close();
                    statement.close();
                    conn.close();

                } catch(Exception e) {
                    e.printStackTrace();
                }
            } else sender.sendMessage("§cNutze: /getpoints <punkte>");
        } else sender.sendMessage(MessageCollection.getNoPerms());
        return false;
    }
}
