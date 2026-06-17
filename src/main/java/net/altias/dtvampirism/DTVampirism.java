package net.altias.dtvampirism;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(DTVampirism.MODID)
public class DTVampirism {
    public static final String MODID = "dtvampirism";
    private static final Logger LOGGER = LogUtils.getLogger();

    public DTVampirism(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        //NeoForge.EVENT_BUS.register(this);

        DTVampirismRegistries.setup();

    }

    public static ResourceLocation location (String name){
        return ResourceLocation.fromNamespaceAndPath(DTVampirism.MODID, name);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

}
