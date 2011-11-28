package pgDev.bukkit.CPMD;

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
	}
	
	public void onDisguiseAsPlayer(DisguiseAsPlayerEvent event) {
	}
	
	public void onUnDisguise(UnDisguiseEvent event) {
	}
	
	public void onMobDisguiseCommand(DisguiseCommandEvent event) {
	}
	
}
