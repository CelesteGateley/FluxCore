package xyz.fluxinc.fluxcore.structure;

/*
    Code based on https://github.com/zhero42/ModularChests
 */

import org.bukkit.Location;

public class CoordinatePair {

    private Location start;
    private Location end;

    public CoordinatePair(Location start, Location end) {
        this.start = start;
        this.end = end;
    }

    public Location getStart() { return start; }

    public Location getEnd() { return end; }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CoordinatePair
                && ((CoordinatePair) obj).getEnd().equals(end)
                && ((CoordinatePair) obj).getStart().equals(start);
    }


}
