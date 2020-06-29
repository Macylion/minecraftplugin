package pl.macylion.rpgplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerLoad implements Listener {
	private final Main plugin;
	
	public PlayerLoad(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		this.loadData(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		e.getPlayer().setHealth((double) plugin.getConfig().get(e.getPlayer().getUniqueId() + ".hp"));
	}
	
	private void loadData(Player player) {
		JavaPlugin plugin = Main.getPlugin(Main.class);
		//Points
		if(plugin.getConfig().get(player.getUniqueId() + ".points") == null)
			plugin.getConfig().set(player.getUniqueId() + ".points", 0.0);
		//Max Health Points
		if(plugin.getConfig().get(player.getUniqueId() + ".hp") == null)
				plugin.getConfig().set(player.getUniqueId() + ".hp" ,
						(double) plugin.getConfig().get("defaultHP"));
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + player.getName() + " minecraft:generic.max_health base set "
				+ (double) plugin.getConfig().get(player.getUniqueId() + ".hp"));
		//AttackDamage
		if(plugin.getConfig().get(player.getUniqueId() + ".ad") == null)
			plugin.getConfig().set(player.getUniqueId() + ".ad" ,
				(double) plugin.getConfig().get("defaultAD"));
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + player.getName() + " minecraft:generic.attack_damage base set "
				+ (double) plugin.getConfig().get(player.getUniqueId() + ".ad"));
		//AttackSpeed
		if(plugin.getConfig().get(player.getUniqueId() + ".as") == null)
			plugin.getConfig().set(player.getUniqueId() + ".as" ,
				(double) plugin.getConfig().get("defaultAS"));
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + player.getName() + " minecraft:generic.attack_speed base set "
			+ (double) plugin.getConfig().get(player.getUniqueId() + ".as"));
		//KnockbackRes
		if(plugin.getConfig().get(player.getUniqueId() + ".kr") == null)
			plugin.getConfig().set(player.getUniqueId() + ".kr" ,
				(double) plugin.getConfig().get("defaultKR"));
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + player.getName() + " minecraft:generic.knockback_resistance base set "
				+ (double) plugin.getConfig().get(player.getUniqueId() + ".kr"));
		//MovementSpeed
		if(plugin.getConfig().get(player.getUniqueId() + ".ms") == null)
			plugin.getConfig().set(player.getUniqueId() + ".ms" ,
				(double) plugin.getConfig().get("defaultMS"));
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + player.getName() + " minecraft:generic.movement_speed base set "
				+ (double) plugin.getConfig().get(player.getUniqueId() + ".ms"));
		plugin.saveConfig();
		
		try {
			if(!player.isDead())
				player.setHealth(player.getHealth());
		} catch (IllegalArgumentException e) {
			player.setHealth((double) plugin.getConfig().get(player.getUniqueId() + ".hp"));
		}
			
	}
}
