package pl.macylion.rpgplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new PlayerLoad(this), this);
		this.getServer().getPluginManager().registerEvents(new ChunkLoad(this), this);
		this.getServer().getPluginManager().registerEvents(new PointsEarning(this), this);
		this.getCommand("points").setExecutor(new PointsCommand(this));
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
