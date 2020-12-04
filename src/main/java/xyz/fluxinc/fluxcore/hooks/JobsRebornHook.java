package xyz.fluxinc.fluxcore.hooks;

import com.gamingmesh.jobs.CMILib.CMIMaterial;
import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.actions.BlockActionInfo;
import com.gamingmesh.jobs.container.ActionType;
import com.gamingmesh.jobs.container.JobsPlayer;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;

import java.util.List;

public class JobsRebornHook {

    private static Jobs plugin;

    public static void registerJobs(Jobs jobs) {
        plugin = jobs;
    }

    public static void addExperienceForBlocks(List<BlockState> blocks, Player player) {
        if (plugin == null) return;
        for (BlockState blockState : blocks) {
            /* Copyright (C) 2011 Zak Ford <zak.j.ford@gmail.com> */
            Material brokenBlock = blockState.getBlock().getRelative(BlockFace.DOWN).getType();
            if (Jobs.getGCManager().preventCropResizePayment && (brokenBlock == CMIMaterial.SUGAR_CANE.getMaterial()
                    || brokenBlock == CMIMaterial.KELP.getMaterial()
                    || brokenBlock == CMIMaterial.CACTUS.getMaterial() || brokenBlock == CMIMaterial.BAMBOO.getMaterial())) {
                return;
            }
            /* END COPYRIGHT*/

            BlockActionInfo bInfo = new BlockActionInfo(blockState.getBlock(), ActionType.BREAK);
            Jobs.action(Jobs.getPlayerManager().getJobsPlayer(player), bInfo, blockState.getBlock());
        }
    }
}
