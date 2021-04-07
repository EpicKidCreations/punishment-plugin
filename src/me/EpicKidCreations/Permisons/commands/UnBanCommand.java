package me.EpicKidCreations.Permisons.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.BanList.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.EpicKidCreations.Permisons.main;

public class UnBanCommand implements CommandExecutor{
	
	main main;

	public UnBanCommand(main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length < 2) {
			return false;
		}
		Player player = (Player) sender;
		String targetname = args[0];
		OfflinePlayer target = Bukkit.getPlayer(targetname);
		StringBuilder builder = new StringBuilder();
		String reason = builder.toString();
		if (player.hasPermission("punisher.*")) {
			Bukkit.getBanList(Type.NAME).pardon(target.getName());
			Bukkit.broadcastMessage(target + " has been unbanned for " + reason + " by " + player.getName());
			return true;
		} else if (player.hasPermission("punisher.tempmute")) {
			Bukkit.getBanList(Type.NAME).pardon(target.toString());
			Bukkit.broadcastMessage(target + " has been unbanned for " + reason + " by " + player.getName());
			return true;
		} else if (player.hasPermission("*")) {
			Bukkit.getBanList(Type.NAME).pardon(target.toString());
			Bukkit.broadcastMessage(target + " has been unbanned for " + reason + " by " + player.getName());
			return true;
		} else {
			player.sendMessage("Error: No permison");
		}
		return true;
	}
	
}

