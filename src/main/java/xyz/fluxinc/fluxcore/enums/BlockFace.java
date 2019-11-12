package xyz.fluxinc.fluxcore.enums;

public enum BlockFace {

    TOP,
    BOTTOM,
    NORTH,
    SOUTH,
    EAST,
    WEST;


    /**
     * Simple method to convert between BlockFace enum and Direction enum
     * @param face The face you want to convert
     * @return The equal Direction
     */
    public static Direction getDirection(BlockFace face) {
        switch (face) {
            case TOP:
                return Direction.UP;
            case NORTH:
                return Direction.NORTH;
            case EAST:
                return Direction.EAST;
            case SOUTH:
                return Direction.SOUTH;
            case WEST:
                return Direction.WEST;
            case BOTTOM:
                return Direction.DOWN;
            default:
                return null;
        }
    }
}
