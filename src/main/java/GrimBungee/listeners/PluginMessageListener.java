package GrimBungee.listeners;

import GrimBungee.AlertsManager;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;

public class PluginMessageListener implements org.bukkit.plugin.messaging.PluginMessageListener {
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

        if (channel.equalsIgnoreCase("BungeeCord")) {

            ByteArrayDataInput in = ByteStreams.newDataInput(message);
            String subchannel = in.readUTF();

            if (subchannel.equals("FlagsListener")) {

                String flag = in.readUTF();
                for(Player staffer : AlertsManager.StafferStateAll) {
                    staffer.sendMessage(flag);
                }

            }
        }
    }
}
