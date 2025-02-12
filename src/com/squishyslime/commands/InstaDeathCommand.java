package com.squishyslime.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.squishyslime.Main;

public class InstaDeathCommand implements CommandExecutor {
	public Main plugin;
	public InstaDeathCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("instadeath.cmd")) {
			if(args.length == 0) {
				if(sender instanceof Player) {
					Player p = (Player) sender;
					if(plugin.players.contains(p.getUniqueId())) {
						plugin.players.remove(p.getUniqueId());
						p.sendMessage("§3[§b§lInstaDeath§r§3]§b Disabled!");
						return true;
					}
					else {
						plugin.players.add(p.getUniqueId());
						p.sendMessage("§3[§b§lInstaDeath§r§3]§b Enabled!");
						return true;
					}
				}
				else {
					sender.sendMessage("§c§lYou have to be a player to use that command!");
					return true;
				}
			}
			else if(args.length == 1) {
				if(args[0] == "ver" || args[0] == "version") {
					sender.sendMessage("§3[§b§lInstaDeath§r§3]§b Version: §l" + plugin.currentVersion);
					return true;
				}
				Player target = Bukkit.getPlayerExact(args[0]);
				if(target == null) {
					sender.sendMessage("§c§lPlayer doesn't exist");
					return true;
				}
				if(plugin.players.contains(target.getUniqueId())) {
					plugin.players.remove(target.getUniqueId());
					target.sendMessage("§3[§b§lInstaDeath§r§3]§b Disabled!");
					sender.sendMessage("§3[§b§lInstaDeath§r§3]§b Disabled for" + target.getName() + "!");
					return true;
				}
				else {
					plugin.players.add(target.getUniqueId());
					target.sendMessage("§3[§b§lInstaDeath§r§3]§b Enabled!");
					sender.sendMessage("§3[§b§lInstaDeath§r§3]§b Enabled for" + target.getName() + "!");
					return true;
				}
			}
		}
		else {
			sender.sendMessage("§c§lYou don't have permission to use that command!");
		}
		return true;
	}

}
