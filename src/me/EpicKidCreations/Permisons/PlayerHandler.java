package me.EpicKidCreations.Permisons;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerHandler {
	
	int Owner = 140;
	int Manager = 130;
	int Administrator = 120;
	int Developer = 110;
	int SModerator = 100;
	int cModerator = 90;
	int Builder = 80;
	int Trainee = 70;
	int God = 60;
	int Pro = 50;
	int MVPplus = 40;
	int MVP = 30;
	int VIPplus = 20;
	int VIP = 10;
	int Default = 0;
	
	
	public void punishHistory(Player p, String Type, String Reason) {
		File f = new File("plugins/punishment" + p.getUniqueId() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.set(Type, Reason);
		try {
			yml.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRank(Player p) {
		File f = new File("plugins/Ranks/PlayerData" + p.getUniqueId() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		return yml.getInt("Rank");
	}
	
	
}
