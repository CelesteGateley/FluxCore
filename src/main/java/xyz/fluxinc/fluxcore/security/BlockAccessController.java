package xyz.fluxinc.fluxcore.security;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;


public class BlockAccessController {

    private WorldGuardPlugin worldGuardCompat;
    private GriefPrevention griefPreventionCompat;

    /**
     * Generates a new Block Access Controller
     * Support for plugins must be manually registered individually
     */
    public BlockAccessController() { this.worldGuardCompat = null; this.griefPreventionCompat = null; }

    /**
     * Registers the current instance of GriefPrevention
     * @param griefPreventionInstance The current instance of GriefPrevention
     */
    public void registerGriefPrevention(GriefPrevention griefPreventionInstance) { this.griefPreventionCompat = griefPreventionInstance; }

    /**
     * Registers the current instance of WorldGuard
     * @param worldGuardInstance the current instance of WorldGuard
     */
    public void registerWorldGuard(WorldGuardPlugin worldGuardInstance) { this.worldGuardCompat = worldGuardInstance; }

    /**
     * @return Whether GriefPrevention is registered or not
     */
    public boolean isGriefPreventionRegistered() { return griefPreventionCompat != null; }

    /**
     * @return Whether WorldGuard is registered or not
     */
    public boolean isWorldGuardRegistered() { return worldGuardCompat != null; }

    /**
     * Checks whether a player can break or place to a specific block or not
     * @param player The player trying to access the block
     * @param location The location of the block
     * @param build Whether the player is trying to break or place
     * @return whether the player can build at the location
     */
    public boolean checkBreakPlace(Player player, Location location, boolean build) {
        boolean access = true;
        if (isWorldGuardRegistered()) { access = worldGuardBlockQuery(player, location, build); }
        if (isGriefPreventionRegistered()) { access = access && griefPreventionBuildQuery(player, location); }
        return access;
    }

    /**
     * Checks whether a player can access blocks and containers in a specific area
     * @param player The player trying to access the block
     * @param location The location of the block
     * @return Whether the player can access the block
     */
    public boolean checkBlockAccess(Player player, Location location) {
        boolean access = true;
        if (isWorldGuardRegistered()) { access = worldGuardAccessQuery(player, location); }
        if (isGriefPreventionRegistered()) { access = access && griefPreventionAccessQuery(player, location); }
        return access;
    }

    private boolean worldGuardBlockQuery(Player p, Location loc, boolean breakBlock) {
        WorldGuardDataInstance wgdInst = new WorldGuardDataInstance(p, loc);
        RegionQuery rQuery = wgdInst.getRegionQuery();

        boolean bypass = WorldGuard.getInstance().getPlatform().getSessionManager().hasBypass(wgdInst.getPlayer(), wgdInst.getPlayer().getWorld());
        if (bypass) { return true; }
        if (breakBlock) { return rQuery.testBuild(wgdInst.getLocation(), wgdInst.getPlayer(), Flags.BLOCK_BREAK); }
        else { return rQuery.testBuild(wgdInst.getLocation(), wgdInst.getPlayer(), Flags.BLOCK_PLACE); }
    }

    private boolean worldGuardAccessQuery(Player p, Location loc) {
        WorldGuardDataInstance wgdInst = new WorldGuardDataInstance(p, loc);
        RegionQuery rQuery = wgdInst.getRegionQuery();

        boolean bypass = WorldGuard.getInstance().getPlatform().getSessionManager().hasBypass(wgdInst.getPlayer(), wgdInst.getPlayer().getWorld());
        if (bypass) { return true; }
        return rQuery.testState(wgdInst.getLocation(), wgdInst.getPlayer(), Flags.USE);
    }

    private boolean griefPreventionBuildQuery(Player p, Location loc) {
        Claim claim = griefPreventionCompat.dataStore.getClaimAt(loc, true, null);
        return claim == null || claim.allowBuild(p, loc.getBlock().getType()) == null;
    }

    private boolean griefPreventionAccessQuery(Player p, Location loc) {
        Claim claim = griefPreventionCompat.dataStore.getClaimAt(loc, true, null);
        return claim == null || claim.allowAccess(p) == null;
    }

    /**
     * Private Class used for Refactoring out WorldGuard Query information
     */
    private class WorldGuardDataInstance {

        LocalPlayer worldGuardPlayer;
        com.sk89q.worldedit.util.Location worldGuardLocation;

        WorldGuardDataInstance(Player player, Location location) {
            this.worldGuardPlayer = worldGuardCompat.wrapPlayer(player);
            this.worldGuardLocation = BukkitAdapter.adapt(location);
        }

        LocalPlayer getPlayer() { return worldGuardPlayer; }
        com.sk89q.worldedit.util.Location getLocation() { return worldGuardLocation; }
        RegionQuery getRegionQuery() { return WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery(); }
    }
}

