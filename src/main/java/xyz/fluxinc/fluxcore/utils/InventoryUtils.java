package xyz.fluxinc.fluxcore.utils;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InventoryUtils {

    /**
     * Sort an inventory by item name and quantity
     * @param itemStacks The itemstacks to be sorted
     * @return The sorted list
     */
    public static ItemStack[] sortItemStacks(ItemStack[] itemStacks) {
        ItemStack[] newArr = new ItemStack[itemStacks.length];
        for (ItemStack itemStack : itemStacks) {
            if (itemStack == null) { continue; }
            for (int i = 0; i < newArr.length; i++) {
                if (newArr[i] == null) { newArr[i] = itemStack; break; }
                if (compareItemStacks(newArr[i], itemStack)) {
                    newArr = shiftByOne(newArr, i);
                    newArr[i] = itemStack;
                    break;
                }
            }
        }
        return newArr;
    }

    /**
     * Compares one itemstack to another
     * @param stack The stack to use as reference
     * @param comparator The stack to compare to
     * @return Returns true if the reference is greater than the comparator, or false otherwise
     */
    public static boolean compareItemStacks(ItemStack stack, ItemStack comparator) {
        return stack.toString().compareTo(comparator.toString()) > 0;
    }

    private static ItemStack[] shiftByOne(ItemStack[] itemStacks, int position) {
        int found = -1;
        for (int i = position+1; i < itemStacks.length; i++) {
            if (itemStacks[i] == null) { found = i; break; }
        }
        if (found != -1) {
            if (found - position >= 0) {
                System.arraycopy(itemStacks, position, itemStacks, position + 1, found - position);
            }
            itemStacks[position] = null;
        }
        return itemStacks;
    }

    /**
     * Distributes items inside a new inventory
     * @param inventoryName The name for the inventory
     * @param items The items to be distributed
     * @return The new inventory
     */
    public static Inventory generateDistributedInventory(String inventoryName, List<ItemStack> items) {
        Inventory inventory;
        switch (items.size()) {
            case 1:
                inventory = Bukkit.createInventory(null, 9, inventoryName);
                inventory.setItem(4, items.get(0));
                return inventory;
            case 2:
                inventory = Bukkit.createInventory(null, 9, inventoryName);
                inventory.setItem(2, items.get(0));
                inventory.setItem(6, items.get(1));
                return inventory;
            case 3:
                inventory = Bukkit.createInventory(null, 9, inventoryName);
                inventory.setItem(1, items.get(0));
                inventory.setItem(4, items.get(1));
                inventory.setItem(7, items.get(2));
                return inventory;
            case 4:
                inventory = Bukkit.createInventory(null, 9, inventoryName);
                inventory.setItem(1, items.get(0));
                inventory.setItem(3, items.get(1));
                inventory.setItem(5, items.get(2));
                inventory.setItem(7, items.get(3));
                return inventory;
            case 5:
                inventory = Bukkit.createInventory(null, 9, inventoryName);
                inventory.setItem(0, items.get(0));
                inventory.setItem(2, items.get(1));
                inventory.setItem(4, items.get(2));
                inventory.setItem(6, items.get(3));
                inventory.setItem(8, items.get(4));
                return inventory;
            case 6:
                inventory = Bukkit.createInventory(null, 18, inventoryName);
                inventory.setItem(1, items.get(0));
                inventory.setItem(3, items.get(1));
                inventory.setItem(5, items.get(2));
                inventory.setItem(7, items.get(3));
                inventory.setItem(11, items.get(4));
                inventory.setItem(15, items.get(5));
                return inventory;
            case 7:
                inventory = Bukkit.createInventory(null, 18, inventoryName);
                inventory.setItem(1, items.get(0));
                inventory.setItem(3, items.get(1));
                inventory.setItem(5, items.get(2));
                inventory.setItem(7, items.get(3));
                inventory.setItem(11, items.get(4));
                inventory.setItem(13, items.get(5));
                inventory.setItem(15, items.get(6));
                return inventory;
            case 8:
                inventory = Bukkit.createInventory(null, 18, inventoryName);
                inventory.setItem(0, items.get(0));
                inventory.setItem(2, items.get(1));
                inventory.setItem(4, items.get(2));
                inventory.setItem(6, items.get(3));
                inventory.setItem(8, items.get(4));
                inventory.setItem(10, items.get(5));
                inventory.setItem(13, items.get(6));
                inventory.setItem(16, items.get(7));
                return inventory;
            case 9:
                inventory = Bukkit.createInventory(null, 18, inventoryName);
                inventory.setItem(0, items.get(0));
                inventory.setItem(2, items.get(1));
                inventory.setItem(4, items.get(2));
                inventory.setItem(6, items.get(3));
                inventory.setItem(8, items.get(4));
                inventory.setItem(10, items.get(5));
                inventory.setItem(12, items.get(6));
                inventory.setItem(14, items.get(7));
                inventory.setItem(16, items.get(8));
                return inventory;
            case 10:
                inventory = Bukkit.createInventory(null, 27, inventoryName);
                inventory.setItem(0, items.get(0));
                inventory.setItem(2, items.get(1));
                inventory.setItem(4, items.get(2));
                inventory.setItem(6, items.get(3));
                inventory.setItem(8, items.get(4));
                inventory.setItem(10, items.get(5));
                inventory.setItem(12, items.get(6));
                inventory.setItem(14, items.get(7));
                inventory.setItem(16, items.get(8));
                inventory.setItem(22, items.get(9));
                return inventory;
            case 11:
                inventory = Bukkit.createInventory(null, 27, inventoryName);
                inventory.setItem(0, items.get(0));
                inventory.setItem(2, items.get(1));
                inventory.setItem(4, items.get(2));
                inventory.setItem(6, items.get(3));
                inventory.setItem(8, items.get(4));
                inventory.setItem(10, items.get(5));
                inventory.setItem(12, items.get(6));
                inventory.setItem(14, items.get(7));
                inventory.setItem(16, items.get(8));
                inventory.setItem(20, items.get(9));
                inventory.setItem(24, items.get(10));
                return inventory;
            case 12:
                inventory = Bukkit.createInventory(null, 27, inventoryName);
                inventory.setItem(0, items.get(0));
                inventory.setItem(2, items.get(1));
                inventory.setItem(4, items.get(2));
                inventory.setItem(6, items.get(3));
                inventory.setItem(8, items.get(4));
                inventory.setItem(10, items.get(5));
                inventory.setItem(12, items.get(6));
                inventory.setItem(14, items.get(7));
                inventory.setItem(16, items.get(8));
                inventory.setItem(20, items.get(9));
                inventory.setItem(22, items.get(10));
                inventory.setItem(24, items.get(11));
                return inventory;
            case 13:
                inventory = Bukkit.createInventory(null, 27, inventoryName);
                inventory.setItem(0, items.get(0));
                inventory.setItem(2, items.get(1));
                inventory.setItem(4, items.get(2));
                inventory.setItem(6, items.get(3));
                inventory.setItem(8, items.get(4));
                inventory.setItem(10, items.get(5));
                inventory.setItem(13, items.get(6));
                inventory.setItem(16, items.get(7));
                inventory.setItem(18, items.get(8));
                inventory.setItem(20, items.get(9));
                inventory.setItem(22, items.get(10));
                inventory.setItem(24, items.get(11));
                inventory.setItem(26, items.get(12));
                return inventory;
            case 14:
                inventory = Bukkit.createInventory(null, 27, inventoryName);
                inventory.setItem(0, items.get(0));
                inventory.setItem(2, items.get(1));
                inventory.setItem(4, items.get(2));
                inventory.setItem(6, items.get(3));
                inventory.setItem(8, items.get(4));
                inventory.setItem(10, items.get(5));
                inventory.setItem(12, items.get(6));
                inventory.setItem(14, items.get(7));
                inventory.setItem(16, items.get(8));
                inventory.setItem(18, items.get(9));
                inventory.setItem(20, items.get(10));
                inventory.setItem(22, items.get(11));
                inventory.setItem(24, items.get(12));
                inventory.setItem(26, items.get(13));
                return inventory;
            default:
                inventory = Bukkit.createInventory(null, items.size(), inventoryName);
                for (int x = 0; x < items.size(); x++) {
                    inventory.setItem(x, items.get(x));
                }
                return inventory;
        }
    }
}
