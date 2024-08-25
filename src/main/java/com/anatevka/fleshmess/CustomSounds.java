package com.anatevka.fleshmess;

import net.minecraft.block.Block;

public class CustomSounds {

    public static class SoundType extends Block.SoundType {

        public final String soundNameStep;
        public final String soundNameBreak;
        public final String soundNamePlace;

        public SoundType(String soundNameBreak, String soundNameStep, String soundNamePlace, float volume,
            float frequency) {
            super(soundNameStep, volume, frequency);
            this.soundNameStep = soundNameStep;
            this.soundNameBreak = soundNameBreak;
            this.soundNamePlace = soundNamePlace;
        }

        @Override
        public String getBreakSound() {
            if (soundNameBreak == null) return FleshMess.MODID + ":" + this.soundName;
            else return this.soundNameBreak;
        }

        @Override
        public String getStepResourcePath() {
            if (soundNameStep == null) return FleshMess.MODID + ":" + this.soundName;
            else return this.soundNameStep;
        }

        @Override
        public String func_150496_b() {
            if (soundNamePlace == null) return getBreakSound();
            else return this.soundNamePlace;
        }
    }

    public static SoundType soundTypeFlesh = new SoundType(
        "fleshmess:fleshbreak",
        "fleshmess:fleshstep",
        "fleshmess:fleshplace",
        1.0F,
        1.0F);
}
