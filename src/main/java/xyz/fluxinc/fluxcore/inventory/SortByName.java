package xyz.fluxinc.fluxcore.inventory;

import org.bukkit.inventory.ItemStack;

import java.util.Comparator;

public class SortByName implements Comparator<ItemStack> {

    @Override
    public int compare(ItemStack item1, ItemStack item2) {
        return item1.getType().name().compareTo(item2.getType().name());
    }
}
