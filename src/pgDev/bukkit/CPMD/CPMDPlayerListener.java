package pgDev.bukkit.CPMD;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Handle events for all Player related events
 * @author pgDev
 */
public class CPMDPlayerListener extends PlayerListener {
    private final CommandPointsMobDisguiseBridge plugin;

    public CPMDPlayerListener(CommandPointsMobDisguiseBridge instance) {
        plugin = instance;
    }

    //Insert Player related code here
}

