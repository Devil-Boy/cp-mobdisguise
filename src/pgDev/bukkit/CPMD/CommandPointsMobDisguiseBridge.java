package pgDev.bukkit.CPMD;

import java.io.File;
import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.Server;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

/**
 * CommandPointsMobDisguiseBridge for Bukkit
 *
 * @author pgDev
 */
public class CommandPointsMobDisguiseBridge extends JavaPlugin {
    private final CPMDPlayerListener playerListener = new CPMDPlayerListener(this);
    private final MDEventListener mdEventListener = new MDEventListener(this);

    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.CUSTOM_EVENT, mdEventListener, Priority.Normal, this);

        // "All is well!" Output
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }
    public void onDisable() {
        // "We done well..." Output
        System.out.println("CommandPointsMobDisguiseBridge disabled!");
    }
}

