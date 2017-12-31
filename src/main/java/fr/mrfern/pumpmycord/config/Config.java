package fr.mrfern.pumpmycord.config;

import java.io.File;

import fr.mrfern.pumpmycord.Main;

public class Config {

	private static Config config = new Config();
	private static Main main;
	
	public static Config getConfig(Main m) {
		main = m;
		return config;
	}

	public void initFile(String path) {
		
		File file = new File(path);
		
	}
	
}