package me.HostelGamer.HostelCraft;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.earth2me.essentials.api.Economy;

public class Ranking {
	public static HostelCraft plugin;
	public String AddPrefixs = "";
	public Ranking(HostelCraft instance){
		plugin = instance;
	}
	public boolean loadRankup(CommandSender sender){
		if(sender instanceof Player){
			Player player = (Player) sender;
			PermissionManager pex = PermissionsEx.getPermissionManager();
			PermissionUser user = pex.getUser(player);
			
			if(user.inGroup("Private")){
			Rankup(player.getName(), 50, "Private", "PFC" , "&2[PFC]&2");
			return true;
			}else if(user.inGroup("PFC")){
			Rankup(player.getName(), 100, "PFC", "Corporal" , "&2[Corporal]&2");
			return true;
			}else if(user.inGroup("Corporal")){
			Rankup(player.getName(), 150, "Corporal", "Sergeant" , "&2[Sergeant]&2");
			return true;
			}else if(user.inGroup("Sergeant")){
			Rankup(player.getName(), 200, "Sergeant", "StaffSergeant" , "&b[StaffSergeant]&2");
			return true;
			}else if(user.inGroup("StaffSergeant")){
			Rankup(player.getName(), 250, "StaffSergeant", "SFC" , "&b[SFC]&2");
			return true;
			}else if(user.inGroup("SFC")){
			Rankup(player.getName(), 300, "SFC", "MasterSergeant" , "&b[MasterSergeant]&2");
			return true;
			}else if(user.inGroup("MasterSergeant")){
			Rankup(player.getName(), 350, "MasterSergeant", "FirstSergeant" , "&b[FirstSergeant]&2");
			return true;
			}else if(user.inGroup("FirstSergeant")){
			Rankup(player.getName(), 400, "FirstSergeant", "SergeantMajor" , "&b[SergeantMajor]&2");
			return true;
			}else if(user.inGroup("SergeantMajor")){
			Rankup(player.getName(), 450, "SergeantMajor", "CSM" , "&b[CSM]&2");
			return true;
			}else if(user.inGroup("CSM")){
			Rankup(player.getName(), 500, "CSM", "SecondLt" , "&7[SecondLt]&2");
			return true;
			}else if(user.inGroup("SecondLt")){
			Rankup(player.getName(), 550, "SecondLt", "FirstLt" , "&7[FirstLt]&2");
			return true;
			}else if(user.inGroup("FirstLt")){
			Rankup(player.getName(), 600, "FirstLt", "Captain" , "&7[Captain]&2");
			return true;
			}else if(user.inGroup("Captain")){
			Rankup(player.getName(), 750, "Captain", "Major" , "&6[Major]&2");
			return true;
			}else if(user.inGroup("Major")){
			Rankup(player.getName(), 1000, "Major", "LtColonel" , "&6[LtColonel]&2");
			return true;
			}else if(user.inGroup("LtColonel")){
			Rankup(player.getName(), 1250, "LtColonel", "Colonel" , "&6[Colonel]&2");
			return true;
			}else if(user.inGroup("Colonel")){
			Rankup(player.getName(), 1500, "Colonel", "BrigadierGeneral" , "&5[BrigadierGeneral]&2");
			return true;
			}else if(user.inGroup("BrigadierGeneral")){
			Rankup(player.getName(), 2500, "BrigadierGeneral", "MajorGeneral" , "&5[MajorGeneral]&2");
			return true;
			}else if(user.inGroup("MajorGeneral")){
			Rankup(player.getName(), 5000, "MajorGeneral", "LieutenantGeneral" , "&5[LieutenantGeneral]&2");
			return true;
			}else if(user.inGroup("LieutenantGeneral")){
			Rankup(player.getName(), 7500, "LieutenantGeneral", "General" , "&5[General]&2");
			return true;
			}else if(user.inGroup("General")){
			Rankup(player.getName(), 10000, "General", "GOA" , "&e[GOA]&2");
			return true;
			}else if(user.inGroup("GOA")){
			Rankup(player.getName(), 25000, "GOA", "President" , "&e[President]&2");
			return true;
			}else if(user.inGroup("User")){
			Rankup(player.getName(), 25, "User", "Private" , "&2[Private]&2");
			return true;
			}
		}else{}
		return false;
	}
	
	public void Rankup(String player, int amount, String rank, String Cnewrank , String newrank){
		Player Tplayer = plugin.getServer().getPlayer(player);
		PermissionManager pex = PermissionsEx.getPermissionManager();
		PermissionUser user = pex.getUser(player);
		if(!(user.inGroup("President"))){
		try{
		if(Economy.hasMore(player, amount)){
			plugin.getServer().broadcastMessage(plugin.ColorChat("&3" + player + ",&e Has been promoted to " + newrank));
			Tplayer.sendMessage(plugin.ColorChat("&c$" + amount + ",&4 Has been taken from your balance, &aRemaining: $" + Economy.getMoney(player)));
			Tplayer.sendMessage(plugin.ColorChat("&a" + amount + " Exp, Has been given to you as a gift!"));
			
				CheckGroups(player, newrank);
				user.removeGroup(rank);
				user.addGroup(Cnewrank);
				Economy.subtract(player, amount);
				Tplayer.giveExp(amount);
				
			
		}else{
			Tplayer.sendMessage(plugin.ColorChat("&cInsufficient funds: You need: $" + amount + ", To buy: " + newrank));
		}
		}catch(Exception ex){plugin.logger.info("Error Something went wrong with /rankup");}
		}else{Tplayer.sendMessage(plugin.ColorChat("&dCongratulations, &eYou are the highest rank in the game!"));}
	}
	
	public void CheckGroups(String player, String newrank){
		PermissionManager pex = PermissionsEx.getPermissionManager();
		PermissionUser user = pex.getUser(player);
		
		if(user.inGroup("Admin")){
			user.setPrefix(newrank + " &4[&eAdmin&4]&e ", "");
		}else if(user.inGroup("Mod")){
			user.setPrefix(newrank + " &4[&3Mod&4]&e ", "");
		}else if(user.inGroup("TMod")){
			user.setPrefix(newrank + " &4[&6Helper&4]&e ", "");
		}else if(user.inGroup("VIP-Major")){
			user.setPrefix(newrank + " &b[&5VIP-Major&b]&5 ", "");
		}else if(user.inGroup("VIP-Minor")){
			user.setPrefix(newrank + " &b[&dVIP-Minor&b]&d ", "");
		}else if(user.inGroup("Donor-Major")){
			user.setPrefix(newrank + " &b[&6Donor-Major&b]&6 ", "");
		}else if(user.inGroup("Donor-Minor")){
			user.setPrefix(newrank + " &b[&cDonor-Minor&b]&c ", "");
		}else{
			user.setPrefix(newrank + " ", "");
		}
		
	}

}
