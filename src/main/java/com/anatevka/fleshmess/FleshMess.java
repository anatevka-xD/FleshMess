package com.anatevka.fleshmess;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.EnumHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anatevka.fleshmess.block.*;
import com.anatevka.fleshmess.item.BoneScythe;
import com.anatevka.fleshmess.potion.ModPotions;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = FleshMess.MODID, version = Tags.VERSION, name = "FleshMess", acceptedMinecraftVersions = "[1.7.10]")
public class FleshMess {

    public static Item boneScythe;

    public static Block fleshBlock;
    public static Block boneLog;
    public static Block fleshLeaves;
    public static Block fleshStrand;
    public static Block corruptedSoil;

    public static Potion corruptionPotion;

    public static DamageSource FLESH_DAMAGE = (new DamageSource("fleshDamage")).setMagicDamage();

    public static final Item.ToolMaterial fleshToolMaterial = EnumHelper
        .addToolMaterial("fleshToolMaterial", 2, 750, 6.0F, 2.5F, 1);

    public static final String MODID = "fleshmess";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = "com.anatevka.fleshmess.ClientProxy", serverSide = "com.anatevka.fleshmess.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        boneScythe = new BoneScythe(fleshToolMaterial).setUnlocalizedName("BoneScythe")
            .setTextureName("fleshmess:bonescythe")
            .setCreativeTab(tabFleshMess);

        fleshBlock = new FleshBlock(Material.ground).setBlockName("FleshBlock")
            .setBlockTextureName("fleshmess:fleshblock")
            .setCreativeTab(tabFleshMess);
        boneLog = new FleshLog(Material.ground).setBlockName("BoneLog")
            .setBlockTextureName("fleshmess:bonelog_side")
            .setCreativeTab(tabFleshMess);
        fleshLeaves = new FleshLeaves(Material.leaves).setBlockName("FleshLeaves")
            .setBlockTextureName("fleshmess:fleshleaves")
            .setCreativeTab(tabFleshMess);
        fleshStrand = new FleshStrand(Material.plants).setBlockName("FleshStrand")
            .setBlockTextureName("fleshmess:fleshstrand")
            .setCreativeTab(tabFleshMess);
        corruptedSoil = new CorruptedSoil(Material.ground).setBlockName("CorruptedSoil")
            .setBlockTextureName("fleshmess:corruptedsoil")
            .setCreativeTab(tabFleshMess);

        GameRegistry.registerItem(
            boneScythe,
            boneScythe.getUnlocalizedName()
                .substring(5));

        GameRegistry.registerBlock(
            fleshBlock,
            fleshBlock.getUnlocalizedName()
                .substring(5));
        GameRegistry.registerBlock(
            boneLog,
            boneLog.getUnlocalizedName()
                .substring(5));
        GameRegistry.registerBlock(
            fleshLeaves,
            fleshLeaves.getUnlocalizedName()
                .substring(5));
        GameRegistry.registerBlock(
            fleshStrand,
            fleshStrand.getUnlocalizedName()
                .substring(5));
        GameRegistry.registerBlock(
            corruptedSoil,
            corruptedSoil.getUnlocalizedName()
                .substring(5));

        Potion[] potionTypes = null;

        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.getName()
                    .equals("potionTypes")
                    || f.getName()
                        .equals("field_76425_a")) {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                    potionTypes = (Potion[]) f.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            } catch (Exception e) {
                System.err.println("Severe error, please report this to the mod author:");
                System.err.println(e);
            }
        }

        proxy.preInit(event);
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        GameRegistry
            .addRecipe(new ItemStack(fleshBlock), new Object[] { "fff", "fff", "fff", 'f', Items.rotten_flesh });
        GameRegistry.addRecipe(
            new ItemStack(boneScythe),
            new Object[] { "bbb", " fb", " b ", 'f', Items.rotten_flesh, 'b', Items.bone });
        GameRegistry.addShapelessRecipe(new ItemStack(Items.rotten_flesh, 9), new ItemStack(fleshBlock));

        corruptionPotion = (new ModPotions("corruption", 31, false, 4393481)).setIconIndex(0, 0)
            .setPotionName("potion.CorruptionPotion");

        proxy.init(event);
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    public static CreativeTabs tabFleshMess = new CreativeTabs("tabFleshMess") {

        @Override
        public Item getTabIconItem() {
            return new ItemStack(boneScythe).getItem();
        }
    };

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
