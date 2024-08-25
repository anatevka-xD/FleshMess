package com.anatevka.fleshmess.block;

import net.minecraft.block.material.Material;

public class CorruptedSoil extends Flesh {

    public CorruptedSoil(Material materialIn) {
        super(materialIn);
        this.setTickRandomly(false);
    }
}
