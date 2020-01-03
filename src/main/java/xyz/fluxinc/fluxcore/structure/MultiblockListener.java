package xyz.fluxinc.fluxcore.structure;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class MultiblockListener implements Listener {

    private MultiblockStructure structure;
    private MultiblockExecutor executor;

    public MultiblockListener(MultiblockStructure structure, MultiblockExecutor executor) {
        this.structure = structure;
        this.executor = executor;
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) { return; }
        if (e.getPlayer().isSneaking()) { return; }
        if (structure.isBoxMaterial(e.getClickedBlock().getType())) { return; }
        CoordinatePair blockLocation = structure.getCoordinatePair(e.getClickedBlock().getLocation());
        if (blockLocation != null) {
            executor.onInteract(e.getPlayer(), blockLocation);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        e.setCancelled(!executor.canDestroy(e.getBlock().getLocation()));
    }

    @EventHandler
    public void onPistonMove(BlockPistonExtendEvent e) {
        e.setCancelled(!executor.canDestroy(e.getBlock().getLocation()));
    }

    @EventHandler
    public void onPistonMove(BlockPistonRetractEvent e) {
        e.setCancelled(!executor.canDestroy(e.getBlock().getLocation()));
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e) {
        e.setCancelled(!executor.canDestroy(e.getBlock().getLocation()));
    }

}
