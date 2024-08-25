package com.anatevka.fleshmess.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class NoTorchesBlock extends Block {

    public NoTorchesBlock(Material materialIn) {
        super(materialIn);
    }

    // Seems to do nothing on its own
    public boolean isOpaqueCube() {
        return false;
    }

    // Seems to work on its own
    public boolean renderAsNormalBlock() {
        return false;
    }

}
