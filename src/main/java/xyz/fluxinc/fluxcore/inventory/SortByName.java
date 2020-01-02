package xyz.fluxinc.fluxcore.inventory;

import org.bukkit.inventory.ItemStack;

import java.util.Comparator;

public class SortByName implements Comparator<ItemStack> {

    @Override
    public int compare(ItemStack item1, ItemStack item2) {
        String s1 = item1.getType().name();
        String s2 = item2.getType().name();
        return s1.compareTo(s2);
    }
}
