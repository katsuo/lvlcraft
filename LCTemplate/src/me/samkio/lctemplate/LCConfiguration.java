package me.samkio.lctemplate;


import java.io.IOException;
import java.util.logging.Level;
import me.samkio.levelcraftcore.util.Properties;
/* This class sets up the configuration for the variables.
 * It also sets up the configuration file with the variables.
 * Important to note it imports the properties functions from LevelCraftCore
 */

public class LCConfiguration {
	public LCMining plugin;
	//TOOLS
	public int Tool1;
	public int Tool2;
	//EXP PER
	public double ExpPerAction1;
	public double ExpPerAction2;

	
	public LCConfiguration(LCMining instance) {
		plugin = instance;
	}
    //loadConfig. Called onEnable in main class.
	public void loadConfig() {
        //Create new properties file with the file string.
		Properties properties = new Properties(plugin.ConfigurationFileString);
		try {
			//Try to load it. If not return an error.
			properties.load();
		} catch (IOException e) {
			plugin.logger.log(Level.SEVERE,"[LC] "+e);
		}
		//Set variables for TOOLS
		this.Tool1 = properties.getInteger("Tool1", 0);
		this.Tool2 = properties.getInteger("Tool2", 5);
		
		//Set variables for EXP PER
		this.ExpPerAction1 = properties.getDouble("ExpPerA1", 5);
		this.ExpPerAction2 = properties.getDouble("ExpPerA2", 5);
	

	}
}
