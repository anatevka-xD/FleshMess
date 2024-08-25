package com.anatevka.fleshmess.block;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class FleshLeaves extends Flesh {

    public FleshLeaves(Material materialIn) {
        super(materialIn);
        this.setLightOpacity(1);
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean isNormalCube() {
        return false;
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return false;
    }
}
