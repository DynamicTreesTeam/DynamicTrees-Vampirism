package net.altias.dtvampirism;

import com.dtteam.dynamictrees.api.cell.CellKit;
import com.dtteam.dynamictrees.event.RegistryEvent;
import com.dtteam.dynamictrees.event.TypeRegistryEvent;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKit;
import com.dtteam.dynamictrees.tree.species.Species;
import net.altias.dtvampirism.cell.DTVampirismCellKits;
import net.altias.dtvampirism.growthlogic.DTVampirismGrowthLogicKits;
import net.altias.dtvampirism.tree.MagicSpecies;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;

public class DTVampirismRegistries {
    public static void setup() {

    }

    @SubscribeEvent
    public static void onCellKitRegistry(final RegistryEvent<CellKit> event) {
        DTVampirismCellKits.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerSpeciesTypes (final TypeRegistryEvent<Species> event) {
        event.registerType(DTVampirism.location("magic"), MagicSpecies.TYPE);
    }

    @SubscribeEvent
    public static void onGrowthLogicKitRegistry(final RegistryEvent<GrowthLogicKit> event) {
        DTVampirismGrowthLogicKits.register(event.getRegistry());
    }
}
