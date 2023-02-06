package de.zblubba.quizcupremastered.listeners;

import de.zblubba.quizcupremastered.QuizCupRemastered;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class GeneralListeners implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(!event.getPlayer().hasPermission("quizcup.break")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if(!event.getPlayer().hasPermission("quizcup.break")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDropItem(EntityDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(!event.getPlayer().hasPermission("quizcup.break")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        if(!p.hasPermission("quizcup.helper")) {
            Bukkit.getScheduler().runTaskLater(QuizCupRemastered.getPlugin(QuizCupRemastered.class), () -> {
                Configuration config = QuizCupRemastered.config;
                p.teleport(new Location(Bukkit.getWorld(config.getString("spawn.world")), config.getDouble("spawn.x"), config.getDouble("spawn.y"), config.getDouble("spawn.z"), (float) config.getDouble("spawn.yaw"), (float) config.getDouble("config.pitch")));
            }, 20);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        EntityDamageEvent.DamageCause cause = event.getCause();
        if(event.getEntity() instanceof Player) {
            if(cause != EntityDamageEvent.DamageCause.VOID) {
                event.setCancelled(true);
            }
        }
    }
}