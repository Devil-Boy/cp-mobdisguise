package pgDev.bukkit.CPMD;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

import pgDev.bukkit.DisguiseCraft.*;
import pgDev.bukkit.DisguiseCraft.api.DisguiseCraftAPI;
import pgDev.bukkit.CommandPoints.*;


/**
 * CommandPointsMobDisguiseBridge for Bukkit
 *
 * @author pgDev
 */
public class CommandPointsMobDisguiseBridge extends JavaPlugin {
	// Listeners
    private final MDEventListener mdEventListener = new MDEventListener(this);
    private final DCEventListener dcEventListener = new DCEventListener(this);
    
    // File Locations
    String pluginMainDir = "./plugins/CommandPointsMobDisguiseBridge";
    String pluginConfigLocation = pluginMainDir + "/CPMD.cfg";
    
    // Settings
    CPMDConfig pluginSettings;
    
    // Types of Mobs
    //String mobTypes = "creeper,skeleton,spider,giant,zombie,slime,pigman,pig,sheep,cow,chicken,squid,wolf,enderman,cavespider,silverfish,villager,snowgolem,mooshroom,blaze,magmacube,enderdragon";
    //LinkedList<String> mobTypeList = new LinkedList<String>();
    
    // CommandPoints API
    CommandPointsAPI cpAPI;
    
    // DisguiseCraft API
    DisguiseCraftAPI dcAPI = null;
    
    // Permissions Integration
    private static PermissionHandler Permissions;
    
    public void onEnable() {
    	// Check for the plugin directory (create if it does not exist)
    	File pluginDir = new File(pluginMainDir);
		if(!pluginDir.exists()) {
			boolean dirCreation = pluginDir.mkdirs();
			if (dirCreation) {
				System.out.println("New CommandPointsMobDisguiseBridge directory created!");
			}
			
		}
		
    	// Load the Configuration
    	try {
        	Properties preSettings = new Properties();
        	if ((new File(pluginConfigLocation)).exists()) {
        		preSettings.load(new FileInputStream(new File(pluginConfigLocation)));
        		pluginSettings = new CPMDConfig(preSettings, this);
        		if (!pluginSettings.upToDate) {
        			pluginSettings.createConfig();
        			System.out.println("CommandPointsMobDisguiseBridge Configuration updated!");
        		}
        	} else {
        		pluginSettings = new CPMDConfig(preSettings, this);
        		pluginSettings.createConfig();
        		System.out.println("CommandPointsMobDisguiseBridge Configuration created!");
        	}
        } catch (Exception e) {
        	System.out.println("Could not load CommandPointsMobDisguiseBridge configuration! " + e);
        }
    	
    	// Fill up the mob list
    	/* Not necessary
    	for (String type: mobTypes.split(",")) {
    		mobTypeList.add(type);
    	}*/
        
        // Integrations turn on!
        checkCPandMD();
    	setupPermissions();
    	
    	if (this.isEnabled()) {
    		// Register events
            PluginManager pm = getServer().getPluginManager();
            if (dcAPI == null) {
            	pm.registerEvents(mdEventListener, this);
            } else {
            	pm.registerEvents(dcEventListener, this);
            }
        	
            // "All is well!" Output
            PluginDescriptionFile pdfFile = this.getDescription();
            System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    	} else {
    		System.out.println("CommandPointsMobDisguiseBridge could not be enabled!");
    	}
    }
    public void onDisable() {
        // "We done well..." Output
        System.out.println("CommandPointsMobDisguiseBridge disabled!");
    }
    
    @SuppressWarnings("static-access")
	private void checkCPandMD() {
        Plugin commandPoints = getServer().getPluginManager().getPlugin("CommandPoints");
        Plugin mobDisguise = getServer().getPluginManager().getPlugin("MobDisguise");
        Plugin disguiseCraft = getServer().getPluginManager().getPlugin("DisguiseCraft");
        
        if (commandPoints != null) {
            cpAPI = ((CommandPoints)commandPoints).getAPI();
        } else {
        	System.out.println("CommandPoints was not found on this server!");
        	getServer().getPluginManager().disablePlugin(this);
        }
        
        if (disguiseCraft != null) {
        	dcAPI = ((DisguiseCraft) disguiseCraft).getAPI();
        } else if (mobDisguise == null) {
        	System.out.println("Neither MobDisguise nor DisguiseCraft was found on this server!");
        	getServer().getPluginManager().disablePlugin(this);
        }
    }
    
    // Permissions Methods
    private void setupPermissions() {
        Plugin permissions = this.getServer().getPluginManager().getPlugin("Permissions");

        if (Permissions == null) {
            if (permissions != null) {
                Permissions = ((Permissions)permissions).getHandler();
            } else {
            }
        }
    }
    
    protected boolean hasPermissions(Player player, String node) {
        if (Permissions != null) {
        	return Permissions.has(player, node);
        } else {
            return player.hasPermission(node);
        }
    }
    

}

