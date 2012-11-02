package me.HostelGamer.HostelCraft;

import java.text.DecimalFormat;

import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.earth2me.essentials.api.Economy;

public class HCoin {
	public static HostelCraft plugin;
	
	public HCoin(HostelCraft instance) {
		plugin = instance;
	}

	public boolean loadHCoin(CommandSender sender, String[] args, String commandLabel){
		if(args.length == 0){
			if(sender instanceof Player){
			HCoinCheckBal(sender.getName());
			return true;
			}else{
				sender.sendMessage("You are the console silly");
			}
			return false;
			}
		if(args.length > 0){
			if(args[0].equalsIgnoreCase("help")){
				sender.sendMessage(plugin.ColorChat("&4----------&e[&bHCoin Help&e]&4----------"));
				sender.sendMessage(plugin.ColorChat("&4Main Cmd: &a/Hcoin &6or &a/coin"));
				sender.sendMessage(plugin.ColorChat("&4Checking Balance: &c/" + commandLabel + " bal &6or &c/" + commandLabel + " balance"));
				sender.sendMessage(plugin.ColorChat("&4Buying HCoin: &c/" + commandLabel + " buy"));
				return true;
			}else if(args[0].equalsIgnoreCase("buy")){
				sender.sendMessage(plugin.ColorChat("&eTo buy HCoin Vist:&b http://hostelcraft.net/donate"));
				return true;
			}else if(args[0].equalsIgnoreCase("bal") || args[0].equalsIgnoreCase("balance")){
				if(sender instanceof Player){
				HCoinCheckBal(sender.getName());
				return true;
				}else{
					sender.sendMessage("You are the console silly");
				}
				return false;
			}else if(args[0].equalsIgnoreCase("reset")){
				HCoinReset(sender, args);
			}else if(args[0].equalsIgnoreCase("add")){
				
				if(sender.isOp() || (!(sender instanceof Player))){
				if(args.length > 2){
					
				HCoinAdd(args[1], args[2]);
				
				return true;
				}else if(args.length < 3){
					sender.sendMessage(plugin.ColorChat("&cError: Invaild Args!"));
					return true;
				}
				}else{
					sender.sendMessage(plugin.ColorChat("&4You do not have Permission to use this command!"));
					return false;
				}
				return false;
			}
		}
		return false;
	}
	
	public boolean HCoinCheckBal(String player){
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		
		Player tPlayer = (Player) plugin.getServer().getOfflinePlayer(player);
		String Balance = plugin.getConfig().getString(tPlayer.getName() + ".HCoin");
<<<<<<< HEAD
		if(Balance != null){
=======
>>>>>>> 08c5967989304bb9bb5ff9428c39fe30e02fa93e
		double amount = Double.parseDouble(Balance);
		DecimalFormat formatter = new DecimalFormat("#,###");
		tPlayer.sendMessage(plugin.ColorChat("&3You're &bHCoin &3balances is:&b " + formatter.format(amount) + "&3!"));
		return true;
		}else{
			tPlayer.sendMessage(plugin.ColorChat("&cYou don't have any HCoins,&e You can buy some at:&b http://hostelcraft.net/donate"));
		}
		return false;
	}
	
	public boolean HCoinReset(CommandSender sender, String[] args){
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		if(sender.isOp() || !(sender instanceof Player)){
			if(args.length > 2){
				DecimalFormat formatter = new DecimalFormat("#,###");
				int intamount = Integer.parseInt(args[2]);
				String amount2 = formatter.format(intamount);
			Player tPlayer = (Player) plugin.getServer().getOfflinePlayer(args[1]);
			plugin.getConfig().set(tPlayer.getName() + ".HCoin", args[2]);
			tPlayer.sendMessage(plugin.ColorChat("&cYou're &bHCoin &cwas reset to:&b " + amount2));
			sender.sendMessage(plugin.ColorChat("&3 " + tPlayer.getName() + "'s &2HCoin was reset to&b " + amount2));
			}else{
				sender.sendMessage(plugin.ColorChat("&4Invalid Args: /coin reset <Playername> <Amount>!"));
			}
			return true;
		}else{
			sender.sendMessage(plugin.ColorChat("&4You do not have permissions to use this command!"));
		}
		return false;
	}

	
	public boolean HCoinAdd(String player, String amount){
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		
		
		if(plugin.getServer().getOfflinePlayer(player) == null){
			plugin.logger.info("Unable to find: " + player);
		}else{
			
			Player tPlayer = (Player) plugin.getServer().getOfflinePlayer(player);
		int Balance = plugin.getConfig().getInt(tPlayer.getName() + ".HCoin");
		
		int totalHCoin = Balance + Integer.parseInt((amount));
		DecimalFormat formatter = new DecimalFormat("#,###");
		int intamount = Integer.parseInt(amount);
		String amount2 = formatter.format(intamount);
		
		plugin.getConfig().set(tPlayer.getName() + ".HCoin", totalHCoin);
		
		tPlayer.sendMessage(plugin.ColorChat("&2" + amount2 + " HCoins, &6has been added to your account!"));
		
		plugin.logger.info(amount + " HCoins have been added to " + player + "'s account!");
		plugin.saveConfig();
		plugin.reloadConfig();
		}
		return false;
	}

	public void loadChecking(Sign sign, String name) {

		
	}
	


	
}
