package net.altias.dtvampirism.growthlogic;

import com.dtteam.dynamictrees.api.registry.Registry;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKit;
import net.altias.dtvampirism.DTVampirism;
import net.minecraft.resources.ResourceLocation;

public class DTVampirismGrowthLogicKits {

    public static final GrowthLogicKit MAGIC = new MagicLogic(ResourceLocation.fromNamespaceAndPath(DTVampirism.MODID, "magic"));

    public static void register(final Registry<GrowthLogicKit> registry) {
        registry.registerAll(MAGIC);
    }


}
