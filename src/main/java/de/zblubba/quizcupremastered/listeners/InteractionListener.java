package de.zblubba.quizcupremastered.listeners;

import de.zblubba.quizcupremastered.QuizCupRemastered;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractionListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Action action = event.getAction();

        if(p.hasPermission("quizcup.tools")) {
            if (action != Action.RIGHT_CLICK_AIR) return;
            ItemStack item = p.getItemInHand();
            if (item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();

                switch(meta.getLocalizedName()) {
                    case "gate1item" -> p.performCommand("gate 1");
                    case "gate2item" -> p.performCommand("gate 2");
                    case "gate3item" -> p.performCommand("gate 3");
                    case "nextquestionItem" -> p.performCommand("startfrage next");
                }

                if (meta.getLocalizedName().equals("§cNächste Frage")) {
                    p.performCommand("startfrage next");
                } else if (meta.getLocalizedName().equals("§cTP-ALL")) {
                    p.performCommand("tp @a -2.5 107 8.5");
                } else if (meta.getLocalizedName().equals("§6Front Gate")) {
                    p.performCommand("gate front");
                } else if (meta.getLocalizedName().equals("§bMid Gate")) {
                    p.performCommand("gate mid");
                } else if (meta.getLocalizedName().equals("§aEnd Gate")) {
                    p.performCommand("gate end");
                } else if (meta.getLocalizedName().equals("§9Spieler §8- §cSichtbarkeit")) {
                    TextComponent onMessage = new net.md_5.bungee.api.chat.TextComponent(QuizCupRemastered.getPrefix + "[§aAN§7] §8- §7Klicke, um Spieler §cunsichtbar §7zu machen!");
                    onMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§7Klicke um Spieler §cunsichtbar §7zu machen")));
                    onMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/invis on"));
                    TextComponent offMessage = new net.md_5.bungee.api.chat.TextComponent(QuizCupRemastered.getPrefix + "[§cAUS§7] §8- §7Klicke, um Spieler §asichtbar §7zu machen!");
                    offMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§7Klicke um Spieler §asichtbar §7zu machen")));
                    offMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/invis off"));

                    p.sendMessage("§cSpielersichtbarkeit §8- §7Einstellungen §aan§7/§caus");
                    p.spigot().sendMessage(onMessage);
                    p.spigot().sendMessage(offMessage);
                }
            }
        }

    }
}
