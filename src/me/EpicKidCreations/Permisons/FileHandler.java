package me.EpicKidCreations.Permisons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileHandler {

	String Path = "plugins/punishment";
	FileConfiguration muted;
	File mutedFile;
	public Map<String, Date> mutedPlayers = new HashMap<>();

	public FileHandler() {

	}

	public void Setup(main main) {
		/*
		 * File MainDirectory = new File(Path); if(!MainDirectory.exists()){
		 * MainDirectory.mkdir(); }
		 */
		if (!main.getDataFolder().exists()) {
			main.getDataFolder().mkdir();
		}
		mutedFile = new File(main.getDataFolder(), "muted.yml");
		if (!mutedFile.exists()) {
			main.saveResource("muted.yml", false);
		}
		muted = YamlConfiguration.loadConfiguration(mutedFile);
		SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss:aa");
		for (String s : muted.getStringList("muted")) {
			String[] split = s.split("//");
			if(split.length == 1) {
				mutedPlayers.put(split[0], null);
				continue;
			}
			try {
				Date date = format.parse(split[1]);
				mutedPlayers.put(split[0], date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void saveMuted() {
		List<String> list = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss:aa");
		for(Entry<String,Date> entry : mutedPlayers.entrySet()) {
			if(entry.getValue() == null) {
				list.add(entry.getKey());
				continue;
			}
			String date = format.format(entry.getValue());
			list.add(entry.getKey() + "//" + date);
		}
		muted.set("muted", list);
		try {
			muted.save(mutedFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public FileConfiguration getMutedConfig() {
		return muted;
	}
}
