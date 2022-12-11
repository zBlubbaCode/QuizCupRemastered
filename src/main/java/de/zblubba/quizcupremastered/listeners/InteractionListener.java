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

        if(action == Action.RIGHT_CLICK_BLOCK) {
            if(event.getClickedBlock().getType() == Material.OAK_BUTTON) {
                p.performCommand("b");
            }
        }

        if(p.hasPermission("quizcup.tools")) {
            if (action != Action.RIGHT_CLICK_AIR) return;
            ItemStack item = p.getItemInHand();
            if (item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                if (meta.getDisplayName().equals("§cNächste Frage")) {
                    p.performCommand("startfrage next");
                } else if (meta.getDisplayName().equals("§cTP-ALL")) {
                    p.performCommand("tp @a -2.5 107 8.5");
                } else if (meta.getDisplayName().equals("§6Front Gate")) {
                    p.performCommand("gate front");
                } else if (meta.getDisplayName().equals("§bMid Gate")) {
                    p.performCommand("gate mid");
                } else if (meta.getDisplayName().equals("§aEnd Gate")) {
                    p.performCommand("gate end");
                } else if (meta.getDisplayName().equals("§9Spieler §8- §cSichtbarkeit")) {
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
