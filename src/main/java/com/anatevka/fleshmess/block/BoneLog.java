package com.anatevka.fleshmess.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BoneLog extends Flesh {

    @SideOnly(Side.CLIENT)
    protected IIcon top_icon;
    protected IIcon side_icon;

    public BoneLog(Material materialIn) {
        super(materialIn);
    }

    public int getRenderType() {
        return 31;
    }

    public int onBlockPlaced(World worldIn, int x, int y, int z, int side, float subX, float subY, float subZ,
        int meta) {
        int j1 = meta & 3;
        byte b0 = 0;

        switch (side) {
            case 0:
            case 1:
                b0 = 0;
                break;
            case 2:
            case 3:
                b0 = 8;
                break;
            case 4:
            case 5:
                b0 = 4;
        }

        return j1 | b0;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        int k = meta & 12;
        int l = meta & 3;
        return k == 0 && (side == 1 || side == 0) ? this.getTopIcon(l)
            : (k == 4 && (side == 5 || side == 4) ? this.getTopIcon(l)
                : (k == 8 && (side == 2 || side == 3) ? this.getTopIcon(l) : this.getSideIcon(l)));
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int p_150163_1_) {
        return this.side_icon;
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int p_150161_1_) {
        return this.top_icon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.top_icon = reg.registerIcon("fleshmess:bonelog_top");
        this.side_icon = reg.registerIcon("fleshmess:bonelog_side");
    }

    public Item getItemDropped(int metadata, Random rand, int fortune) {
        return Items.bone;
    }

}
