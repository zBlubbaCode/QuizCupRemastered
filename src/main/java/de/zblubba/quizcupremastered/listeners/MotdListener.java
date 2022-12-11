package de.zblubba.quizcupremastered.listeners;

import de.zblubba.quizcupremastered.QuizCupRemastered;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MotdListener implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        String motdMessageFirst = QuizCupRemastered.config.getString("motd.firstLine"); motdMessageFirst = ChatColor.translateAlternateColorCodes('&', motdMessageFirst);
        String motdMessageSecond = QuizCupRemastered.config.getString("motd.secondLine"); motdMessageSecond = ChatColor.translateAlternateColorCodes('&', motdMessageSecond);

        event.setMotd(motdMessageFirst + "\n" + motdMessageSecond);
    }
}