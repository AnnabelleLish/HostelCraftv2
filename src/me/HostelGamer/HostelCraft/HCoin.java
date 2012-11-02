package me.HostelGamer.HostelCraft;

import java.text.DecimalFormat;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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
		Player tPlayer = (Player) plugin.getServer().getOfflinePlayer(player);
		String Balance = plugin.getConfig().getString(tPlayer.getName() + ".HCoin");
		if(Balance != null){
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
		
		tPlayer.sendMessage(plugin.ColorChat("&3" + amount2 + " HCoins, &6has been added to your " +
				"account!"));
		
		plugin.logger.info(amount + " HCoins have been added to " + player + "'s account!");
		plugin.saveConfig();
		plugin.reloadConfig();
		}
		return false;
	}
	
	public void HCoinTake(String player, int HowMuch, int Balance){
		Player tPlayer = (Player) plugin.getServer().getOfflinePlayer(player);
		int remove = (int)Balance - (int)HowMuch;
		plugin.getConfig().set(tPlayer.getName() + ".HCoin", remove);
		plugin.saveConfig();
		plugin.reloadConfig();
		tPlayer.sendMessage(plugin.ColorChat("&3" + HowMuch + " HCoins, &chas been removed from your account!"));
		return;
	}
	
	public void HCoinSignBuying(String player, String itemName, String ID){
		Player tPlayer = (Player) plugin.getServer().getOfflinePlayer(player);
		if(ID.equalsIgnoreCase("id:1")){
			try{
			int add = Integer.parseInt(itemName.replaceAll("$", "").replaceAll("IGM", "").replaceAll(",", "").replaceAll(" ", ""));
				Economy.add(player, add);
				tPlayer.sendMessage(plugin.ColorChat("&a" + add + " In-Game Money, &ahas been added from your account!"));
				return;
			}catch(Exception ex){}
		}else if (ID.equalsIgnoreCase("id:2")){
			int ItemEnchantLevel = Integer.parseInt(itemName.replaceAll("Prot", "").replaceAll(" ", ""));
			PlayerInventory pi = tPlayer.getInventory();
			if(pi.firstEmpty() > 31 || pi.firstEmpty() == -1){
				tPlayer.sendMessage(plugin.ColorChat("&cYou're Inventory is full or is all most full!"));
				return;
			}else{
			AddingGear(player, ItemEnchantLevel);
			tPlayer.sendMessage(plugin.ColorChat("&aProtection: " + ItemEnchantLevel + " Gear, &ahas been added from your account!"));
			tPlayer.sendMessage(plugin.ColorChat("&4If only one peace of the gear shows up Logout then back in!"));
			}
			
			return;
		}else if (ID.equalsIgnoreCase("id:3")){
			
			String MystInt = itemName.replaceAll("Mystcrates", "").replaceAll(" ", "").replaceAll("x", "");
			int currentInt = (int) plugin.getConfig().getInt(tPlayer.getName()+".Mystcrates");
			int totalMysts = currentInt + Integer.parseInt(MystInt);
			plugin.getConfig().set(tPlayer.getName() + ".Mystcrates", totalMysts);
			plugin.saveConfig();
			plugin.reloadConfig();
			tPlayer.sendMessage(plugin.ColorChat("&3Use: &4/Myst open &b(To open a crate)"));
			tPlayer.sendMessage(plugin.ColorChat("&3" + MystInt + "&b Myst Crates have been credited to Your Account!"));
			return;
			
			}
			
		return;
	}
	
	public void AddingGear(String player, int level){
		Player tPlayer = (Player) plugin.getServer().getOfflinePlayer(player);
		PlayerInventory pi = tPlayer.getInventory();
		
		ItemStack item = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemStack item1 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemStack item2 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		ItemStack item3 = new ItemStack(Material.DIAMOND_BOOTS, 1);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		item1.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		item2.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		item3.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		pi.addItem(item);
		pi.addItem(item1);
		pi.addItem(item2);
		pi.addItem(item3);
		return;
	}
	
	public boolean loadChecking(String player, String line1, String line2, String line3, int Balance){
		int buyingCost = Integer.parseInt(line3.replaceAll("HC", "").replaceAll(",", "").replaceAll(" ", ""));
		HCoinTake(player, buyingCost, Balance);
		HCoinSignBuying(player, line1, line2);
		
		return false;
	}
}