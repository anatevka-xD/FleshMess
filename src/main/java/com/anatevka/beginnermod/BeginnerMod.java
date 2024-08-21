package com.anatevka.beginnermod;

import com.anatevka.beginnermod.block.FleshBlock;
import com.anatevka.beginnermod.block.FleshLeaves;
import com.anatevka.beginnermod.block.FleshLog;
import com.anatevka.beginnermod.block.FleshStrand;
import com.anatevka.beginnermod.item.BoneScythe;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemFood;
import net.minecraftforge.common.util.EnumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = BeginnerMod.MODID, version = Tags.VERSION, name = "BeginnerMod", acceptedMinecraftVersions = "[1.7.10]")
public class BeginnerMod {

    public static Item boneScythe;
    public static Item greenBottle;
    public static Item redBottle;
    public static Block fleshBlock;
    public static Block fleshStrand;
    public static Block fleshLog;
    public static Block fleshLeaves;

    public static final Item.ToolMaterial fleshToolMaterial = EnumHelper.addToolMaterial(
        "fleshToolMaterial", 2, 750, 6.0F, 2.5F, 1);

    public static final String MODID = "beginnermod";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = "com.anatevka.beginnermod.ClientProxy", serverSide = "com.anatevka.beginnermod.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        boneScythe = new BoneScythe(fleshToolMaterial).setUnlocalizedName("BoneScythe").setTextureName("beginnermod:bonescythe").setCreativeTab(tabBeginnerMod);
        greenBottle = new ItemFood(10, 1.0F, false).setUnlocalizedName("GreenBottle").setTextureName("beginnermod:greenbottle").setCreativeTab(tabBeginnerMod);
        redBottle = new ItemFood(20, 1.0F, true).setUnlocalizedName("RedBottle").setTextureName("beginnermod:redbottle").setCreativeTab(tabBeginnerMod);


        fleshBlock = new FleshBlock(Material.sponge).setBlockName("FleshBlock").setBlockTextureName("beginnermod:fleshblock").setCreativeTab(tabBeginnerMod);
        fleshStrand = new FleshStrand(Material.sponge).setBlockName("FleshStrand").setBlockTextureName("beginnermod:fleshstrand").setCreativeTab(tabBeginnerMod);
        fleshLog = new FleshLog(Material.sponge).setBlockName("FleshLog").setBlockTextureName("beginnermod:fleshlog").setCreativeTab(tabBeginnerMod);
        fleshLeaves = new FleshLeaves(Material.sponge).setBlockName("FleshLeaves").setBlockTextureName("beginnermod:fleshleaves").setCreativeTab(tabBeginnerMod);

        GameRegistry.registerItem(boneScythe, boneScythe.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(greenBottle, greenBottle.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(redBottle, redBottle.getUnlocalizedName().substring(5));

        GameRegistry.registerBlock(fleshBlock, fleshBlock.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(fleshStrand, fleshStrand.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(fleshLog, fleshLog.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(fleshLeaves, fleshLeaves.getUnlocalizedName().substring(5));

        GameRegistry.addSmelting(greenBottle, new ItemStack(redBottle), 5.0F);

        proxy.preInit(event);
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        GameRegistry.addRecipe(new ItemStack(fleshBlock), new Object[]{"fff", "fff", "fff", 'f', Items.rotten_flesh});
        GameRegistry.addRecipe(new ItemStack(boneScythe), new Object[]{"bbb", " fb", " b ", 'f', Items.rotten_flesh, 'b', Items.bone});
        GameRegistry.addShapelessRecipe(new ItemStack(Items.rotten_flesh, 9), new ItemStack(fleshBlock));

        proxy.init(event);
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    public static CreativeTabs tabBeginnerMod = new CreativeTabs("tabBeginnerMod") {
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
