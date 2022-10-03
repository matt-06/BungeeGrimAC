package GrimBungee.events;

import GrimBungee.AlertsManager;
import GrimBungee.Core;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoinEvent implements Listener {
    public final Core plugin;

    public onJoinEvent(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {

        String onJoinMessage = this.plugin.getConfig().getString("onJoinMessage");
        onJoinMessage = ChatColor.translateAlternateColorCodes('&', onJoinMessage);
        Player player = event.getPlayer();
        if (player.hasPermission("grimbungee.flags")) {
            AlertsManager.StafferState.put(player.getUniqueId(), "All");
            AlertsManager.StafferStateAll.add(player);
            AlertsManager.StafferStateCurrent.add(player);
            player.sendMessage(onJoinMessage);
        }

    }
}
