package xyz.fluxinc.fluxcore.utils;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.fluxinc.fluxcore.inventory.SortByName;

import java.util.Arrays;
import java.util.List;

public class InventoryUtils {

    /**
     * Sort an inventory by item name and quantity
     * @param inventory The inventory to sort
     * @return The sorted inventory
     */
    public static Inventory sortInventory(Inventory inventory) {
        Inventory sortedInventory = Bukkit.createInventory(null, inventory.getSize());
        List<ItemStack> items = Arrays.asList(inventory.getContents());
        items.sort(new SortByName());
        int slotId = 0;
        while (items.size() > 1) {
            if (items.get(0).getType() != items.get(1).getType() || items.get(0).getAmount() == items.get(0).getMaxStackSize()) {
                sortedInventory.setItem(slotId, items.get(0));
                items.remove(0);
                slotId++;
                continue;
            }
            int zeroAmount = items.get(0).getAmount();
            int oneAmount = items.get(1).getAmount();
            if (zeroAmount + oneAmount > items.get(0).getMaxStackSize()) {
                int difference =  (zeroAmount + oneAmount) - items.get(0).getMaxStackSize();
                items.get(0).setAmount(items.get(0).getMaxStackSize());
                items.get(1).setAmount(difference);
                sortedInventory.setItem(slotId, items.get(0));
                items.remove(0);
                slotId++;
            } else if (zeroAmount + oneAmount == items.get(0).getMaxStackSize()) {
                items.get(0).setAmount(zeroAmount + oneAmount);
                items.remove(1);
                sortedInventory.setItem(slotId, items.get(0));
                items.remove(0);
                slotId++;
            } else {
                items.get(0).setAmount(zeroAmount + oneAmount);
                items.remove(1);
            }
        }
        sortedInventory.setItem(slotId, items.get(0));
        return inventory;
    }
}
