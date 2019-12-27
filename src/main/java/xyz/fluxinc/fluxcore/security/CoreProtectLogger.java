package xyz.fluxinc.fluxcore.security;


import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CoreProtectLogger {

    /**
     * Log a player breaking a block
     * @param player The player performing the action
     * @param block The block being broken
     */
    public static void logBlockBreak(Player player, Block block) {
        CoreProtectAPI api = getCoreProtectAPI();
        if (api != null) {
            api.logRemoval(player.getName(), block.getLocation(), block.getType(), block.getBlockData());
        }
    }

    /**
     * Log a player placing a block
     * @param player The player performing the action
     * @param block The block being placed
     */
    public static void logBlockPlace(Player player, Block block) {
        CoreProtectAPI api = getCoreProtectAPI();
        if (api != null) {
            api.logPlacement(player.getName(), block.getLocation(), block.getType(), block.getBlockData());
        }
    }


    private static CoreProtectAPI getCoreProtectAPI() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("CoreProtect");

        if (plugin instanceof CoreProtect) {
            CoreProtectAPI api = ((CoreProtect) plugin).getAPI();
            if (api.isEnabled() && api.APIVersion() >= 6) {
                return api;
            }
        }
        return null;
    }
}
