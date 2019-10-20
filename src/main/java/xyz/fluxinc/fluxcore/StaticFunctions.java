package xyz.fluxinc.fluxcore;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StaticFunctions {

    public static ItemStack addLore(ItemStack item, String lore){
        ItemMeta iMeta = item.getItemMeta();
        List<String> currentLore = Objects.requireNonNull(iMeta).getLore();
        if (currentLore == null) { currentLore = new ArrayList<>(); }
        currentLore.add(lore);
        iMeta.setLore(currentLore);
        item.setItemMeta(iMeta);
        return item;
    }

    public static String generateInvisibleLore(String lore) {
        StringBuilder hidden = new StringBuilder();
        for (char c : lore.toCharArray()) { hidden.append(ChatColor.COLOR_CHAR + "").append(c); }
        return hidden.toString();
    }

    public static String getInvisibleLore(String lore) {
        return lore.replaceAll(ChatColor.COLOR_CHAR + "", "");
    }



}
