package xyz.fluxinc.fluxcore.structure;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class MultiblockExecutor {

    public abstract void onInteract(Player player, CoordinatePair coordinatePair);

    public abstract boolean canDestroy(Location Location);

}
