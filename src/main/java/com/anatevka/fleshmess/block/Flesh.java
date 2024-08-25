package com.anatevka.fleshmess.block;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import com.anatevka.fleshmess.CustomSounds;
import com.anatevka.fleshmess.FleshMess;

public class Flesh extends Block {

    public Flesh(Material materialIn) {
        super(materialIn);
        this.setHardness(1.0F);
        this.setStepSound(CustomSounds.soundTypeFlesh);
        this.setTickRandomly(true);
    }

    @Override
    public Item getItemDropped(int metadata, Random rand, int fortune) {
        return Items.rotten_flesh;
    }

    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction,
        IPlantable plantable) {
        return plantable.getPlantType(world, x, y, z) == EnumPlantType.Plains;
    }

    protected boolean canSilkHarvest() {
        return false;
    }

    public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public void spreadFlesh(World worldIn, int x, int y, int z, List<Block> whitelist) {
        if (worldIn.getBlock(x, y, z) instanceof BlockLog) {
            worldIn.setBlock(x, y, z, FleshMess.boneLog);
            return;
        }

        if (worldIn.getBlock(x, y, z) instanceof BlockBush) {
            worldIn.setBlock(x, y, z, FleshMess.fleshStrand);
            return;
        }

        if (worldIn.getBlock(x, y, z) instanceof BlockLeaves) {
            worldIn.setBlock(x, y, z, FleshMess.fleshLeaves);
            return;
        }

        if (worldIn.getBlock(x, y, z) == Blocks.dirt) {
            worldIn.setBlock(x, y, z, FleshMess.corruptedSoil);
            return;
        }

        if (whitelist.contains(worldIn.getBlock(x, y, z))) {
            worldIn.setBlock(x, y, z, FleshMess.fleshBlock);
        }
    }

    public void updateTick(World worldIn, int x, int y, int z, Random random) {
        List<Block> whitelist = Arrays.asList(Blocks.grass);

        if (!worldIn.isRemote) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    for (int k = -1; k < 2; k++) {
                        spreadFlesh(worldIn, x + i, y + j, z + k, whitelist);
                    }
                }
            }
        }
    }

    public void onEntityCollidedWithBlock(World worldIn, int x, int y, int z, Entity entityIn) {
        if (entityIn instanceof EntityLivingBase && ((EntityLivingBase) entityIn).isPotionActive(31)) {
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(31, 10, 0));
        }
    }
}
