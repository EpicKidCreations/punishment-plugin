package me.EpicKidCreations.Permisons;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Events implements Listener {

	PlayerHandler PlayerHandler;
	main main;

	public Events(PlayerHandler playerHandler, main main) {
		PlayerHandler = playerHandler;
		this.main = main;
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		Date date = main.FileHandler.mutedPlayers.getOrDefault( p.getUniqueId().toString(), new Date());
		System.out.println(date.toString());
		if (main.FileHandler.mutedPlayers.containsKey(p.getUniqueId().toString()) && 
				date.after(new Date())) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You have been muted. You can not chat.");
		}
	}
}
