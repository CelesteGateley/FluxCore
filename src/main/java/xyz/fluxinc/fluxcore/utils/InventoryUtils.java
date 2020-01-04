package xyz.fluxinc.fluxcore.utils;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.fluxinc.fluxcore.inventory.SortByName;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    /**
     * Removes durability from a tool
     * @param player The player breaking the blocks
     * @param tool The tool used to break the blocks
     * @param blocksBroken The number of blocks broken with the tool
     */
    public static void takeDurability(Player player, ItemStack tool, int blocksBroken) {
        Random random = new Random();
        double chance = (100D / (getEnchantmentLevel(tool, Enchantment.DURABILITY) + 1));
        ItemMeta iMeta = tool.getItemMeta();
        if (iMeta instanceof Damageable) {
            Damageable damageable = (Damageable) iMeta;
            for (int i = 0; i < blocksBroken; i++) {
                double rand = random.nextInt(99) + 1;
                if (rand >= chance) { continue; }
                damageable.setDamage(damageable.getDamage() + 1);
                if (damageable.getDamage() > tool.getType().getMaxDurability()) {
                    player.getInventory().remove(tool);
                    return;
                }
            }
            tool.setItemMeta((ItemMeta) damageable);
        }
    }

    /**
     * Gets the level of an enchantment on a tool
     * @param tool The tool to check
     * @param enchantment The enchantment to look for
     * @return The level of the enchantment
     */
    public static int getEnchantmentLevel(ItemStack tool, Enchantment enchantment) {
        ItemMeta itemMeta = tool.getItemMeta();
        if (itemMeta == null) { return 0; }
        return itemMeta.getEnchantLevel(enchantment);
    }
}
