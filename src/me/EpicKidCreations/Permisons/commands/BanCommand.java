package me.EpicKidCreations.Permisons.commands;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.EpicKidCreations.Permisons.main;

public class BanCommand implements CommandExecutor{
	
	main main;

	public BanCommand(main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length < 2) {
			return false;
		}
		Player player = (Player) sender;
		String targetname = args[0];
		Player target = Bukkit.getPlayer(targetname);
		StringBuilder builder = new StringBuilder();
		String reason = builder.toString();
		if (target == null) {
			player.sendMessage(targetname + " is not a player");
			return true;
		}
		if (player.hasPermission("punisher.*")) {
			Bukkit.getBanList(Type.NAME).addBan(targetname, reason, null, player.getName());
			target.kickPlayer("You are banned");
			Bukkit.broadcastMessage(target + " has been banned for " + reason + " by " + player.getName());
			return true;
		} else if (player.hasPermission("punisher.tempmute")) {
			Bukkit.getBanList(Type.NAME).addBan(targetname, reason, null, player.getName());
			target.kickPlayer("You are banned");
			Bukkit.broadcastMessage(target + " has been banned for " + reason + " by " + player.getName());
			return true;
		} else if (player.hasPermission("*")) {
			Bukkit.getBanList(Type.NAME).addBan(targetname, reason, null, player.getName());
			target.kickPlayer("You are banned");
			Bukkit.broadcastMessage(target + " has been banned for " + reason + " by " + player.getName());
			return true;
		} else {
			player.sendMessage("Error: No permison");
		}
		return true;
	}
	
}
