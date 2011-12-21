package pgDev.bukkit.CPMD;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

import me.desmin88.mobdisguise.api.MobDisguiseListener;
import me.desmin88.mobdisguise.api.event.*;

public class MDEventListener extends MobDisguiseListener implements Listener {
	CommandPointsMobDisguiseBridge plugin;
	
	public MDEventListener(CommandPointsMobDisguiseBridge pluginI) {
		plugin = pluginI;
	}
	
	// Listen Methods
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
	
	public void onUnDisguise(UnDisguiseEvent event) {
	}
	
	public void onMobDisguiseCommand(DisguiseCommandEvent event) {
		/* Better method found
		// Player check
		Player disguiser;
		if(event.getSender() instanceof Player){
			disguiser = (Player)event.getSender();
		}else{
			event.getSender().sendMessage("This command can only be run by a player.");
			event.setCancelled(true);
			return;
		}
		
		String[] params = event.getArgs();
		if (params.length != 0) {
			if (params[0].equalsIgnoreCase("p")) { // Player disguise attempt
			} else { // Mob disguise attempt
				if (plugin.mobTypeList.contains(params[0])) {
					if (!plugin.hasPermissions(disguiser, "CPMD.disguise.free")) {
						if (plugin.cpAPI.hasAccount(disguiser.getName(), plugin)) {
							if (plugin.cpAPI.hasPoints(disguiser.getName(), plugin.pluginSettings.disguiseCosts.get(params[0]), plugin)) {
								plugin.cpAPI.removePoints(disguiser.getName(), plugin.pluginSettings.disguiseCosts.get(params[0]), "Disguised as a " + params[0], plugin);
							} else {
								disguiser.sendMessage(ChatColor.RED + "You do not have the required commandpoints to disguise as this mob.");
								event.setCancelled(true);
							}
						} else {
							disguiser.sendMessage(ChatColor.RED + "You do not have a commandpoints account.");
							event.setCancelled(true);
						}
					}
				}
			}
		}
		*/
	}
	
}
