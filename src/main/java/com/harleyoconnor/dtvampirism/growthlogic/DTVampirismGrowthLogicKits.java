package com.harleyoconnor.dtvampirism.growthlogic;

import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import net.minecraft.resources.ResourceLocation;
import com.harleyoconnor.dtvampirism.DTVampirism;

public class DTVampirismGrowthLogicKits {

    public static final GrowthLogicKit MAGIC = new MagicLogic(new ResourceLocation(DTVampirism.MOD_ID, "magic"));

    public static void register(final Registry<GrowthLogicKit> registry) {
        registry.registerAll(MAGIC);
    }

}
