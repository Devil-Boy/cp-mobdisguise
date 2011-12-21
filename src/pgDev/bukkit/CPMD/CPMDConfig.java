package pgDev.bukkit.CPMD;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

public class CPMDConfig {
	private Properties properties;
	private final CommandPointsMobDisguiseBridge plugin;
	public boolean upToDate = true;
	
	// List of Config Options
	HashMap<String, Integer> disguiseCosts = new HashMap<String, Integer>();
	boolean undisguiseOnExit;
	
	public CPMDConfig(Properties p, CommandPointsMobDisguiseBridge plugin) {
        properties = p;
        this.plugin = plugin;
        
        // Grab values here.
        disguiseCosts.put("player", getInt("player", 1));
        disguiseCosts.put("creeper", getInt("creeper", 1));
        disguiseCosts.put("skeleton", getInt("skeleton", 1));
        disguiseCosts.put("spider", getInt("spider", 1));
        disguiseCosts.put("giant", getInt("giant", 1));
        disguiseCosts.put("zombie", getInt("zombie", 1));
        disguiseCosts.put("slime", getInt("slime", 1));
        disguiseCosts.put("ghast", getInt("ghast", 1));
        disguiseCosts.put("pigman", getInt("pigman", 1));
        disguiseCosts.put("enderman", getInt("enderman", 1));
        disguiseCosts.put("cavespider", getInt("cavespider", 1));
        disguiseCosts.put("silverfish", getInt("silverfish", 1));
        disguiseCosts.put("blaze", getInt("blaze", 1));
        disguiseCosts.put("magmacube", getInt("magmacube", 1));
        disguiseCosts.put("enderdragon", getInt("enderdragon", 1));
        disguiseCosts.put("pig", getInt("pig", 1));
        disguiseCosts.put("sheep", getInt("sheep", 1));
        disguiseCosts.put("cow", getInt("cow", 1));
        disguiseCosts.put("chicken", getInt("chicken", 1));
        disguiseCosts.put("squid", getInt("squid", 1));
        disguiseCosts.put("wolf", getInt("wolf", 1));
        disguiseCosts.put("mooshroom", getInt("mooshroom", 1));
        disguiseCosts.put("snowgolem", getInt("snowgolem", 1));
        disguiseCosts.put("villager", getInt("villager", 1));
        undisguiseOnExit = getBoolean("undisguiseOnExit", true);
        
	}
	
	// Value obtaining functions down below
	public int getInt(String label, int thedefault) {
		String value;
        try {
        	value = getString(label);
        	return Integer.parseInt(value);
        } catch (NoSuchElementException e) {
        	return thedefault;
        }
    }
    
    public double getDouble(String label) throws NoSuchElementException {
        String value = getString(label);
        return Double.parseDouble(value);
    }
    
    public File getFile(String label) throws NoSuchElementException {
        String value = getString(label);
        return new File(value);
    }

    public boolean getBoolean(String label, boolean thedefault) {
    	String values;
        try {
        	values = getString(label);
        	return Boolean.valueOf(values).booleanValue();
        } catch (NoSuchElementException e) {
        	return thedefault;
        }
    }
    
    public Color getColor(String label) {
        String value = getString(label);
        Color color = Color.decode(value);
        return color;
    }
    
    public HashSet<String> getSet(String label, String thedefault) {
        String values;
        try {
        	values = getString(label);
        } catch (NoSuchElementException e) {
        	values = thedefault;
        }
        String[] tokens = values.split(",");
        HashSet<String> set = new HashSet<String>();
        for (int i = 0; i < tokens.length; i++) {
            set.add(tokens[i].trim().toLowerCase());
        }
        return set;
    }
    
    public LinkedList<String> getList(String label, String thedefault) {
    	String values;
        try {
        	values = getString(label);
        } catch (NoSuchElementException e) {
        	values = thedefault;
        }
        if(!values.equals("")) {
            String[] tokens = values.split(",");
            LinkedList<String> set = new LinkedList<String>();
            for (int i = 0; i < tokens.length; i++) {
                set.add(tokens[i].trim().toLowerCase());
            }
            return set;
        }else {
        	return new LinkedList<String>();
        }
    }
    
    public String getString(String label) throws NoSuchElementException {
        String value = properties.getProperty(label);
        if (value == null) {
        	upToDate = false;
            throw new NoSuchElementException("Config did not contain: " + label);
        }
        return value;
    }
    
    public String getString(String label, String thedefault) {
    	String value;
    	try {
        	value = getString(label);
        } catch (NoSuchElementException e) {
        	value = thedefault;
        }
        return value;
    }
    
    public String linkedListToString(LinkedList<String> list) {
    	if(list.size() > 0) {
    		String compounded = "";
    		boolean first = true;
        	for (String value : list) {
        		if (first) {
        			compounded = value;
        			first = false;
        		} else {
        			compounded = compounded + "," + value;
        		}
        	}
        	return compounded;
    	}
    	return "";
    }
    
    
    // Config creation method
    public void createConfig() {
    	try{
    		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(plugin.pluginConfigLocation)));
    		out.write("#\r\n");
    		out.write("# CP-MD Bridge Configuration\r\n");
    		out.write("#\r\n");
    		out.write("\r\n");
    		out.write("# Disguise costs\r\n");
    		out.write("#	Here you set the amount of\r\n");
    		out.write("#	points needed to disguise\r\n");
    		out.write("#	 as each mob.\r\n");
    		out.write("creeper=" + disguiseCosts.get("creeper") + "\r\n");
    		out.write("skeleton=" + disguiseCosts.get("skeleton") + "\r\n");
    		out.write("spider=" + disguiseCosts.get("spider") + "\r\n");
    		out.write("giant=" + disguiseCosts.get("giant") + "\r\n");
    		out.write("zombie=" + disguiseCosts.get("zombie") + "\r\n");
    		out.write("slime=" + disguiseCosts.get("slime") + "\r\n");
    		out.write("ghast=" + disguiseCosts.get("ghast") + "\r\n");
    		out.write("pigman=" + disguiseCosts.get("pigman") + "\r\n");
    		out.write("enderman=" + disguiseCosts.get("enderman") + "\r\n");
    		out.write("cavespider=" + disguiseCosts.get("cavespider") + "\r\n");
    		out.write("silverfish=" + disguiseCosts.get("silverfish") + "\r\n");
    		out.write("blaze=" + disguiseCosts.get("blaze") + "\r\n");
    		out.write("magmacube=" + disguiseCosts.get("magmacube") + "\r\n");
    		out.write("enderdragon=" + disguiseCosts.get("enderdragon") + "\r\n");
    		out.write("pig=" + disguiseCosts.get("pig") + "\r\n");
    		out.write("sheep=" + disguiseCosts.get("sheep") + "\r\n");
    		out.write("cow=" + disguiseCosts.get("cow") + "\r\n");
    		out.write("chicken=" + disguiseCosts.get("chicken") + "\r\n");
    		out.write("squid=" + disguiseCosts.get("squid") + "\r\n");
    		out.write("wolf=" + disguiseCosts.get("wolf") + "\r\n");
    		out.write("mooshroom=" + disguiseCosts.get("mooshroom") + "\r\n");
    		out.write("snowgolem=" + disguiseCosts.get("snowgolem") + "\r\n");
    		out.write("villager=" + disguiseCosts.get("villager") + "\r\n");
    		out.write("\r\n");
    		out.write("# Player disguise cost\r\n");
    		out.write("#	If you don't want players to\r\n");
    		out.write("#	disguise as each other, edit\r\n");
    		out.write("#	the permissions in MobDisguise.\r\n");
    		out.write("player=" + disguiseCosts.get("player") + "\r\n");
    		out.write("\r\n");
    		out.write("# Undisguise on exit\r\n");
    		out.write("#	Here you decide whether or not\r\n");
    		out.write("#	players are undisguised when they\r\n");
    		out.write("#	leave the server.\r\n");
    		out.write("undisguiseOnExit=" + undisguiseOnExit + "\r\n");
    		out.close();
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    }
}
