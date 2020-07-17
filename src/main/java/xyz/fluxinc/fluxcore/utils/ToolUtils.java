package xyz.fluxinc.fluxcore.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.bukkit.Material.*;

public class ToolUtils {

    public static List<Material> pickaxes;
    public static List<Material> axes;
    public static List<Material> shovels;
    public static List<Material> hoes;

    static {
        pickaxes = new ArrayList<>();
        pickaxes.add(WOODEN_PICKAXE);
        pickaxes.add(STONE_PICKAXE);
        pickaxes.add(IRON_PICKAXE);
        pickaxes.add(GOLDEN_PICKAXE);
        pickaxes.add(DIAMOND_PICKAXE);
        pickaxes.add(NETHERITE_PICKAXE);

        axes = new ArrayList<>();
        axes.add(WOODEN_AXE);
        axes.add(STONE_AXE);
        axes.add(IRON_AXE);
        axes.add(GOLDEN_AXE);
        axes.add(DIAMOND_AXE);
        axes.add(NETHERITE_AXE);

        shovels = new ArrayList<>();
        shovels.add(WOODEN_SHOVEL);
        shovels.add(STONE_SHOVEL);
        shovels.add(IRON_SHOVEL);
        shovels.add(GOLDEN_SHOVEL);
        shovels.add(DIAMOND_SHOVEL);
        shovels.add(NETHERITE_SHOVEL);

        hoes = new ArrayList<>();
        hoes.add(WOODEN_HOE);
        hoes.add(STONE_HOE);
        hoes.add(IRON_HOE);
        hoes.add(GOLDEN_HOE);
        hoes.add(DIAMOND_HOE);
        hoes.add(NETHERITE_HOE);
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

    /**
     *  Transfers enchantments from one tool to another
     * @param source The original tool with enchantments
     * @param destination The tool to be enchanted
     * @return The destination with the enchantments added
     */
    public static ItemStack transferEnchantments (ItemStack source, ItemStack destination){
        destination.addEnchantments(source.getEnchantments());
        return destination;
    }
}
