package xyz.fluxinc.fluxcore.structure;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    Code based on https://github.com/zhero42/ModularChests
    Rewritten to be more reusable by CelesteMagisteel, with permission
 */

public class MultiblockStructure {

    private List<Material> borderMaterial;
    private List<Material> yMaterial;
    private List<Material> sideMaterial;
    private List<Material> internalMaterial;

    public MultiblockStructure(List<Material> borderMaterial, List<Material> sideMaterial, List<Material> internalMaterial) {
        this.borderMaterial = borderMaterial;
        this.yMaterial = sideMaterial;
        this.sideMaterial = sideMaterial;
        this.internalMaterial = internalMaterial;
    }

    public MultiblockStructure(List<Material> borderMaterial, List<Material> sideMaterial, List<Material> internalMaterial, List<Material> yMaterial) {
        this.borderMaterial = borderMaterial;
        this.yMaterial = yMaterial;
        this.sideMaterial = sideMaterial;
        this.internalMaterial = internalMaterial;
    }

    public MultiblockStructure(Material borderMaterial, Material sideMaterial, Material internalMaterial) {
        this.borderMaterial = new ArrayList<>(Collections.singletonList(borderMaterial));
        this.sideMaterial = new ArrayList<>(Collections.singletonList(sideMaterial));
        this.yMaterial = new ArrayList<>(Collections.singletonList(sideMaterial));
        this.internalMaterial = new ArrayList<>(Collections.singletonList(internalMaterial));
    }

    public MultiblockStructure(Material borderMaterial, Material sideMaterial, Material internalMaterial, Material yMaterial) {
        this.borderMaterial = new ArrayList<>(Collections.singletonList(borderMaterial));
        this.sideMaterial = new ArrayList<>(Collections.singletonList(sideMaterial));
        this.yMaterial = new ArrayList<>(Collections.singletonList(yMaterial));
        this.internalMaterial = new ArrayList<>(Collections.singletonList(internalMaterial));
    }

    public List<Material> getBorderMaterial() { return borderMaterial; }

    public List<Material> getyMaterial() { return yMaterial; }

    public List<Material> getSideMaterial() { return sideMaterial; }

    public List<Material> getInternalMaterial() { return internalMaterial; }

public CoordinatePair getCoordinatePair(Location location) {
    Material sel = location.getBlock().getType();
    World w = location.getWorld();
    if (isBoxMaterial(sel)) {
        int x = location.getBlockX(); int y = location.getBlockY(); int z = location.getBlockZ();

        int x1 = x; int x2 = x; int y1 = y; int y2 = y; int z1 = z; int z2 = z;

        do { sel = w.getBlockAt(x1--, y, z).getType(); } while(isBoxMaterial(sel));
        do { sel = w.getBlockAt(x2++, y, z).getType(); } while(isBoxMaterial(sel));
        do { sel = w.getBlockAt(x, y1--, z).getType(); } while(isBoxMaterial(sel));
        do { sel = w.getBlockAt(x, y2++, z).getType(); } while(isBoxMaterial(sel));
        do { sel = w.getBlockAt(x, y, z1--).getType(); } while(isBoxMaterial(sel));
        do { sel = w.getBlockAt(x, y, z2++).getType(); } while(isBoxMaterial(sel));

        return new CoordinatePair(new Location(w, x1+2, y1+2, z1+2), new Location(w, x2-2, y2-2, z2-2));
    }
    return null;
}

    public boolean isBoxMaterial(Material material) {
        return sideMaterial.contains(material)
                || yMaterial.contains(material)
                || internalMaterial.contains(material)
                || borderMaterial.contains(material);
    }
}
