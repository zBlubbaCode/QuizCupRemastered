package de.zblubba.quizcupremastered.guis;

import de.zblubba.quizcupremastered.util.ItemBuilder;
import de.zblubba.quizcupremastered.util.MessageCollection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.lang.management.OperatingSystemMXBean;

public class ModerationGUICommand implements CommandExecutor {

    private String getSpaces = "§8=============================";
    private String getGateItem = "§7Klicke, um das Item für dieses Gate zu erhalten!";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p) {
            if(p.hasPermission("quizcup.gui")) {

                //create 6x9 inventory
                Inventory inv = Bukkit.createInventory(null, 6*9, "§e§lQuizCup§c§l3 §8- §cModeration");

                //fill inventory with glass
                for(int i = 0; i < 6*9; i++) {
                    inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§8 ").build());
                }

                //set all items
                inv.setItem(4, new ItemBuilder(Material.END_CRYSTAL).setName("§e§lQuiz§6§lCup §c§l3").setLore(getSpaces, "§7Willkommen im QuizCup Moderations-GUI", "§7Hier kannst du einige Aktionen und", "§7Einstellungen ausführen.",getSpaces).build());
                inv.setItem(19, new ItemBuilder(Material.OAK_FENCE_GATE).setName("§cItems §7für die verschiedenen §cTore").setLore(getSpaces, "§aKlicke§7, um alle Items direkt in", "§7dein Inventar zu erhalten!", getSpaces).setLocalizedName("allgates").build());
                inv.setItem(21, new ItemBuilder(Material.BLACK_STAINED_GLASS).setLocalizedName("gate1").setName("§7Gate §c#1").setLore(getSpaces, getGateItem, getSpaces).build());
                inv.setItem(22, new ItemBuilder(Material.PISTON).setLocalizedName("gate2").setName("§7Gate §a#2").setLore(getSpaces, getGateItem, getSpaces).build());
                inv.setItem(23, new ItemBuilder(Material.CYAN_TERRACOTTA).setLocalizedName("gate3").setName("§7Gate §b#3").setLore(getSpaces, getGateItem, getSpaces).build());
                inv.setItem(28, new ItemBuilder(Material.STONE_PICKAXE).setLocalizedName("alltools").setName("§cItems §7für die verschiedenen §cTools").setLore(getSpaces, "§aKlicke§7, um alle Items direkt in", "§7dein Inventar zu erhalten!",getSpaces).build());
                inv.setItem(30, new ItemBuilder(Material.STICK).setLocalizedName("stick").setName("§cKnockback Stick").setLore(getSpaces, "§cPerfekt§7, um böse Leute zu schlagen",getSpaces).build());
                inv.setItem(31, new ItemBuilder(Material.NETHER_STAR).setLocalizedName("nextquestion").setName("§cnächste Frage starten").setLore(getSpaces, "§cKlicke§7, um die nächste Frage zu starten.",getSpaces).build());
                inv.setItem(32, new ItemBuilder(Material.BARRIER).setLocalizedName("closeserver").setName("§7Server §cschließen§7/§aöffnen").setLore(getSpaces, "§7Öffnet oder schließt den Server für neue Spieler", "§7Spieler, die drauf sind können danach auch wieder joinen",getSpaces).build());
                inv.setItem(33, new ItemBuilder(Material.REDSTONE).setLocalizedName("zumstart").setName("§czum Start").setLore(getSpaces, "§7Warnt alle Spieler, dass sie zum Start sollen",getSpaces).build());
                inv.setItem(47, new ItemBuilder(Material.REDSTONE_TORCH).setLocalizedName("serverinfo").setName("§cInformationen zum Server").setLore(getSpaces, "§7Spieler online §8- §c" + Bukkit.getOnlinePlayers().size(), "§7verbrauchter ram §8- §c" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000 + "MB", "§7max-ram §8- §c" + Runtime.getRuntime().maxMemory() / 1000000 + "MB", "§7cpu-kerne §8- §c" + Runtime.getRuntime().availableProcessors(),getSpaces).build());
                inv.setItem(49, new ItemBuilder(Material.ENDER_EYE).setLocalizedName("tpall").setName("§cTP-All").setLore(getSpaces, "§7Teleportiert §calle §7Spieler zu dir",getSpaces).build());
                inv.setItem(51, new ItemBuilder(Material.SPRUCE_SIGN).setLocalizedName("closechat").setName("§7Chat §cschließen§7/§aöffnen").setLore(getSpaces, "§7Öffnet oder schließt den Chat",getSpaces).build());

                p.openInventory(inv);
            } else p.sendMessage(MessageCollection.getNoPerms());
        } else {
            sender.sendMessage(MessageCollection.mustbePlayer());
            return false;
        }
        return false;
    }
}
