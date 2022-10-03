package GrimBungee.events;

import GrimBungee.AlertsManager;
import GrimBungee.Core;
import ac.grim.grimac.events.FlagEvent;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class onFlagEvent implements Listener {
    public final Core plugin;

    public onFlagEvent(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFlagEvent(FlagEvent event) {
        double maxFlags = plugin.getConfig().getDouble("MaxViolations");
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        String server_name = plugin.getConfig().getString("server_name");
        String flag = plugin.getConfig().getString("alert")
                .replaceAll("%FlaggedPlayer%", event.getPlayer().getName())
                .replaceAll("%Flag%", event.getCheck().getCheckName())
                .replaceAll("%FlagCheck%", String.valueOf(event.getViolations()))
                .replaceAll("%Server%", server_name)
                .replaceAll("%Version%", event.getPlayer().getVersionName())
                .replaceAll("%Ping%", String.valueOf(event.getPlayer().getTransactionPing()));
        flag = ChatColor.translateAlternateColorCodes('&', flag);
        out.writeUTF("Forward");
        out.writeUTF("ALL");
        out.writeUTF("FlagsListener");
        double flags = event.getViolations() % maxFlags;
        boolean messageSend = false;
        if (flags == 0.0 && !messageSend) {
            out.writeUTF(flag);
            Player p = (Player)Iterables.getFirst(Bukkit.getOnlinePlayers(), (Object)null);
            p.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());

            for(Player staffer : AlertsManager.StafferStateCurrent){
                staffer.sendMessage(flag);
                if (AlertsManager.StafferStateDisabled.contains(staffer)) {
                    return;
                }
            }

            messageSend = true;
        }

    }
}
