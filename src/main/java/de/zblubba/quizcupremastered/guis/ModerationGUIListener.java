package de.zblubba.quizcupremastered.guis;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ModerationGUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();

        if(event.getCurrentItem() == null) return;
        if(!event.getView().getTitle().equals("§e§lQuizCup§c§l3 §8- §cModeration")) return;
        event.setCancelled(true);

        if(!event.getCurrentItem().getItemMeta().hasLocalizedName()) return;

        switch(event.getCurrentItem().getItemMeta().getLocalizedName()) {
            case "allgates" -> {
                p.performCommand("gateitem 1");
                p.performCommand("gateitem 2");
                p.performCommand("gateitem 3");
            }
            case "gate1" -> p.performCommand("gateitem 1");
            case "gate2" -> p.performCommand("gateitem 2");
            case "gate3" -> p.performCommand("gateitem 3");

            case "alltools" -> {
                p.performCommand("toolsitem stick");
                p.performCommand("toolsitem frage");
                p.performCommand("toolsitem server");
                p.performCommand("toolsitem start");
            }
            case "stick" -> p.performCommand("toolsitem stick");
            case "nextquestion" -> p.performCommand("toolsitem frage");
            case "closeserver" -> p.performCommand("toolsitem server");
            case "zumstart" -> p.performCommand("toolsitem start");

            case "tpall" -> p.performCommand("tp @a @p");
            case "closechat" -> p.performCommand("closechat toggle");
        }
    }
}
