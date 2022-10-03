package GrimBungee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;

public class AlertsManager {
    public static Map<UUID, String> StafferState = new HashMap<>();
    public static ArrayList<Player> StafferStateAll = new ArrayList<>();
    public static ArrayList<Player> StafferStateCurrent = new ArrayList<>();
    public static ArrayList<Player> StafferStateDisabled = new ArrayList<>();

    public AlertsManager() {
    }
}
