package xyz.fluxinc.fluxcore.Security;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Location;
import org.bukkit.entity.Player;


public class BlockAccessController {

    private WorldGuardPlugin worldGuardCompat;
    private GriefPrevention griefPreventionCompat;

    public BlockAccessController(WorldGuard wgCompatInstance, GriefPrevention gpCompatInstance) { if (wgCompatInstance != null) { this.worldGuardCompat = WorldGuardPlugin.inst(); } this.griefPreventionCompat = gpCompatInstance; }
    public BlockAccessController(GriefPrevention gpCompatInstance) { this.worldGuardCompat = null; this.griefPreventionCompat = gpCompatInstance; }
    public BlockAccessController(WorldGuard wgCompatInstance) { if (wgCompatInstance != null) { this.worldGuardCompat = WorldGuardPlugin.inst(); } this.griefPreventionCompat = null; }
    public BlockAccessController() { this.worldGuardCompat = null; this.griefPreventionCompat = null; }

    public void registerGriefPrevention(GriefPrevention gpCompatInstance) { this.griefPreventionCompat = gpCompatInstance; }
    public void registerWorldGuard(WorldGuard wgCompatInstance) { if (wgCompatInstance != null) { this.worldGuardCompat = WorldGuardPlugin.inst(); }}

    public boolean isWorldGuardRegistered() { return worldGuardCompat != null; }
    public boolean isGriefPreventionRegistered() { return griefPreventionCompat != null; }

    public boolean checkAccess(Player player, Location location) {
        boolean access = true;
        if (isWorldGuardRegistered()) { access = worldGuardQuery(player, location); }
        if (isGriefPreventionRegistered()) { access = access && griefPreventionQuery(player, location); }
        return access;
    }

    private boolean worldGuardQuery(Player p, Location loc) {
        LocalPlayer lPlayer = worldGuardCompat.wrapPlayer(p);
        com.sk89q.worldedit.util.Location wLoc = BukkitAdapter.adapt(loc);
        RegionContainer cont = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = cont.createQuery();

        boolean bypass = WorldGuard.getInstance().getPlatform().getSessionManager().hasBypass(lPlayer, lPlayer.getWorld());
        boolean buildQuery = query.testState(wLoc, lPlayer, Flags.BUILD);

        return bypass || buildQuery;
    }

    private boolean griefPreventionQuery(Player p, Location loc) {
        Claim claim = GriefPrevention.instance.dataStore.getClaimAt(loc, true, null);
        return claim == null || claim.allowBreak(p, loc.getBlock().getType()) == null;
    }
}
