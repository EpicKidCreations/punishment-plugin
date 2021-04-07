package me.EpicKidCreations.Permisons;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.EpicKidCreations.Permisons.commands.BanCommand;
import me.EpicKidCreations.Permisons.commands.KickCommand;
import me.EpicKidCreations.Permisons.commands.MutedCommand;
import me.EpicKidCreations.Permisons.commands.TempBanCommand;
import me.EpicKidCreations.Permisons.commands.TempMuteCommand;
import me.EpicKidCreations.Permisons.commands.UnBanCommand;
import me.EpicKidCreations.Permisons.commands.UnMuteCommand;
import me.EpicKidCreations.Permisons.commands.WarnCommand;

public class main extends JavaPlugin implements Listener{

	
	public FileHandler FileHandler = new FileHandler();
	PlayerHandler PlayerHandler = new PlayerHandler();
	Events Events = new Events(PlayerHandler, this);
	
	@Override
	public void onEnable() {
		FileHandler.Setup(this);
		getServer().getPluginManager().registerEvents(Events, this);
		getCommand("warn").setExecutor(new WarnCommand());
		getCommand("mute").setExecutor(new MutedCommand(this));
		getCommand("unmute").setExecutor(new UnMuteCommand(this));
		getCommand("tempmute").setExecutor(new TempMuteCommand(this));
		getCommand("kick").setExecutor(new KickCommand(this));
		getCommand("ban").setExecutor(new BanCommand(this));
		getCommand("unban").setExecutor(new UnBanCommand(this));
		getCommand("tempban").setExecutor(new TempBanCommand(this));
	}
	
	@Override
	public void onDisable() {
		
	}
	
	
}
