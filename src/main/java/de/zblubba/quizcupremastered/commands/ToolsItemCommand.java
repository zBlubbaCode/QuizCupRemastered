package de.zblubba.quizcupremastered.commands;

import de.zblubba.quizcupremastered.util.MessageCollection;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ToolsItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p) {
            if(p.hasPermission("quizcup.tools")) {
                if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("stick")) {
                        ItemStack stick = new ItemStack(Material.STICK, 1);
                        ItemMeta stickMeta = stick.getItemMeta();

                        stickMeta.setDisplayName("§aK§bn§co§dk§eb§aa§bc§dk §9S§1t§2i§ec§5k §4§l3000");
                        ArrayList<String> lore = new ArrayList<>();
                        lore.add("§8=============================");
                        lore.add("§7Mit diesem Stock kannst du jedes");
                        lore.add("§cUngetüm §7in die Ecke zwengen!");
                        lore.add("§8=============================");
                        stickMeta.setLore(lore);
                        stickMeta.addEnchant(Enchantment.KNOCKBACK, 255, true);

                        stick.setItemMeta(stickMeta);
                        p.getInventory().addItem(stick);
                    } else if(args[0].equalsIgnoreCase("frage")) {
                        ItemStack star = new ItemStack(Material.NETHER_STAR, 1);
                        ItemMeta starMeta = star.getItemMeta();

                        starMeta.setDisplayName("§cnächste Frage §8- §7rechtsklick");
                        ArrayList<String> lore = new ArrayList<>();
                        lore.add("§8=============================");
                        lore.add("§7Rechtsklicke, um automatisch die");
                        lore.add("§7nächste Frage zu starten");
                        lore.add("§8=============================");
                        starMeta.setLore(lore);
                        starMeta.setLocalizedName("nextquestionItem");

                        star.setItemMeta(starMeta);
                        p.getInventory().addItem(star);
                    } else if(args[0].equalsIgnoreCase("server")) {
                        ItemStack barrier = new ItemStack(Material.BARRIER, 1);
                        ItemMeta barrierMeta = barrier.getItemMeta();

                        barrierMeta.setDisplayName("§7Serverstatus §8- §aan§8 | §caus");
                        ArrayList<String> lore = new ArrayList<>();
                        lore.add("§8=============================");
                        lore.add("§7Rechtsklicke, um den Server zu");
                        lore.add("§7schließen, oder öffnen");
                        lore.add("§8=============================");
                        barrierMeta.setLore(lore);
                        barrierMeta.setLocalizedName("serverstateitem");

                        barrier.setItemMeta(barrierMeta);
                        p.getInventory().addItem(barrier);
                    } else if(args[0].equalsIgnoreCase("start")) {
                        ItemStack start = new ItemStack(Material.REDSTONE, 1);
                        ItemMeta startMeta = start.getItemMeta();

                        startMeta.setDisplayName("§7zum Start §6Meldung §8- §7rechtsklick");
                        ArrayList<String> lore = new ArrayList<>();
                        lore.add("§8=============================");
                        lore.add("§7Rechtsklicke, um eine Meldung zu senden,");
                        lore.add("§7dass alle Spieler zu Start müssen.");
                        lore.add("§8=============================");
                        startMeta.setLore(lore);
                        startMeta.setLocalizedName("zumstartitem");

                        start.setItemMeta(startMeta);
                        p.getInventory().addItem(start);
                    } else p.sendMessage("§cAlias: /toolsitem <stick | frage | server | start>");
                } else p.sendMessage("§cAlias: /toolsitem <stick | frage | server | start>");
            } else p.sendMessage(MessageCollection.getNoPerms());
        } else sender.sendMessage(MessageCollection.mustbePlayer());
        return false;
    }
}
