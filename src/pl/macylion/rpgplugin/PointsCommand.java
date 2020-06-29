package pl.macylion.rpgplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PointsCommand implements CommandExecutor{
	private final Main plugin;
	
	public PointsCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			double points = (double) plugin.getConfig().get(player.getUniqueId() + ".points");
			sender.sendMessage(ChatColor.GREEN + "["+plugin.getName()+"]" + 
					ChatColor.WHITE + " You have " + points + " points");
		}
		else {
			sender.sendMessage("You are not a player!");
		}
		
		return true;
	}

}
