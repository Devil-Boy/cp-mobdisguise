package pgDev.bukkit.CPMD;

import me.desmin88.mobdisguise.api.MobDisguiseAPI;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class CPMDPlayerListener extends PlayerListener {
	CommandPointsMobDisguiseBridge plugin;
	
	public CPMDPlayerListener(CommandPointsMobDisguiseBridge pluginI) {
		plugin = pluginI;
	}
	
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (plugin.pluginSettings.undisguiseOnExit && MobDisguiseAPI.isDisguised(event.getPlayer())) {
			MobDisguiseAPI.undisguisePlayer(event.getPlayer());
		}
	}
	
}
