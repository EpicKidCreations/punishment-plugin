package me.EpicKidCreations.Permisons.commands;

import org.bukkit.BanList.Type;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.EpicKidCreations.Permisons.main;

public class TempBanCommand implements CommandExecutor{
	
	main main;

	public TempBanCommand(main main) {
		this.main = main;
	}
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
		if (target == null) {
			player.sendMessage(targetname + " is not a player");
			return true;
		}
		Date date = new Date();
		date.setTime(date.getTime() + time * 1000);
		StringBuilder builder = new StringBuilder();
		for (int i = 2; i < args.length; i++) {
			builder.append(args[i]);
		}
		String reason = builder.toString();
		if (player.hasPermission("punisher.*")) {
			Bukkit.getBanList(Type.NAME).addBan(targetname, reason, date, player.getName());
			target.kickPlayer("You are banned");
			Bukkit.broadcastMessage(target + " has been banned for " + reason + " by " + player.getName());
			return true;
		} else if (player.hasPermission("punisher.tempmute")) {
			Bukkit.getBanList(Type.NAME).addBan(targetname, reason, date, player.getName());
			target.kickPlayer("You are banned");
			Bukkit.broadcastMessage(target + " has been banned for " + reason + " by " + player.getName());
			return true;
		} else if (player.hasPermission("*")) {
			Bukkit.getBanList(Type.NAME).addBan(targetname, reason, date, player.getName());
			target.kickPlayer("You are banned");
			Bukkit.broadcastMessage(target + " has been banned for " + reason + " by " + player.getName());
			return true;
		} else {
			player.sendMessage("Error: No permison");
		}
		return true;
	}
	
}
