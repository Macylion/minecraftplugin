package com.github.macylion;

import java.io.File;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.potion.PotionEffect;

public class Plugin extends JavaPlugin {
	
	public class CommandKit implements CommandExecutor {
	    @SuppressWarnings("deprecation")
		@Override
	    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    	if (sender instanceof Player) {
	            Player player = (Player) sender;
	            player.sendMessage(command.getLabel());
	            for(int i = 0; i <= 10; i++) {
	            	for(int j = 0; j <= 10; j++) {
	            		Location loc = player.getLocation();
	            		loc.setX(loc.getX() + (i*7) - 40);
	            		loc.setZ(loc.getZ() + (j*7) - 40);
	            		player.getWorld().createExplosion(loc, 4);
	            		loc.setY(loc.getY() + 48);
	            		player.getWorld().spawnEntity(loc, EntityType.SQUID);
	            	}
	            }
	            for(int i = 0; i <= 10; i++) {
	            	for(int j = 0; j <= 10; j++) {
	            		Location loc = player.getLocation();
	            		loc.setX(loc.getX() + i - 5);
	            		loc.setZ(loc.getZ() + j - 5);
	            		loc.setY(loc.getY() + 32);
	    	            player.getWorld().spawnFallingBlock(loc, Material.CACTUS, (byte) 0);
	            	}
	            }
	            
	            Location loc = player.getLocation();
	            loc.setY(loc.getY() + 4);
	            Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
	            FireworkMeta fwm = fw.getFireworkMeta();
	           
	            fwm.setPower(4);
	            fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());
	            fwm.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(true).build());
	           
	            fw.setFireworkMeta(fwm);
	            fw.detonate();
	            
	        }
	        return true;
	    }
	}
	
	public Plugin() {
		// TODO Auto-generated constructor stub
	}

	public Plugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
		super(loader, description, dataFolder, file);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onEnable() {
		this.getLogger().info("test");
		this.getCommand("test").setExecutor(new CommandKit());
	}

}
