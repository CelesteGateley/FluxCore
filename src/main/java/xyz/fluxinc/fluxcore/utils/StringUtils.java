package xyz.fluxinc.fluxcore.utils;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    /**
     * Converts a list of materials to a more readable format
     * @param materials The list of materials to be converted
     * @return The converted list
     */
    public static List<String> convertMaterialsToString(List<Material> materials) {
        List<String> strings = new ArrayList<>();
        for (Material material : materials) {
            strings.add(toTitleCase(material.name()));
        }
        return strings;
    }

    /**
     * Convert a single material to a more readable format
     * @param material The material to convert
     * @return The converted string
     */
    public static String convertMaterialToString(Material material) {
        return toTitleCase(material.name());
    }

    /**
     * Converts a string to title case
     * @author https://www.baeldung.com/java-string-title-case
     * @param string The string to be converted
     * @return The converted string
     */
    public static String toTitleCase(String string) {
        if (string == null || string.isEmpty()) { return string; }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : string.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }
}
