package xyz.fluxinc.fluxcore.security;


import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CoreProtectLogger {

    private CoreProtect coreProtectInstance = null;
    private CoreProtectAPI apiInstance = null;

    /**
     * Initialize the CoreProtectLogger
     * @param instance The expected instance of CoreProtect
     */
    public CoreProtectLogger(Plugin instance) {
        if (instance instanceof CoreProtect) {
            coreProtectInstance = (CoreProtect) instance;
            apiInstance = coreProtectInstance.getAPI();
            if (!apiInstance.isEnabled() || apiInstance.APIVersion() < 6) { coreProtectInstance = null; apiInstance = null; }
        }
    }

    /**
     * Log a player breaking a block
     * @param player The player performing the action
     * @param block The block being broken
     */
    public void logBlockBreak(Player player, Block block) {
        if (isCoreProtectDisabled()) { return; }
        apiInstance.logRemoval(player.getName(), block.getLocation(), block.getType(), block.getBlockData());
    }


    /**
     * Log a player placing a block
     * @param player The player performing the action
     * @param block The block being placed
     */
    public void logBlockPlace(Player player, Block block) {
        if (isCoreProtectDisabled()) { return; }
        apiInstance.logPlacement(player.getName(), block.getLocation(), block.getType(), block.getBlockData());
    }

    private boolean isCoreProtectDisabled() {
        return coreProtectInstance == null
                || apiInstance == null;
    }
}
