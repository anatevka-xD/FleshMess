package com.anatevka.beginnermod.block;

import com.anatevka.beginnermod.CustomSounds;
import net.minecraft.block.material.Material;

public class FleshLog extends FleshBlock {
    public FleshLog(Material materialIn) {
        super(materialIn);
        this.setHardness(1.0F);
        this.setStepSound(CustomSounds.soundTypeFlesh);
    }
}
