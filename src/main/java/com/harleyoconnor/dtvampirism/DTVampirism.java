package com.harleyoconnor.dtvampirism;

import com.ferreusveritas.dynamictrees.DynamicTrees;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * @author Harley O'Connor
 */
@Mod(DTVampirism.MOD_ID)
public final class DTVampirism {

    public static final String MOD_ID = "dtvampirism";

    public DTVampirism() {
        RegistryHandler.setup(MOD_ID);

        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void gatherData(final GatherDataEvent event) {
        DynamicTrees.gatherTagGenerators(MOD_ID, event);
    }

}
