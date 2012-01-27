package pgDev.bukkit.CPMD;

import me.desmin88.mobdisguise.api.MobDisguiseAPI;

import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class CPMDPlayerListener implements Listener {
	CommandPointsMobDisguiseBridge plugin;
	
	public CPMDPlayerListener(CommandPointsMobDisguiseBridge pluginI) {
		plugin = pluginI;
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (plugin.pluginSettings.undisguiseOnExit && MobDisguiseAPI.isDisguised(event.getPlayer())) {
			MobDisguiseAPI.undisguisePlayer(event.getPlayer());
		}
	}
	
}
