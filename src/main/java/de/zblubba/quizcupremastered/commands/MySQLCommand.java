package de.zblubba.quizcupremastered.commands;

import de.zblubba.quizcupremastered.util.MessageCollection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        StringBuilder message = new StringBuilder();
        for(int i = 0; i < args.length; i++) {
            message.append(" ").append(args[i]);
        }

        sender.sendMessage(message + "");

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    MessageCollection.getMySQLHost() + MessageCollection.getMySQLDatabase(),
                    MessageCollection.getMySQLUser(), MessageCollection.getMySQLPassword());

            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM points");
            int points = 0;
            String uuid = null;
            while (resultSet.next()) {
                points = resultSet.getInt("points");
                uuid = resultSet.getString("uuid");
            }
            resultSet.close();
            statement.close();
            connection.close();

            sender.sendMessage("uuid: " + uuid + " points: " + points);
        } catch(Exception exception) {

        }

        return false;
    }
}
