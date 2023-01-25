package de.zblubba.quizcupremastered.fragesystem;

import de.zblubba.quizcupremastered.QuizCupRemastered;
import de.zblubba.quizcupremastered.util.MessageCollection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CreateFrageCommand implements CommandExecutor {

    String frage;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("quizcup.frage.create")) {
            if (args.length >= 2) {
                int numberOfFrage = Integer.parseInt(args[0]);

                for (int i = 1; i < args.length; i++) {
                    frage = frage + args[i] + " ";
                    frage = frage.replaceAll("null", "");
                }

                Connection connection = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection(
                            MessageCollection.getMySQLHost() + MessageCollection.getMySQLDatabase(),
                            MessageCollection.getMySQLUser(), MessageCollection.getMySQLPassword());

                    String query = " insert into fragen (id, frage)"
                            + " values (?, ?)";

                    PreparedStatement statement;
                    statement = connection.prepareStatement(query);
                    statement.setInt(1, numberOfFrage);
                    statement.setString(2, frage);

                    statement.execute();

                    statement.close();
                    connection.close();

                    sender.sendMessage(QuizCupRemastered.getPrefix + "Die Frage §c" + numberOfFrage + " §7wurde §cerfolgreich §7erstellt!");

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        }

        return false;
    }
}
