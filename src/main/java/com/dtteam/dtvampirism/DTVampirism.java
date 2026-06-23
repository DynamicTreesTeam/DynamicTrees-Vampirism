package com.dtteam.dtvampirism;

import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.pod.Pod;
import com.dtteam.dynamictrees.block.soil.SoilProperties;
import com.dtteam.dynamictrees.data.GatherDataHelper;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

@Mod(DTVampirism.MODID)
public class DTVampirism {
    public static final String MODID = "dtvampirism";
    private static final Logger LOGGER = LogUtils.getLogger();

    public DTVampirism(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::gatherData);

    }

    public static ResourceLocation location (String name){
        return ResourceLocation.fromNamespaceAndPath(DTVampirism.MODID, name);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void gatherData(final GatherDataEvent event) {
        GatherDataHelper.gatherAllData(MODID, event,
                SoilProperties.REGISTRY,
                Family.REGISTRY,
                Species.REGISTRY,
                LeavesProperties.REGISTRY,
                //Fruit.REGISTRY,
                Pod.REGISTRY
        );
    }

}
