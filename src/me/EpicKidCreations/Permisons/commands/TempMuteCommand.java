package me.EpicKidCreations.Permisons.commands;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.EpicKidCreations.Permisons.main;

public class TempMuteCommand implements CommandExecutor {
	main main;

	public TempMuteCommand(main main) {
		this.main = main;
	}

	/**
	 * This will return null if its not a number
	 * 
	 * @param s the time
	 * @return the time that is added with the "multiplier" like s, m, h
	 */
	public Long getTime(String s) {
		Pattern pattern = Pattern.compile("\\d+([^\\d])?");
		Matcher matcher = pattern.matcher(s);

		if (!matcher.find()) {
			try {
				return Long.parseLong(s.replaceAll("\\w", ""));
			} catch (Exception e) {
				return null;
			}
		}

		long multiplier = 1;

		if (matcher.groupCount() == 1) {
			switch (matcher.group(1)) {
			case "s":
				break;
			case "m":
				multiplier = 60;
				break;
			case "h":
				multiplier = 360;
				break;
			}
		}

		s = s.replaceAll("[^\\d]", "");

		try {
			return Long.parseLong(s) * multiplier;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length < 3) {
			return false;
		}
		Player player = (Player) sender;
		String targetname = args[0];
		Player target = Bukkit.getPlayer(targetname);
		Long time = getTime(args[1]);
		if (time == null) {
			player.sendMessage("Eror: enter proper time");
			return true;
		}
		Date date = new Date();
		date.setTime(date.getTime() + time * 1000);
		StringBuilder builder = new StringBuilder();
		for (int i = 2; i < args.length; i++) {
			builder.append(args[i]);
		}
		String reason = builder.toString();
		if (target == null) {
			player.sendMessage(targetname + " is not a player");
			return true;
		}
		if (player.hasPermission("punisher.*")) {
			Bukkit.broadcastMessage(target.getDisplayName() + " has been tempmuted by " + player.getName());
			target.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "You are tempmuted by " + player.getName()
					+ " for " + reason + " until " + date.toString());
			main.FileHandler.mutedPlayers.put(target.getUniqueId().toString(), date);
			main.FileHandler.saveMuted();
			return true;
		} else if (player.hasPermission("punisher.tempmute")) {
			Bukkit.broadcastMessage(target.getDisplayName() + " has been tempmuted by " + player.getName());
			target.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "You are tempmuted by " + player.getName()
					+ " for " + reason + " until " + date.toString());
			main.FileHandler.mutedPlayers.put(target.getUniqueId().toString(), date);
			main.FileHandler.saveMuted();
		} else if (player.hasPermission("*")) {
			Bukkit.broadcastMessage(target.getDisplayName() + " has been tempmuted by " + player.getName());
			target.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "You are tempmuted by " + player.getName()
					+ " for " + reason + " until " + date.toString());
			main.FileHandler.mutedPlayers.put(target.getUniqueId().toString(), date);
			main.FileHandler.saveMuted();
		} else {
			player.sendMessage("Error: No permison");
		}
		return true;
	}
}
