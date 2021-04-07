package me.EpicKidCreations.Permisons.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarnCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length < 2) {
			return false;
		}
		String targetname = args[0];
		Player target = Bukkit.getPlayer(targetname);
		StringBuilder builder = new StringBuilder();
		for(int i = 1; i < args.length; i++) {
			builder.append(args[i]);
		}
		String reason = builder.toString();
		if(target == null) {
			sender.sendMessage(targetname + " is not a player");
			return true;
		}
		if (sender.hasPermission("punisher.*")) {
			Bukkit.broadcastMessage(target.getDisplayName() + " has been warned by " + sender.getName());
			target.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "You are warned by " + sender.getName() + " for " + reason);
			return true;
		}else if (sender.hasPermission("punisher.warn")) {
			Bukkit.broadcastMessage(target.getDisplayName() + " has been warned by " + sender.getName());
			target.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "You are warned by " + sender.getName() + " for " + reason);
			return true;
		}else if (sender.hasPermission("*")) {
			Bukkit.broadcastMessage(target.getDisplayName() + " has been warned by " + sender.getName());
			target.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "You are warned by " + sender.getName() + " for " + reason);
			return true;
		}
		else {
			sender.sendMessage("Error: No permison");
		}
		return true;
	}
}
