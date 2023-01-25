package de.zblubba.quizcupremastered.commands;

import de.zblubba.quizcupremastered.QuizCupRemastered;
import de.zblubba.quizcupremastered.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CloseServerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("quizcup.state")) {
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("open")) {
                    QuizCupRemastered.config.set("server_closed", false);
                    QuizCupRemastered.saveFile(QuizCupRemastered.configFile, QuizCupRemastered.config);
                    sender.sendMessage(QuizCupRemastered.getPrefix + "Der Server ist nun wieder §ageöffnet§7!");

                    QuizCupRemastered.isServerClosed = false;
                } else if(args[0].equalsIgnoreCase("close")) {
                    for(Player players : Bukkit.getOnlinePlayers()) {
                        QuizCupRemastered.allowedList.add(players.getUniqueId().toString());
                    }
                    QuizCupRemastered.isServerClosed = true;

                    QuizCupRemastered.config.set("server_closed", true);
                    QuizCupRemastered.saveFile(QuizCupRemastered.configFile, QuizCupRemastered.config);
                    Bukkit.broadcastMessage(QuizCupRemastered.getPrefix + "§cDer Server ist nun geschlossen! Niemand kann noch joinen! Alle, die jetzt auf dem Server sind können jedoch wieder joinen.");
                } else sender.sendMessage("§cNutze: /closeserver <open | close>");
            } else sender.sendMessage("§cNutze: /closeserver <open | close>");
        } else sender.sendMessage(MessageCollection.getNoPerms());
        return false;
    }
}
