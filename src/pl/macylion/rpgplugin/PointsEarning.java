package pl.macylion.rpgplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class PointsEarning implements Listener {
	private final Main plugin;
	
	public PointsEarning(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		if(e.getEntity().getKiller() instanceof Player && e.getEntity() instanceof Monster) {
			Player player = (Player) e.getEntity().getKiller();
			this.addPoints(player, (double) plugin.getConfig().get("pointsMonsterKill"));
		}
	}
	
	@EventHandler
	public void onPlayerLevelChange(PlayerLevelChangeEvent e) {
		if(e.getNewLevel() > (double) plugin.getConfig().get("pointLevelMin") && e.getNewLevel() > e.getOldLevel())
			this.addPoints(e.getPlayer(), (double) plugin.getConfig().get("pointsLevelUP"));
	}
	
	private void addPoints(Player player, double points) {
		plugin.getConfig().set(player.getUniqueId() + ".points",
			(double) plugin.getConfig().get(player.getUniqueId() + ".points")
			+ points);
		player.sendMessage(ChatColor.GREEN + "["+plugin.getName()+"]" + 
			ChatColor.WHITE + " You have earned " + points + " point" + ((points == 1)?"!":"s!"));
		plugin.saveConfig();
	}
}
