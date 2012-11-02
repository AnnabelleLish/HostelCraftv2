package me.HostelGamer.HostelCraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.earth2me.essentials.api.Economy;



public class HostelCraftListener implements Listener {
	
public static HostelCraft plugin;
	
	public HostelCraftListener(HostelCraft instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
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
			
				if (csign.getLine(0).equalsIgnoreCase("[hcoin]"))
				{
					event.getPlayer().sendMessage("Working with HCoin");
					String Checking = csign.getLine(2);
					if(Checking.equalsIgnoreCase("1")){
						try {
							Economy.add(event.getPlayer().getName(), 1500);
							event.getPlayer().sendMessage("Money Added!s");
						} catch (Exception ex) {
							event.getPlayer().sendMessage("Some thing went wrong with loadChecking " + ex);
						}
					}
					
					event.setCancelled(true);
					return;
				}
			
		}
	}
	

}
