package xyz.fluxinc.fluxcore.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class CheckExecutor {

    /**
     * Check whether an ItemStack matches the required item
     * @param itemStack The ItemStack to check
     * @return Whether the ItemStack matches the criteria
     */
    public abstract boolean verifyItemStack(ItemStack itemStack);

    /**
     * Return the material that the expected item will be. Returns null if a specific material is not required
     * @return The required material or null
     */
    public abstract Material getMaterial();

    /**
     * The function to execute if the item is found in the players inventory
     * @param player The player who has the item
     */
    public abstract void executeIfTrue(Player player);

    /**
     * The function to execute if the item is not found in the players inventory
     * @param player The player who does not have the item
     */
    public abstract void executeIfFalse(Player player);

    /**
     * Clean-up function for if a player leaves
     * @param player The player who left
     */
    public abstract void removeOnLeave(Player player);
}
