package de.zblubba.quizcupremastered.commands;

import de.zblubba.quizcupremastered.util.MessageCollection;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GateItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p) {
            if(p.hasPermission("quizcup.gates")) {
                if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("1")) {
                        ItemStack gate = new ItemStack(Material.BLACK_STAINED_GLASS, 1);
                        ItemMeta meta = gate.getItemMeta();

                        meta.setDisplayName("§7Gate §c#1 §8- §7Rechtsklick");
                        meta.setLocalizedName("gate1item");
                        gate.setItemMeta(meta);

                        p.getInventory().addItem(gate);
                    } else if(args[0].equalsIgnoreCase("2")) {
                        ItemStack gate = new ItemStack(Material.PISTON, 1);
                        ItemMeta meta = gate.getItemMeta();

                        meta.setDisplayName("§7Gate §a#2 §8- §7Rechtsklick");
                        meta.setLocalizedName("gate2item");
                        gate.setItemMeta(meta);

                        p.getInventory().addItem(gate);
                    } else if(args[0].equalsIgnoreCase("3")) {
                        ItemStack gate = new ItemStack(Material.CYAN_TERRACOTTA, 1);
                        ItemMeta meta = gate.getItemMeta();

                        meta.setDisplayName("§7Gate §b#3 §8- §7Rechtsklick");
                        meta.setLocalizedName("gate3item");
                        gate.setItemMeta(meta);

                        p.getInventory().addItem(gate);
                    } else p.sendMessage("§cAlias: /gateitem <1 | 2 | 3>");
                } else p.sendMessage("§cAlias: /gateitem <1 | 2 | 3>");
            } else p.sendMessage(MessageCollection.getNoPerms());
        } else sender.sendMessage(MessageCollection.mustbePlayer());
        return false;
    }
}
