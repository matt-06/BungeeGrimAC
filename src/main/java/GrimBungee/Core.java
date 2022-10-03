package GrimBungee;

import GrimBungee.commands.AlertsCommand;
import GrimBungee.events.onFlagEvent;
import GrimBungee.events.onJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
    public void onEnable() {
        registerEvents();
        registerCommands();
        createConfig();

        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new GrimBungee.listeners.PluginMessageListener());
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getLogger().info("Plugin enabled");
    }

    public void onDisable() {
        getServer().getMessenger().unregisterOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().unregisterIncomingPluginChannel(this, "BungeeCord");
    }

    void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new onFlagEvent(this), this);
        pm.registerEvents(new onJoinEvent(this), this);
    }

    void registerCommands(){
        getCommand("alerts").setExecutor(new AlertsCommand(this));
    }

    void createConfig(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
}
