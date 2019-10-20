package xyz.fluxinc.fluxcore.configuration;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class LanguageManager<Plugin extends JavaPlugin> extends ConfigurationManager<Plugin> {

    public LanguageManager(Plugin plugin, String langFile) { super(plugin, langFile); }

    public String generateMessage(String langKey, Map<String, String> variables) {
        String prefix = configuration.getString("prefix");
        String msg = configuration.getString(langKey);
        if (prefix == null || msg == null) { instance.getLogger().severe("Invalid Lang File, missing elements prefix or " + langKey); return ""; }
        for (Map.Entry<String,String> pair : variables.entrySet()) { msg = msg.replaceAll("%" + pair.getKey() + "%", pair.getValue()); }
        return ChatColor.translateAlternateColorCodes('&', prefix + msg);
    }

    public String generateMessage(String langKey) {
        String prefix = configuration.getString("prefix");
        String msg = configuration.getString(langKey);
        if (prefix == null || msg == null) { instance.getLogger().severe("Invalid Lang File, missing elements prefix or " + langKey); return ""; }
        return ChatColor.translateAlternateColorCodes('&', prefix + " " + msg);
    }
}
