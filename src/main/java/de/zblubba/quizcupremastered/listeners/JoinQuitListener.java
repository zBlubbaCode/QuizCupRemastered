package de.zblubba.quizcupremastered.listeners;

import de.zblubba.quizcupremastered.QuizCupRemastered;
import de.zblubba.quizcupremastered.util.MessageCollection;
import org.bukkit.*;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class JoinQuitListener implements Listener {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        event.setJoinMessage(MessageCollection.getJoinMessage(p));
        QuizCupRemastered.playerList.add(p.getName());

        if(QuizCupRemastered.isServerClosed) {if(!p.hasPermission("quizcup.bypass") && !QuizCupRemastered.allowedList.contains(p.getName())) {p.kickPlayer("§e§lQuizCup\n\n§cDer Server ist zurzeit geschlossen!");}}
        if(p.hasPermission("quizcup.admin")) {
            ItemStack boots = new ItemStack(Material.LEATHER_BOOTS); LeatherArmorMeta meta = (LeatherArmorMeta) boots.getItemMeta(); meta.setColor(Color.fromRGB(255, 0 ,0));meta.addEnchant(Enchantment.PROTECTION_FALL, 100, true); meta.addItemFlags(ItemFlag.HIDE_ENCHANTS); meta.setDisplayName("§cAdmin §8| §c" + p.getName());
            boots.setItemMeta(meta);
            p.getInventory().setItem(36, boots);
        }

        if(QuizCupRemastered.config.getBoolean("join.playsound")) {
            Bukkit.getScheduler().runTaskLater(QuizCupRemastered.getPlugin(QuizCupRemastered.class), () -> {
                p.playSound(p.getLocation(), Sound.valueOf(QuizCupRemastered.config.getString("join.sound")), 255, 1);
            }, 40);
        }

        Configuration config = QuizCupRemastered.config;
        if(config.getBoolean("join.teleport_to_spawn")) {
            p.teleport(new Location(Bukkit.getWorld(config.getString("spawn.world")), config.getDouble("spawn.x"), config.getDouble("spawn.y"), config.getDouble("spawn.z"), (float) config.getDouble("spawn.yaw"), (float) config.getDouble("config.pitch")));
        }

        QuizCupRemastered.getInstance().scoreboard.setScoreboard(p);
        QuizCupRemastered.getInstance().scoreboard.setTab(p);

        for(Player players : Bukkit.getOnlinePlayers()) {
            QuizCupRemastered.getInstance().scoreboard.updateTab(players);
        }
    }


    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();

        event.setQuitMessage(MessageCollection.getQuitMessage(p));
        QuizCupRemastered.playerList.remove(p.getName());
    }


}
