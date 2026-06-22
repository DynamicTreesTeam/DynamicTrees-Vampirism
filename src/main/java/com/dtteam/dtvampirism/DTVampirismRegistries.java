package com.dtteam.dtvampirism;

import com.dtteam.dynamictrees.api.cell.CellKit;
import com.dtteam.dynamictrees.event.RegistryEvent;
import com.dtteam.dynamictrees.event.TypeRegistryEvent;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKit;
import com.dtteam.dynamictrees.tree.species.Species;
import com.dtteam.dtvampirism.tree.MagicSpecies;
import net.neoforged.bus.api.SubscribeEvent;

public class DTVampirismRegistries {
    public static void setup() {

    }

    @SubscribeEvent
    public static void registerSpeciesTypes (final TypeRegistryEvent<Species> event) {
        event.registerType(DTVampirism.location("magic"), MagicSpecies.TYPE);
    }
}
