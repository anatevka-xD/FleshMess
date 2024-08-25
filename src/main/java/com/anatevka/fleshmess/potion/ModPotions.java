package com.anatevka.fleshmess.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

import com.anatevka.fleshmess.FleshMess;

public class ModPotions extends Potion {

    private final ResourceLocation icon;
    private int statusIconIndex = 31;

    public ModPotions(String name, int id, boolean isBad, int color) {
        super(id, isBad, color);
        this.icon = new ResourceLocation(FleshMess.MODID, "textures/effects/potions.png");
    }

    @Override
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(icon);
        return super.getStatusIconIndex();
    }

    public Potion setIconIndex(int par1, int par2) {
        super.setIconIndex(par1, par2);
        return this;
    }

    public boolean isReady(int p_76397_1_, int p_76397_2_) {
        int k;

        if (this.id == FleshMess.corruptionPotion.id) {
            k = 5 >> p_76397_2_;
            return k > 0 ? p_76397_1_ % k == 0 : true;
        }

        return true;
    }

    public void performEffect(EntityLivingBase entity, int level) {
        if (this.id == FleshMess.corruptionPotion.id) {
            entity.attackEntityFrom(FleshMess.FLESH_DAMAGE, 1.0F);
        }
    }
}
