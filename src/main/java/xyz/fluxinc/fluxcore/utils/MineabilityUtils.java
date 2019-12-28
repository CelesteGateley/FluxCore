package xyz.fluxinc.fluxcore.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Material.*;

public class MineabilityUtils {

    public static List<Material> STONE_PICKAXE_BLOCKS;
    public static List<Material> IRON_PICKAXE_BLOCKS;

    static {
        STONE_PICKAXE_BLOCKS = new ArrayList<>();
        STONE_PICKAXE_BLOCKS.add(IRON_ORE);
        STONE_PICKAXE_BLOCKS.add(LAPIS_ORE);
        STONE_PICKAXE_BLOCKS.add(NETHER_QUARTZ_ORE);
        STONE_PICKAXE_BLOCKS.add(IRON_BLOCK);
        STONE_PICKAXE_BLOCKS.add(LAPIS_BLOCK);
        STONE_PICKAXE_BLOCKS.add(REDSTONE_BLOCK);

        IRON_PICKAXE_BLOCKS = new ArrayList<>();
        IRON_PICKAXE_BLOCKS.add(DIAMOND_ORE);
        IRON_PICKAXE_BLOCKS.add(GOLD_ORE);
        IRON_PICKAXE_BLOCKS.add(REDSTONE_ORE);
        IRON_PICKAXE_BLOCKS.add(EMERALD_ORE);
        IRON_PICKAXE_BLOCKS.add(GOLD_BLOCK);
        IRON_PICKAXE_BLOCKS.add(EMERALD_BLOCK);
        IRON_PICKAXE_BLOCKS.add(DIAMOND_BLOCK);
    }

    public static boolean verifyBlockMining(ItemStack tool, Material block) {
        Material toolType = tool.getType();
        if (STONE_PICKAXE_BLOCKS.contains(block)) {
            return toolType == Material.STONE_PICKAXE || toolType == Material.IRON_PICKAXE || toolType == Material.DIAMOND_PICKAXE;
        } else if (IRON_PICKAXE_BLOCKS.contains(block)) {
            return toolType == Material.IRON_PICKAXE || toolType == Material.DIAMOND_PICKAXE;
        } else if (block == Material.OBSIDIAN) {
            return toolType == Material.DIAMOND_PICKAXE;
        }
        return true;
    }

}
