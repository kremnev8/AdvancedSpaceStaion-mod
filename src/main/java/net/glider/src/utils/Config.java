package net.glider.src.utils;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

	Configuration config;
	
	public boolean InfoPointAcsessable;

	public Config() {
	}
	
	public static void init(String name) {
		Configuration cfg = new Configuration(new File("config/"+name+".cfg"));
		  try {
		   cfg.load();
		 //  rituals = cfg.get(Configuration.CATEGORY_GENERAL, "retuals enable", false, "if it true then you can transmutate items into blocks").getBoolean(true);

		  } catch (Exception e) {
		   GLoger.logWarn("Has a problem loading it's configuration",e);
		  } finally {
		   if (cfg != null && cfg.hasChanged())
		    cfg.save();
		  }
	}
}