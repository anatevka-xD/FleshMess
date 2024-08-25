package com.anatevka.fleshmess.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class FleshStrand extends Flesh {

    public FleshStrand(Material materialIn) {
        super(materialIn);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World worldIn, int x, int y, int z) {
        return null;
    }

    public int getRenderType() {
        return 1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z) {
        return super.canPlaceBlockAt(worldIn, x, y, z) && this.canBlockStay(worldIn, x, y, z);
    }

    protected boolean canPlaceBlockOn(Block ground) {
        return ground.isOpaqueCube();
    }

    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        super.onNeighborBlockChange(worldIn, x, y, z, neighbor);
        this.checkAndDropBlock(worldIn, x, y, z);
    }

    public void updateTick(World worldIn, int x, int y, int z, Random random) {
        this.checkAndDropBlock(worldIn, x, y, z);
    }

    protected void checkAndDropBlock(World worldIn, int x, int y, int z) {
        if (!this.canBlockStay(worldIn, x, y, z)) {
            this.dropBlockAsItem(worldIn, x, y, z, new ItemStack(Items.rotten_flesh));
            worldIn.setBlock(x, y, z, getBlockById(0), 0, 2);
        }
    }

    public boolean canBlockStay(World worldIn, int x, int y, int z) {
        return worldIn.getBlock(x, y - 1, z)
            .isOpaqueCube();
    }

    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction,
        IPlantable plantable) {
        return false;
    }
}
