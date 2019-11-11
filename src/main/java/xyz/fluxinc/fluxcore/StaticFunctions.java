package xyz.fluxinc.fluxcore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// TODO: Refactor into separate classes

public class StaticFunctions {

    public static ItemStack addLore(ItemStack item, String lore){
        ItemMeta iMeta = item.getItemMeta();
        List<String> currentLore = Objects.requireNonNull(iMeta).getLore();
        if (currentLore == null) { currentLore = new ArrayList<>(); }
        currentLore.add(lore);
        iMeta.setLore(currentLore);
        item.setItemMeta(iMeta);
        return item;
    }

    public static String generateInvisibleLore(String lore) {
        StringBuilder hidden = new StringBuilder();
        for (char c : lore.toCharArray()) { hidden.append(ChatColor.COLOR_CHAR + "").append(c); }
        return hidden.toString();
    }

    public static String getInvisibleLore(String lore) {
        return lore.replaceAll(ChatColor.COLOR_CHAR + "", "");
    }

    private static List<Block> getVMBlockList(Block startingBlock, int maxBlocks) {
        List<Block> blocks = new ArrayList<>();
        Material template = startingBlock.getType();
        List<Block> toVisit = new ArrayList<>();
        toVisit.add(startingBlock);
        while (!toVisit.isEmpty() && blocks.size() < maxBlocks) {
            List<Block> processQueue = new ArrayList<>(toVisit);
            blocks.addAll(toVisit);
            toVisit = new ArrayList<>();
            for (Block block : processQueue) {
                List<Block> blockFaces = getBlockFaces(block);
                for (Block blockFace : blockFaces) {
                    if (block.getType() == template && !toVisit.contains(blockFace) && !blocks.contains(blockFace)) {
                        toVisit.add(block);
                    }
                    if (blocks.size() >= maxBlocks) { break; }
                }
                if (blocks.size() >= maxBlocks) { break; }
            }
        }
        return blocks;
    }

    private static List<Block> getBlockFaces(Block block) {
        List<Block> blocks = new ArrayList<>();
        blocks.add(block.getWorld().getBlockAt(block.getX()+1, block.getY(), block.getZ()));
        blocks.add(block.getWorld().getBlockAt(block.getX()-1, block.getY(), block.getZ()));
        blocks.add(block.getWorld().getBlockAt(block.getX(), block.getY()+1, block.getZ()));
        blocks.add(block.getWorld().getBlockAt(block.getX(), block.getY()-1, block.getZ()));
        blocks.add(block.getWorld().getBlockAt(block.getX(), block.getY(), block.getZ()+1));
        blocks.add(block.getWorld().getBlockAt(block.getX(), block.getY(), block.getZ()-1));
        return blocks;
    }


}
