package xyz.fluxinc.fluxcore.configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class ConfigurationManager<Plugin extends JavaPlugin> {

    YamlConfiguration configuration;
    String filename;
    Plugin instance;

    /**
     * A Class used for managing configuration files within a FluxCore plugin
     * @param instance The plugin that is being used
     * @param configuration The name of the configuration file
     */
    public ConfigurationManager(Plugin instance, String configuration) {
        this.filename = configuration;
        this.instance = instance;
        reloadConfiguration();
    }

    /**
     * Copy any missing keys into the configuration file
     */
    public void verifyKeys() {
        YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(instance.getResource(configuration.getName())));
        for (Map.Entry<String, Object> entry : defaultConfig.getValues(true).entrySet()) {
            configuration.addDefault(entry.getKey(), entry.getValue());
        }
        configuration.options().copyDefaults(true);
        saveConfiguration();
    }

    /**
     * Returns the configuration file that is being managed
     * @return The configuration file
     */
    public YamlConfiguration getConfig() { return this.configuration; }

    /**
     * Returns a string from the configuration
     * @param key The key to look-up
     * @return The configuration value
     */
    public String getString(String key) { return this.configuration.getString(key); }

    /**
     * Returns a boolean from the configuration
     * @param key The key to look-up
     * @return The configuration value
     */
    public boolean getBoolean(String key) { return this.configuration.getBoolean(key); }

    /**
     * Returns an integer from the configuration
     * @param key The key to look-up
     * @return The configuration value
     */
    public int getInt(String key) { return this.configuration.getInt(key); }

    /**
     * Returns a double from the configuration
     * @param key The key to look-up
     * @return The configuration value
     */
    public double getDouble(String key) { return this.configuration.getDouble(key); }

    /**
     * Returns a long from the configuration
     * @param key The key to look-up
     * @return The configuration value
     */
    public long getLong(String key) { return this.configuration.getLong(key); }

    /**
     * Returns an array from the configuration
     * @param key The key to look-up
     * @return The configuration value
     */
    public List<?> getArray(String key) { return this.configuration.getList(key); }

    /**
     * Returns an object from the configuration
     * @param key The key to look-up
     * @param <T> The type of object you're trying to get
     * @return The configuration value
     */
    public <T> T getGeneric(String key) { return (T) this.configuration.get(key); }

    /**
     * Returns a raw config key with colors, but no prefix
     * @param key The key to return
     * @return The semi-formatted string
     */
    public String getFormattedString(String key) {
        return ChatColor.translateAlternateColorCodes('&', configuration.getString(key));
    }

    /**
     * Save any changes to the file
     */
    public void saveConfiguration() {
        try { configuration.save(new File(instance.getDataFolder(), filename)); }
        catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * Load any changes from the file
     */
    public void loadConfiguration() {
        try { configuration.load(new File(instance.getDataFolder(), filename)); }
        catch (IOException | InvalidConfigurationException e) { e.printStackTrace(); }
    }

    /**
     * Reinitialize the configuration file, saving the file if it does not exist
     */
    public void reloadConfiguration() {
        YamlConfiguration config = new YamlConfiguration();
        File configFile = new File(instance.getDataFolder(), filename);
        if (!configFile.exists()) {
            instance.saveResource(filename, false);
        }
        try { config.load(configFile); }
        catch (InvalidConfigurationException | IOException e) { e.printStackTrace(); Bukkit.getPluginManager().disablePlugin(instance); }
        this.configuration = config;
    }

}
