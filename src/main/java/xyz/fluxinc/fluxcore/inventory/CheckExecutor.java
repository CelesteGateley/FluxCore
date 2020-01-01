package xyz.fluxinc.fluxcore.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class CheckExecutor {

    public abstract boolean verifyItemStack(ItemStack itemStack);

    public abstract Material getMaterial();

    public abstract void executeIfTrue(Player player);

    public abstract void executeIfFalse(Player player);

    public abstract void removeOnLeave(Player player);
}
