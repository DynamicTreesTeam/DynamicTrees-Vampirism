package com.harleyoconnor.dtvampirism;

import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

/**
 * @author Harley O'Connor
 */
@Mod(DTVampirism.MOD_ID)
public final class DTVampirism {

    public static final String MOD_ID = "dtvampirism";

    public DTVampirism() {
        RegistryHandler.setup(MOD_ID);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherData);
    }

    private void gatherData(final GatherDataEvent event) {
        GatherDataHelper.gatherTagData(MOD_ID, event);
        GatherDataHelper.gatherLootData(MOD_ID, event);
    }

}
