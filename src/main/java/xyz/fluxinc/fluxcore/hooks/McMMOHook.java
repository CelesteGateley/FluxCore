package xyz.fluxinc.fluxcore.hooks;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;


public class McMMOHook {

    private static mcMMO plugin;

    /**
     * Registers the mcMMO Plugin
     * @param mcMMO the instance of McMMO
     */
    public static void registerMcMMO(Plugin mcMMO) {
        plugin = (com.gmail.nossr50.mcMMO) mcMMO;
    }

    /**
     * Add experience for a list of blocks
     * @param blocks List of blocks to be processed
     * @param player Player to gain experience
     */
    public static void addBlockExperience(ArrayList<BlockState> blocks, Player player) {
        if (plugin != null) {
            ExperienceAPI.addXpFromBlocks(blocks, UserManager.getPlayer(player));
        }
    }

    /**
     * Add experience for a single block
     * @param block Block to be processed
     * @param player Player to gain experience
     */
    public static void addBlockExperience(BlockState block, Player player) {
        if (plugin != null) {
            ExperienceAPI.addXpFromBlock(block, UserManager.getPlayer(player));
        }
    }
}
