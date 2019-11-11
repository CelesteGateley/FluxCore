package xyz.fluxinc.fluxcore.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockUtils {

    /*
        EAST/WEST = +/- X
        TOP/BOTTOM = +/- Y
        NORTH/SOUTH = +/- Z
     */

    public static final int TOP_FACE = 1;
    public static final int NORTH_FACE = 2;
    public static final int EAST_FACE = 3;
    public static final int SOUTH_FACE = 4;
    public static final int WEST_FACE = 5;
    public static final int BOTTOM_FACE = 6;

    /**
     * Gets a list of connected blocks for the same type.
     * @param startingBlock The block to start checking from
     * @param maxBlocks The maximum number of blocks to find. Use -1 for unlimited
     * @return The list of found blocks
     */
    public static List<Block> getVMBlockList(Block startingBlock, int maxBlocks) {
        List<Block> blocks = new ArrayList<>();
        Material template = startingBlock.getType();
        List<Block> toVisit = new ArrayList<>();
        toVisit.add(startingBlock);
        while (!toVisit.isEmpty() && (blocks.size() < maxBlocks || maxBlocks == -1)) {
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

    /**
     * Gets a list of blocks connected to the specified block, in the following order: Top, North, East, South, West, Bottom
     * @param block The block to get the faces of
     * @return The connected blocks
     */
    public static List<Block> getBlockFaces(Block block) {
        List<Block> blocks = new ArrayList<>();
        blocks.add(block.getWorld().getBlockAt(block.getX(), block.getY()+1, block.getZ()));
        blocks.add(block.getWorld().getBlockAt(block.getX(), block.getY(), block.getZ()+1));
        blocks.add(block.getWorld().getBlockAt(block.getX()+1, block.getY(), block.getZ()));
        blocks.add(block.getWorld().getBlockAt(block.getX(), block.getY(), block.getZ()-1));
        blocks.add(block.getWorld().getBlockAt(block.getX()-1, block.getY(), block.getZ()));
        blocks.add(block.getWorld().getBlockAt(block.getX(), block.getY()-1, block.getZ()));

        return blocks;
    }

    /**
     * Get a block at a specific face. Use constants to specify face
     * @param block The block to check
     * @param face The specific face you wish to get
     * @return The block at that face
     */
    public static Block getBlockFace(Block block, int face) {
        switch (face) {
            case 1:
                return block.getWorld().getBlockAt(block.getX(), block.getY() + 1, block.getZ());
            case 2:
                return block.getWorld().getBlockAt(block.getX(), block.getY(), block.getZ() + 1);
            case 3:
                return block.getWorld().getBlockAt(block.getX() + 1, block.getY(), block.getZ());
            case 4:
                return block.getWorld().getBlockAt(block.getX(), block.getY(), block.getZ() - 1);
            case 5:
                return block.getWorld().getBlockAt(block.getX() - 1, block.getY(), block.getZ());
            case 6:
                return block.getWorld().getBlockAt(block.getX(), block.getY() - 1, block.getZ());
            default:
                return null;
        }
    }
}
