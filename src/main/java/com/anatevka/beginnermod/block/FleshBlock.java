package com.anatevka.beginnermod.block;

import com.anatevka.beginnermod.BeginnerMod;
import com.anatevka.beginnermod.CustomSounds;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FleshBlock extends Block {
    public FleshBlock(Material materialIn) {
        super(materialIn);
        this.setHardness(1.0F);
        this.setStepSound(CustomSounds.soundTypeFlesh);
        this.setTickRandomly(true);
    }
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) { return true; }

    public void spreadFlesh(World worldIn, int x, int y, int z, List<Block> blacklist) {
        if (blacklist.contains(worldIn.getBlock(x, y, z))) { return; }
        if (worldIn.getBlock(x, y, z) instanceof BlockLiquid) { return; }

        if (worldIn.getBlock(x, y, z) instanceof BlockLog) {
            worldIn.setBlock(x, y, z, BeginnerMod.fleshLog);
            return;
        }

        if (worldIn.getBlock(x, y, z) instanceof BlockBush) {
            worldIn.setBlock(x, y, z, BeginnerMod.fleshStrand);
            return;
        }

        if (worldIn.getBlock(x, y, z) instanceof BlockLeaves) {
            worldIn.setBlock(x, y, z, BeginnerMod.fleshLeaves);
            return;
        }

        worldIn.setBlock(x, y, z, BeginnerMod.fleshBlock);
    }

    public void updateTick(World worldIn, int x, int y, int z, Random random) {
        List<Block> blacklist = Arrays.asList( Blocks.air, BeginnerMod.fleshStrand, BeginnerMod.fleshBlock, BeginnerMod.fleshLog, BeginnerMod.fleshLeaves);

        if (!worldIn.isRemote) {
           spreadFlesh(worldIn, x + 1, y, z, blacklist);
           spreadFlesh(worldIn, x - 1, y, z, blacklist);

           spreadFlesh(worldIn, x, y + 1, z, blacklist);
           spreadFlesh(worldIn, x, y - 1, z, blacklist);

           spreadFlesh(worldIn, x, y, z + 1, blacklist);
           spreadFlesh(worldIn, x, y, z - 1, blacklist);
        }
    }
}
