package GrimBungee.commands;

import GrimBungee.AlertsManager;
import GrimBungee.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AlertsCommand implements CommandExecutor {
    public final Core plugin;

    public AlertsCommand(Core plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.hasPermission("tangerine.flags")) {

                String AlertsAll = this.plugin.getConfig().getString("alertsALL");
                AlertsAll = ChatColor.translateAlternateColorCodes('&', AlertsAll);
                String AlertsCurrent = this.plugin.getConfig().getString("alertsCURRENT");
                AlertsCurrent = ChatColor.translateAlternateColorCodes('&', AlertsCurrent);
                String AlertsDisabled = this.plugin.getConfig().getString("alertsDISABLED");
                AlertsDisabled = ChatColor.translateAlternateColorCodes('&', AlertsDisabled);

                if (AlertsManager.StafferState.get(player.getUniqueId()).equals("Disabled")) {
                    AlertsManager.StafferStateDisabled.remove(player);
                    AlertsManager.StafferState.put(player.getUniqueId(), "All");
                    AlertsManager.StafferStateAll.add(player);
                    AlertsManager.StafferStateCurrent.add(player);
                    player.sendMessage(AlertsAll);
                } else if (AlertsManager.StafferState.get(player.getUniqueId()).equals("All")) {
                    AlertsManager.StafferState.put(player.getUniqueId(), "Current");
                    AlertsManager.StafferStateCurrent.add(player);
                    player.sendMessage(AlertsCurrent);
                    AlertsManager.StafferStateAll.remove(player);
                } else if (AlertsManager.StafferState.get(player.getUniqueId()).equals("Current")) {
                    AlertsManager.StafferStateCurrent.remove(player);
                    AlertsManager.StafferState.put(player.getUniqueId(), "Disabled");
                    AlertsManager.StafferStateDisabled.add(player);
                    player.sendMessage(AlertsDisabled);
                }
            }
        }

        return true;
    }
}
