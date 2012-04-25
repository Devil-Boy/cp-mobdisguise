package pgDev.bukkit.CPMD;

import me.desmin88.mobdisguise.api.MobDisguiseAPI;

import org.bukkit.ChatColor;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerQuitEvent;

import pgDev.bukkit.DisguiseCraft.api.PlayerDisguiseEvent;


public class DCEventListener implements Listener {
	CommandPointsMobDisguiseBridge plugin;
	
	public DCEventListener(CommandPointsMobDisguiseBridge pluginI) {
		plugin = pluginI;
	}
	
	// Listen Methods
	@EventHandler
	public void onDisguise(PlayerDisguiseEvent event) {
		System.out.println("Disguised!");
		if (plugin.dcAPI.isDisguised(event.getPlayer())) {
			System.out.println("Pre disguised!");
			if (event.getDisguise().mob == plugin.dcAPI.getDisguise(event.getPlayer()).mob) {
				return;
			}
		}
		if (!event.getDisguise().isPlayer()) {
			System.out.println("Not player");
			if (!plugin.hasPermissions(event.getPlayer(), "CPMD.disguise.free")) {
				if (plugin.cpAPI.hasAccount(event.getPlayer().getName(), plugin)) {
					if (plugin.cpAPI.hasPoints(event.getPlayer().getName(), plugin.pluginSettings.disguiseCosts.get(event.getDisguise().mob.name().toLowerCase()), plugin)) {
						plugin.cpAPI.removePoints(event.getPlayer().getName(), plugin.pluginSettings.disguiseCosts.get(event.getDisguise().mob.name().toLowerCase()), "Disguised as a " + event.getDisguise().mob.name(), plugin);
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
		} else {
			System.out.println("Is player");
			if (!plugin.hasPermissions(event.getPlayer(), "CPMD.disguise.free")) {
				if (plugin.cpAPI.hasAccount(event.getPlayer().getName(), plugin)) {
					if (plugin.cpAPI.hasPoints(event.getPlayer().getName(), plugin.pluginSettings.disguiseCosts.get("player"), plugin)) {
						plugin.cpAPI.removePoints(event.getPlayer().getName(), plugin.pluginSettings.disguiseCosts.get("player"), "Disguised as a player:" + event.getDisguise().data.getFirst(), plugin);
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
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (plugin.pluginSettings.undisguiseOnExit && plugin.dcAPI.isDisguised(event.getPlayer())) {
			MobDisguiseAPI.undisguisePlayer(event.getPlayer());
		}
	}
}