package com.anatevka.beginnermod.block;

import com.anatevka.beginnermod.BeginnerMod;
import com.anatevka.beginnermod.CustomSounds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Random;

public class FleshStrand extends Block {
    public FleshStrand(Material materialIn) {
        super(materialIn);
        this.setHardness(1.0F);
        this.setStepSound(CustomSounds.soundTypeFlesh);
        this.setTickRandomly(true);
    }

    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z)
    {
        return super.canPlaceBlockAt(worldIn, x, y, z) && this.canBlockStay(worldIn, x, y, z);
    }

    protected boolean canPlaceBlockOn(Block ground)
    {
        return ground == BeginnerMod.fleshBlock;
    }

    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor)
    {
        super.onNeighborBlockChange(worldIn, x, y, z, neighbor);
        this.checkAndDropBlock(worldIn, x, y, z);
    }

    public void updateTick(World worldIn, int x, int y, int z, Random random)
    {
        this.checkAndDropBlock(worldIn, x, y, z);
    }

    protected void checkAndDropBlock(World worldIn, int x, int y, int z)
    {
        if (!this.canBlockStay(worldIn, x, y, z))
        {
            this.dropBlockAsItem(worldIn, x, y, z, worldIn.getBlockMetadata(x, y, z), 0);
            worldIn.setBlock(x, y, z, getBlockById(0), 0, 2);
        }
    }

    public boolean canBlockStay(World worldIn, int x, int y, int z)
    {
        return  worldIn.getBlock(x, y - 1, z) == BeginnerMod.fleshBlock;
    }

    public int getRenderType()
    {
        return 1;
    }
    public boolean isOpaqueCube()
    {
        return false;
    }
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z)
    {
        return null;
    }
}
