package xyz.fluxinc.fluxcore.utils;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoreUtils {

    private static int SPLIT_RATE = 40;

    /**
     * Appends a string to the end of an items lore
     * @param item The item that you want to add the line to
     * @param lore The string you with to append
     * @return The item with the lore appended
     */
    public static ItemStack addLore(ItemStack item, String lore){
        ItemMeta iMeta = item.getItemMeta();
        List<String> currentLore = Objects.requireNonNull(iMeta).getLore();
        if (currentLore == null) { currentLore = new ArrayList<>(); }
        if (lore.length() < SPLIT_RATE) {
            currentLore.add(lore);
        } else {
            currentLore.addAll(splitString(lore));
        }
        iMeta.setLore(currentLore);
        item.setItemMeta(iMeta);
        return item;
    }

    /**
     * Get a string that would be invisible when inside the lore
     * @param lore The string you with to be hidden
     * @return The resulting string
     */
    public static String generateInvisibleLore(String lore) {
        StringBuilder hidden = new StringBuilder();
        for (char c : lore.toCharArray()) { hidden.append(ChatColor.COLOR_CHAR + "").append(c); }
        return hidden.toString();
    }

    /**
     * Converts an invisible string to a normal string
     * @param lore the invisible string
     * @return The resulting string
     */
    public static String getInvisibleLore(String lore) { return lore.replaceAll(ChatColor.COLOR_CHAR + "", ""); }


    /**
     * Check to see if a set of lore has a specific invisible string
     * @param itemLore The lore to check for the string
     * @param requestedString The string you are looking for
     * @return Whether the lore contains the string or not
     */
    public static boolean hasInvisibleLore(List<String> itemLore, String requestedString) {
        for (String lore : itemLore) {
            lore = lore.replaceAll(ChatColor.COLOR_CHAR + "", "");
            if (lore == requestedString) { return true; }
        }
        return false;
    }

    /**
     * A convenient wrapper for hasInvisibleLore(List, String) to extract lore from an ItemStack
     * @param item The item you wish to get the lore from
     * @param requestedString The string you are looking for
     * @return Whether the lore contains the string or not
     */
    public static boolean hasInvisibleLore(ItemStack item, String requestedString) {
        ItemMeta iMeta = item.getItemMeta();
        if (iMeta == null) { return false; }
        List<String> lore = iMeta.getLore();
        return lore != null && hasInvisibleLore(lore, requestedString);
    }

    private static List<String> splitString(String string) {
        List<String> strings = new ArrayList<>();
        int splitCount = SPLIT_RATE;
        StringBuilder current = new StringBuilder();
        String colorCode = "";
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if (character == '\u00A7') {
                i++;
                character = string.charAt(i);
                switch (character) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd':
                    case 'e':
                    case 'f':
                    case 'k':
                    case 'l':
                    case 'm':
                    case 'n':
                    case 'o':
                    case 'r':
                        colorCode = "\u00A7" + character;
                        current.append(colorCode);
                        continue;
                }
            }
            if (character == '\n' || (character == ' ' && i >= splitCount)) {
                splitCount += SPLIT_RATE;
                strings.add(current.toString());
                current = new StringBuilder();
                current.append(colorCode);
            } else {
                current.append(character);
            }
        }
        if (!current.toString().equals("")) {
            strings.add(current.toString());
        }
        return strings;
    }
}
