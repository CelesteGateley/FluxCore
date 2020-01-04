package xyz.fluxinc.fluxcore.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToolUtils {

    public static List<Material> pickaxes;
    public static List<Material> axes;
    public static List<Material> shovels;
    public static List<Material> hoes;

    static {
        pickaxes = new ArrayList<>();
        pickaxes.add(Material.WOODEN_PICKAXE);
        pickaxes.add(Material.STONE_PICKAXE);
        pickaxes.add(Material.IRON_PICKAXE);
        pickaxes.add(Material.GOLDEN_PICKAXE);
        pickaxes.add(Material.DIAMOND_PICKAXE);

        axes = new ArrayList<>();
        axes.add(Material.WOODEN_AXE);
        axes.add(Material.STONE_AXE);
        axes.add(Material.IRON_AXE);
        axes.add(Material.GOLDEN_AXE);
        axes.add(Material.DIAMOND_AXE);

        shovels = new ArrayList<>();
        shovels.add(Material.WOODEN_SHOVEL);
        shovels.add(Material.STONE_SHOVEL);
        shovels.add(Material.IRON_SHOVEL);
        shovels.add(Material.GOLDEN_SHOVEL);
        shovels.add(Material.DIAMOND_SHOVEL);

        hoes = new ArrayList<>();
        hoes.add(Material.WOODEN_HOE);
        hoes.add(Material.STONE_HOE);
        hoes.add(Material.IRON_HOE);
        hoes.add(Material.GOLDEN_HOE);
        hoes.add(Material.DIAMOND_HOE);
    }

    /**
     * Get how much durability a tool has left
     * @param tool The tool to check
     * @return The amount of durability remaining or 0 if undamageable
     */
    public static int getRemainingDurability(ItemStack tool) {
        ItemMeta iMeta = tool.getItemMeta();
        if (iMeta instanceof Damageable) {
            return tool.getType().getMaxDurability() - ((Damageable) iMeta).getDamage();
        }
        return 0;
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
