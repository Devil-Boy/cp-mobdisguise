package pgDev.bukkit.CPMD;

import org.bukkit.ChatColor;
import org.bukkit.event.*;

import me.desmin88.mobdisguise.api.event.*;

public class MDEventListener implements Listener {
	CommandPointsMobDisguiseBridge plugin;
	
	public MDEventListener(CommandPointsMobDisguiseBridge pluginI) {
		plugin = pluginI;
	}
	
	// Listen Methods
	@EventHandler
	public void onDisguiseAsMob(DisguiseAsMobEvent event) {
		if (!plugin.hasPermissions(event.getPlayer(), "CPMD.disguise.free")) {
			if (plugin.cpAPI.hasAccount(event.getPlayer().getName(), plugin)) {
				if (plugin.cpAPI.hasPoints(event.getPlayer().getName(), plugin.pluginSettings.disguiseCosts.get(event.getMobType()), plugin)) {
					plugin.cpAPI.removePoints(event.getPlayer().getName(), plugin.pluginSettings.disguiseCosts.get(event.getMobType()), "Disguised as a " + event.getMobType(), plugin);
					int remainingPoints = plugin.cpAPI.getPoints(event.getPlayer().getName(), plugin);
					if (remainingPoints == 1) {
						event.getPlayer().sendMessage(ChatColor.BLUE + "You have " + remainingPoints + " point remaining.");
					} else {
						event.getPlayer().sendMessage(ChatColor.BLUE + "You have " + remainingPoints + " points remaining.");
					}
				} else {
					event.getPlayer().sendMessage(ChatColor.RED + "You do not have the required commandpoints to disguise as this mob.");
					event.setCancelled(true);
				}
			} else {
				event.getPlayer().sendMessage(ChatColor.RED + "You do not have a commandpoints account.");
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onDisguiseAsPlayer(DisguiseAsPlayerEvent event) {
		if (!plugin.hasPermissions(event.getPlayer(), "CPMD.disguise.free")) {
			if (plugin.cpAPI.hasAccount(event.getPlayer().getName(), plugin)) {
				if (plugin.cpAPI.hasPoints(event.getPlayer().getName(), plugin.pluginSettings.disguiseCosts.get("player"), plugin)) {
					plugin.cpAPI.removePoints(event.getPlayer().getName(), plugin.pluginSettings.disguiseCosts.get("player"), "Disguised as a player:" + event.getName(), plugin);
					int remainingPoints = plugin.cpAPI.getPoints(event.getPlayer().getName(), plugin);
					if (remainingPoints == 1) {
						event.getPlayer().sendMessage(ChatColor.BLUE + "You have " + remainingPoints + " point remaining.");
					} else {
						event.getPlayer().sendMessage(ChatColor.BLUE + "You have " + remainingPoints + " points remaining.");
					}
				} else {
					event.getPlayer().sendMessage(ChatColor.RED + "You do not have the required commandpoints to disguise as another person.");
					event.setCancelled(true);
				}
			} else {
				event.getPlayer().sendMessage(ChatColor.RED + "You do not have a commandpoints account.");
				event.setCancelled(true);
			}
		}
	}
	
}
