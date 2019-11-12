package xyz.fluxinc.fluxcore.enums;

public enum Direction {
    UP,
    DOWN,
    NORTH,
    SOUTH,
    EAST,
    WEST;

    /**
     * Simple method to convert between BlockFace enum and Direction enum
     * @param direction The direction you want to convert
     * @return The equal BlockFace
     */
    public static BlockFace getBlockFace(Direction direction) {
        switch (direction) {
            case UP:
                return BlockFace.TOP;
            case NORTH:
                return BlockFace.NORTH;
            case EAST:
                return BlockFace.EAST;
            case SOUTH:
                return BlockFace.SOUTH;
            case WEST:
                return BlockFace.WEST;
            case DOWN:
                return BlockFace.BOTTOM;
        }
        return BlockFace.TOP;
    }

}
