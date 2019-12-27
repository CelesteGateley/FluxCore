package xyz.fluxinc.fluxcore;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import net.coreprotect.CoreProtectAPI;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.fluxinc.fluxcore.configuration.LanguageManager;
import xyz.fluxinc.fluxcore.security.BlockAccessController;
import xyz.fluxinc.fluxcore.security.CoreProtectLogger;

public final class FluxCore extends JavaPlugin {

    private BlockAccessController blockAccessController;

    @Override
    public void onEnable() {
        // Plugin startup logic

        LanguageManager<FluxCore> langFile = new LanguageManager<>(this, "lang.yml");

        blockAccessController = new BlockAccessController();
        try { blockAccessController.registerWorldGuard(WorldGuardPlugin.inst()); } catch (NoClassDefFoundError ignored) {}
        try { blockAccessController.registerGriefPrevention(GriefPrevention.instance); } catch (NoClassDefFoundError ignored) {}

    }

    @Override
    public void onDisable() { blockAccessController = null; }

    private void severeErrorHandler(Exception e) {
        e.printStackTrace();
        getServer().getPluginManager().disablePlugin(this);
    }

    /**
     * Gives you a convenient instance of BlockAccessController, with all possible protection plugins registered
     * @return Current Block Access Controller
     */
    public BlockAccessController getBlockAccessController() { return blockAccessController; }

}
