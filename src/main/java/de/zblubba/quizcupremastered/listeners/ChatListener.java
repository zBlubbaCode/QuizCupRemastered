package de.zblubba.quizcupremastered.listeners;

import de.zblubba.quizcupremastered.commands.CloseChatCommand;
import de.zblubba.quizcupremastered.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        String msg = event.getMessage();

        event.setMessage(p.hasPermission("quizcup.colored") ? ChatColor.translateAlternateColorCodes('&', event.getMessage()) : event.getMessage());

        if(CloseChatCommand.isChatClose && !p.hasPermission("quizcup.chat.bypass")) {
            event.setCancelled(true);
            p.sendMessage(MessageCollection.getChatClosed());
        }

        if(p.hasPermission("quizcup.admin")) { event.setFormat("§cAdmin §8| §c%1$s §8» §7%2$s");
        } else if(p.hasPermission("quizcup.helper")) { event.setFormat("§6Helfer §8| §6%1$s §8» §7%2$s");
        } else { event.setFormat("§bSpieler §8| §b%1$s §8» §7%2$s"); }

        if(msg.startsWith("@t")) {
            if(p.hasPermission("quizcup.teamchat.write")) {
                event.setCancelled(true);
                for(Player team : Bukkit.getOnlinePlayers()) {
                    if(team.hasPermission("quizcup.teamchat.see")) {
                        team.sendMessage("§e§l|| §cTeamChat §8| §c" + p.getName() + msg.replaceAll("@t", " §7»"));
                    }
                }
            }
            event.setCancelled(true);
        }
    }
}