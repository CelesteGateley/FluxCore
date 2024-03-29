package xyz.fluxinc.fluxcore.hooks;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.actions.BlockActionInfo;
import com.gamingmesh.jobs.container.ActionType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;

import java.util.List;

public class JobsRebornHook {

    private static Jobs plugin;

    /**
     * Registers the Jobs Plugin
     * @param jobs The instance of the jobs plugin
     */
    public static void registerJobs(Jobs jobs) {
        plugin = jobs;
    }

    /**
     * Check if Jobs is registered as a hook
     * @return The status of JobsReborn
     */
    public static boolean isJobsRegistered() { return plugin != null; }

    /**
     * Add jobs experience and money for block states
     * @param blocks a list of block states
     * @param player player to adjust
     */
    public static void addExperienceForBlocks(List<BlockState> blocks, Player player) {
        if (plugin == null) return;
        for (BlockState blockState : blocks) {
            BlockActionInfo bInfo = new BlockActionInfo(blockState.getBlock(), ActionType.BREAK);
            Jobs.action(Jobs.getPlayerManager().getJobsPlayer(player), bInfo, blockState.getBlock());
        }
    }

    /**
     * Add jobs experience for a single placed block
     * @param block The block being placed
     * @param player The player placing the block
     */
    public static void addExperienceForBlockPlace(Block block, Player player) {
        if (plugin == null) return;

        Jobs.action(Jobs.getPlayerManager().getJobsPlayer(player), new BlockActionInfo(block, ActionType.PLACE), block);
    }

    /**
     * Add jobs experience for a single broken block
     * @param block The block being broken
     * @param player The player placing the block
     */
    public static void addExperienceForBlockBreak(Block block, Player player) {
        if (plugin == null) { return; }

        Jobs.action(Jobs.getPlayerManager().getJobsPlayer(player), new BlockActionInfo(block, ActionType.BREAK), block);
    }
}
