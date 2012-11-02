package me.HostelGamer.HostelCraft;


import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class HostelCraft extends JavaPlugin {
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static HostelCraft plugin;
	//Other Classes
	public final Ranking R = new Ranking(this);
	public final HCoin HC = new HCoin(this);
	public final Mystcrate My = new Mystcrate(this);
	public final Info Info = new Info(this);
	public final HostelCraftListener hl = new HostelCraftListener(this);

	
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(hl, this);
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info("[" + pdfFile.getName() + "] Version: "+ pdfFile.getVersion() + " is now Enabled!");
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info("[" + pdfFile.getName() + "] is now Disabled!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd,String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("vote")){
			Info.loadInfoVote(sender);
			return true;
		}
		
		if (commandLabel.equalsIgnoreCase("donations") || commandLabel.equalsIgnoreCase("donate")){
			Info.loadInfoDonations(sender, args);
			return true;
		}
		
		if (commandLabel.equalsIgnoreCase("hcoin") || commandLabel.equalsIgnoreCase("coin")) {
			HC.loadHCoin(sender, args, commandLabel);
			return true;
		}
		
		if(commandLabel.equalsIgnoreCase("rankup")){
			R.loadRankup(sender);
			return true;
		}
		
		if (commandLabel.equalsIgnoreCase("mystcrate") || commandLabel.equalsIgnoreCase("myst")) {
			My.loadMystcrate(sender, args);
			return true;
		}
		return false;
		}
	
	public String ColorChat(String string) {
		string = string.replaceAll("&0", ChatColor.BLACK + "");
		string = string.replaceAll("&1", ChatColor.DARK_BLUE + "");
		string = string.replaceAll("&2", ChatColor.DARK_GREEN + "");
		string = string.replaceAll("&3", ChatColor.DARK_AQUA + "");
		string = string.replaceAll("&4", ChatColor.DARK_RED + "");
		string = string.replaceAll("&5", ChatColor.DARK_PURPLE + "");
		string = string.replaceAll("&6", ChatColor.GOLD + "");
		string = string.replaceAll("&7", ChatColor.GRAY + "");
		string = string.replaceAll("&8", ChatColor.DARK_GRAY + "");
		string = string.replaceAll("&9", ChatColor.BLUE + "");
		string = string.replaceAll("&a", ChatColor.GREEN + "");
		string = string.replaceAll("&b", ChatColor.AQUA + "");
		string = string.replaceAll("&c", ChatColor.RED + "");
		string = string.replaceAll("&d", ChatColor.LIGHT_PURPLE + "");
		string = string.replaceAll("&e", ChatColor.YELLOW + "");
		string = string.replaceAll("&f", ChatColor.WHITE + "");

		return string;
	}
}
