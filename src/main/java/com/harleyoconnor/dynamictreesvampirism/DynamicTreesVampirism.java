package com.harleyoconnor.dynamictreesvampirism;

import com.harleyoconnor.dynamictreesvampirism.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @author Harley O'Connor
 */
@Mod(modid= AddonConstants.MOD_ID, name= AddonConstants.MOD_NAME, dependencies = AddonConstants.MOD_DEPENDENCIES, updateJSON = "https://github.com/Harleyoc1/DynamicTreesVersionInfo/blob/master/Add-ons/Vamprisim.json?raw=true")
public final class DynamicTreesVampirism {

    @SidedProxy(clientSide = AddonConstants.PACKAGE_GROUP + ".proxy.ClientProxy", serverSide = AddonConstants.PACKAGE_GROUP + ".proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

}
