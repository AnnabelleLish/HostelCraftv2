package me.HostelGamer.HostelCraft;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.entity.Player;

public class DailyLootCrate {

public static HostelCraft plugin;
	
	public DailyLootCrate(HostelCraft instance) {
		plugin = instance;
	}
	
	public void OnLogin(String player){
		Player tPlayer = (Player) plugin.getServer().getOfflinePlayer(player);
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Calendar cal = Calendar.getInstance();
		String Time = dateFormat.format(cal.getTime());
		int checkAccountAmount = plugin.getConfig().getInt(tPlayer.getName() + ".LootCrates");
		String checkTime = plugin.getConfig().getString(tPlayer.getName() + ".LootLastDate");
		if(checkAccountAmount == 0){
			
				if(checkTime == Time){
					tPlayer.sendMessage("You have gotten a LootCrate today!");
					return;
				}else{
					tPlayer.sendMessage("Vlah");
				}
				
				if(checkTime == null){
					plugin.getConfig().set(tPlayer.getName() + ".LootLastDate", Time);
					plugin.saveConfig();
					plugin.reloadConfig();
					tPlayer.sendMessage("1 Daily Loot Crate has added to your account!");
					return;
				}
				
		}else{
			tPlayer.sendMessage("You must have more then 0 Crates");
		}
		return;
	}
	
}
