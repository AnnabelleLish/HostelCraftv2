package me.HostelGamer.HostelCraft;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;



public class HostelCraftListener implements Listener {
	
public static HostelCraft plugin;
	
	public HostelCraftListener(HostelCraft instance) {
		plugin = instance;
	}
	
	//@EventHandler(priority = EventPriority.HIGHEST)
	//public void onPlayerJoin(final PlayerJoinEvent event)
	//{
		//plugin.loot.OnLogin(event.getPlayer().getName());
		//return;
	//}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerInteract(final PlayerInteractEvent event)
	{
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
		{
			return;
		}
		
		final Block block = event.getClickedBlock();
		
		if (block == null)
		{
			return;
		}

		
		
		final int mat = block.getTypeId();
		if (mat == Material.SIGN_POST.getId() || mat == Material.WALL_SIGN.getId())
		{
			final Sign csign = (Sign)block.getState();
			if(csign.getLine(0).equalsIgnoreCase(plugin.ColorChat("&b[hcoin]"))){
				SignChecking(csign.getLine(1), csign.getLine(2), csign.getLine(3), event.getPlayer().getName());
				return;
			}else if(csign.getLine(0).equalsIgnoreCase(plugin.ColorChat("&a[rankup]"))){
				plugin.R.loadRankup(event.getPlayer());
				return;
			}else if(csign.getLine(0).equalsIgnoreCase(plugin.ColorChat("&b[vote]"))){
				plugin.Info.loadInfoVote(event.getPlayer());
				return;
			}
			return;
		}
		event.isCancelled();
		return;
	}
	
	public void SignChecking(String num1, String num2, String num3, String player)
	{
		String line1 = num1; String line2 = num2; String line3 = num3;
		Player tPlayer = (Player) plugin.getServer().getOfflinePlayer(player);
		int Balance = plugin.getConfig().getInt(tPlayer.getName() + ".HCoin");
		int buyingCost = Integer.parseInt(line3.replaceAll("HC", "").replaceAll(",", "").replaceAll(" ", ""));
		buyingCost--;
		if(buyingCost < Balance){
			plugin.HC.loadChecking(player, line1, line2, line3, Balance);
			return;
		}else{
			tPlayer.sendMessage(plugin.ColorChat("&cYou need &3" + num3.replaceAll("HC", "") + "&bHCoin's, &c You have &3" + Balance + "&c!"));
			return;
		
		}
	}
	
}
