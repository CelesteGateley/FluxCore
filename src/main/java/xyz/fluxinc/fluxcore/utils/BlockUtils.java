package xyz.fluxinc.fluxcore.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import xyz.fluxinc.fluxcore.enums.BlockFace;
import xyz.fluxinc.fluxcore.enums.Direction;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class BlockUtils {

    /**
     * Gets a list of connected blocks for the same type.
     * @param startingBlock The block to start checking from
     * @param maxBlocks The maximum number of blocks to find. Use -1 for unlimited
     * @return The list of found blocks
     */
    public static List<Block> getVMBlockList(Block startingBlock, int maxBlocks) {
        return getVMBlockList(startingBlock, maxBlocks, false);
    }

    /**
     * Gets a list of connected blocks for the same type.
     * @param startingBlock The block to start checking from
     * @param maxBlocks The maximum number of blocks to find. Use -1 for unlimited
     * @param includeCorners should corners be included when checking VM blocks
     * @return The list of found blocks
     */
    public static List<Block> getVMBlockList(Block startingBlock, int maxBlocks, boolean includeCorners) {
        List<Block> blocks = new ArrayList<>();
        Material template = startingBlock.getType();
        List<Block> toVisit = new ArrayList<>();
        toVisit.add(startingBlock);
          do {
            List<Block> processQueue = new ArrayList<>(toVisit);
            blocks.addAll(toVisit);
            toVisit = new ArrayList<>();
            for (Block block : processQueue) {
                List<Block> blockFaces = includeCorners ? getBlockCube(block, 1) : getBlockFaces(block);
                for (Block blockFace : blockFaces) {
                    if (blockFace.getType() == template && !toVisit.contains(blockFace) && !blocks.contains(blockFace)) {
                        toVisit.add(blockFace);
                    }
                    if (blocks.size() >= maxBlocks) { break; }
                }
                if (blocks.size() >= maxBlocks) { break; }
            }
        } while (!toVisit.isEmpty() && (blocks.size() < maxBlocks || maxBlocks == -1));
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
     * Get a cube of blocks, given the center block
     * @param block The starting block
     * @param radius How many blocks away from center should be listed
     * @return The list of blocks
     */
    public static List<Block> getBlockCube(Block block, int radius) {
        List<Block> blocks = new ArrayList<>();

        for (int x = abs(radius) * -1; x <= abs(radius); x++) {
            for (int y = abs(radius) * -1; y <= abs(radius); y++) {
                for (int z = abs(radius) * -1; z <= abs(radius); z++) {
                    blocks.add(block.getWorld().getBlockAt(block.getX() + x, block.getY() + y, block.getZ() + z));
                }
            }
        }
        return blocks;
    }

    /**
     * Get a list of blocks of the same type in a given direction. Always returns the initial block as the first item
     * @param block The starting block
     * @param direction The direction to head. Use constants to specify
     * @return The list of blocks
     */
    public static List<Block> getDirectionalBlockList(Block block, Direction direction) {
        List<Block> blocks = new ArrayList<>();
        Block nextBlock = block;
        do {
            blocks.add(nextBlock);
            Block newBlock = getBlockFace(block, Direction.getBlockFace(direction));
            nextBlock = newBlock != null && newBlock.getType() == block.getType() ? newBlock : null;
        } while(nextBlock != null);
        return blocks;
    }


    /**
     * Get a block at a specific face
     * @param block The block to check
     * @param face The specific face you wish to get.
     * @return The block at that face
     */
    public static Block getBlockFace(Block block, BlockFace face) {
        switch (face) {
            case TOP:
                return block.getWorld().getBlockAt(block.getX(), block.getY() + 1, block.getZ());
            case NORTH:
                return block.getWorld().getBlockAt(block.getX(), block.getY(), block.getZ() + 1);
            case EAST:
                return block.getWorld().getBlockAt(block.getX() + 1, block.getY(), block.getZ());
            case SOUTH:
                return block.getWorld().getBlockAt(block.getX(), block.getY(), block.getZ() - 1);
            case WEST:
                return block.getWorld().getBlockAt(block.getX() - 1, block.getY(), block.getZ());
            case BOTTOM:
                return block.getWorld().getBlockAt(block.getX(), block.getY() - 1, block.getZ());
            default:
                return null;
        }
    }

    /**
     * A hack method to check if a block can be broken by a specific tool.
     * Works by checking if it drops anything when broken by the tool
     * @param block The block to test
     * @param tool The tool to test
     * @return Whether the block can be broken or not
     */
    public static boolean canBreak(Block block, ItemStack tool) {
        return !block.getDrops(tool).isEmpty();
    }
}
