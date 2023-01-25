package de.zblubba.quizcupremastered.commands;

import de.zblubba.quizcupremastered.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CloseChatCommand implements CommandExecutor {

    public static boolean isChatClosed;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission("quizcup.chat.close")) {

            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("close")) {
                    isChatClosed = true;
                    Bukkit.broadcastMessage(MessageCollection.getCloseChat());
                } else if(args[0].equalsIgnoreCase("open")){
                    isChatClosed = false;
                    Bukkit.broadcastMessage(MessageCollection.getOpenChat());
                } else {
                    sender.sendMessage(MessageCollection.getPrefix() + "Nutze §c/chat <close | open>");
                }
            } else {
                sender.sendMessage(MessageCollection.getPrefix() + "Nutze §c/chat <close | open>");
            }

        } else {
            sender.sendMessage(MessageCollection.getNoPerms());
        }

        return false;
    }
}