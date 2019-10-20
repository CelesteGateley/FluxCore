package xyz.fluxinc.fluxcore.configuration;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConfigurationManager<Plugin extends JavaPlugin> {

    YamlConfiguration configuration;
    Plugin instance;

    public ConfigurationManager(Plugin instance, String configuration) {
        YamlConfiguration config = new YamlConfiguration();
        instance.saveResource(configuration, false);
        try { config.load(new File(instance.getDataFolder(), configuration)); }
        catch (InvalidConfigurationException | IOException e) { e.printStackTrace(); Bukkit.getPluginManager().disablePlugin(instance); }
        this.configuration = config;
        this.instance = instance;
    }

    public YamlConfiguration getConfig() { return this.configuration; }

}
