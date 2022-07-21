package xyz.fluxinc.fluxcore.hooks;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Placeholder extends PlaceholderExpansion {

    private final boolean persist;
    private final JavaPlugin plugin;

    public Placeholder(JavaPlugin plugin) {
        this.plugin = plugin;
        this.persist = true;
    }

    @Override
    public boolean persist(){ return persist; }

    @Override
    public boolean canRegister(){ return true; }

    @Override
    public String getIdentifier() {
        return plugin.getName();
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().get(0);
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) { return this.placeholder(player, identifier); }

    public abstract String placeholder(Player player, String identifier);

    @Override
    public boolean register() {
        return super.register();
    }
}
