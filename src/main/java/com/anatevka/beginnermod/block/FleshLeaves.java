package com.anatevka.beginnermod.block;

import com.anatevka.beginnermod.CustomSounds;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class FleshLeaves extends FleshBlock {
    public FleshLeaves(Material materialIn) {
        super(materialIn);
        this.setHardness(1.0F);
        this.setStepSound(CustomSounds.soundTypeFlesh);
    }
    public boolean isOpaqueCube()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, int x, int y, int z, int side)
    {
        Block block = worldIn.getBlock(x, y, z);
        return block == this ? false : super.shouldSideBeRendered(worldIn, x, y, z, side);
    }
}
