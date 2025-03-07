package com.harleyoconnor.dtvampirism;

import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * @author Harley O'Connor
 */
@Mod(DTVampirism.MOD_ID)
public final class DTVampirism {

    public static final String MOD_ID = "dtvampirism";

    public DTVampirism() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::gatherData);

        if (ModList.get().isLoaded("werewolves")){
            modEventBus.register(DTVampirismRegistries.class);
        }

        MinecraftForge.EVENT_BUS.register(this);

        RegistryHandler.setup(MOD_ID);
    }

    private void gatherData(final GatherDataEvent event) {
        GatherDataHelper.gatherTagData(MOD_ID, event);
        GatherDataHelper.gatherLootData(MOD_ID, event);
    }

    public static ResourceLocation location (String name){
        return new ResourceLocation(MOD_ID, name);
    }

}
