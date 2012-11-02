package me.HostelGamer.HostelCraft;

import java.text.DecimalFormat;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Info {
	public static HostelCraft plugin;
	public Info(HostelCraft instance) {
		plugin = instance;
	}
	
	public boolean loadInfoVote(CommandSender sender){
		sender.sendMessage(plugin.ColorChat("&3-------------------&4[&aVoting&4]&3-------------------"));
		sender.sendMessage(plugin.ColorChat("&8__________________________________________________"));
		sender.sendMessage(plugin.ColorChat("&4What do you get for voting?"));
		sender.sendMessage(plugin.ColorChat("&6You will get &b3 Diamonds&6 &&a $40 In Game Money &dEvery link!"));
		sender.sendMessage(plugin.ColorChat("&8-------------------&6[&7links&6]&8-------------------"));
		sender.sendMessage(plugin.ColorChat("&bhttp://www.hostelcraft.net/vote"));
		sender.sendMessage(plugin.ColorChat("&8__________________________________________________"));
		return true;
		
	}
	
	public boolean loadInfoDonations(CommandSender sender, String[] args){
		if(args.length == 0){
			String getDonations = plugin.getConfig().getString("ServerInfo.TotalDonations");
			sender.sendMessage(plugin.ColorChat("&6Total Server Donations: &a$" + getDonations));
			return true;
		}else{
		if(args[0].equalsIgnoreCase("send") && args.length > 3){
		if(sender.isOp() || !(sender instanceof Player)){
			
			plugin.getServer().broadcastMessage(plugin.ColorChat("&4[&dDonation&4] : &3" + args[1] + ", &dJust donated for:&7 " + args[2] + "&d!"));
			
			int currentInt = (int) plugin.getConfig().getInt("ServerInfo.TotalDonations");
			DecimalFormat formatter = new DecimalFormat("#,###.96");
			int total = currentInt + Integer.parseInt(args[3]);
			
			plugin.getServer().broadcastMessage(plugin.ColorChat("&6Total Server Donations: &a$" + formatter.format(total)));
			
			
			plugin.getConfig().set("ServerInfo.TotalDonations", total);
			plugin.saveConfig();
			return true;
		}}
		}
		
		return false;
	}

}
