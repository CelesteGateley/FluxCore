package xyz.fluxinc.fluxcore.utils;

import org.bukkit.inventory.ItemStack;

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
}
