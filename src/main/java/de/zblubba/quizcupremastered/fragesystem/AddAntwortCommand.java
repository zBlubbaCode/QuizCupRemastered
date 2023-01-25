package de.zblubba.quizcupremastered.fragesystem;

import de.zblubba.quizcupremastered.QuizCupRemastered;
import de.zblubba.quizcupremastered.util.MessageCollection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.*;

public class AddAntwortCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String antwort = "";

        if(sender.hasPermission("quitcup.frage.antwort.add")) {
            if(args.length >= 3) {
                int fragenNummer = Integer.parseInt(args[0]);
                int type = Integer.parseInt(args[1]);

                char antwortLetter;
                if(type == 1) {antwortLetter = 'A';} else if(type == 2) {antwortLetter = 'B';} else if(type == 3) {antwortLetter = 'C';} else antwortLetter = 'D';

                for (int i = 2; i < args.length; i++) {
                    antwort = antwort + args[i] + " ";
                    antwort = antwort.replaceAll("null", "");
                }
                //antwort = antwort.substring(1);

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(MessageCollection.getMySQLHost() + MessageCollection.getMySQLDatabase(),MessageCollection.getMySQLUser(), MessageCollection.getMySQLPassword());

                    String query = "update fragen set antwort" + antwortLetter + " = ? where id = ?";
                    PreparedStatement statement = conn.prepareStatement(query);

                    statement.setString(1, antwort);
                    statement.setInt(2, fragenNummer);

                    statement.execute();

                    statement.close();
                    conn.close();

                    sender.sendMessage(QuizCupRemastered.getPrefix + "Die Antwort §c" + antwortLetter + " §7wurde §cerfolgreich §7zur Frage " + fragenNummer + " erstellt!");

                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
        }

        return false;
    }
}
