package com.harleyoconnor.dtvampirism;

import com.ferreusveritas.dynamictrees.DynamicTrees;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import com.ferreusveritas.dynamictrees.blocks.rootyblocks.RootyBlock;
import com.ferreusveritas.dynamictrees.systems.DirtHelper;
import com.ferreusveritas.dynamictrees.systems.RootyBlockHelper;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author Harley O'Connor
 */
@Mod(DTVampirism.MOD_ID)
public final class DTVampirism {

    public static final String MOD_ID = "dtvampirism";

    public static final String CORRUPT_DIRT_LIKE = "corrupt_dirt_like";

    public DTVampirism() {
        RegistryHandler.setup(MOD_ID);

        DirtHelper.createNewAdjective(CORRUPT_DIRT_LIKE);

        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void onBlockRegistry(final RegistryEvent.Register<Block> event) {
        final Block cursedEarth = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("vampirism", "cursed_earth"));
        DirtHelper.registerSoil(cursedEarth, CORRUPT_DIRT_LIKE);

        for (final RootyBlock rooty : RootyBlockHelper.generateListForRegistry(true, MOD_ID)) {
            event.getRegistry().register(rooty);
        }
    }

    @SubscribeEvent
    public void gatherData(final GatherDataEvent event) {
        DynamicTrees.gatherTagGenerators(MOD_ID, event);
    }

}
