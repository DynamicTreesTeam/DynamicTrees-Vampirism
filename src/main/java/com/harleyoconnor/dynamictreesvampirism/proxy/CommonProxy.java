package com.harleyoconnor.dynamictreesvampirism.proxy;

import com.ferreusveritas.dynamictrees.ModConstants;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.harleyoconnor.dynamictreesvampirism.AddonContent;
import de.teamlapen.vampirism.core.ModBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;

/**
 * Common proxy, holds things that are done on both client and server.
 *
 * @author Harley O'Connor
 */
public class CommonProxy {

	public void preInit() {
	}
	
	public void init() {
		AddonContent.spruceOakSpecies.getFamily().addSpeciesLocationOverride((world, trunkPos) -> world.getBiome(trunkPos).equals(ModBiomes.vampireForest) ? AddonContent.spruceOakSpecies : Species.NULLSPECIES);
	}

	private Species getSpecies (final String name) {
		return TreeRegistry.findSpecies(new ResourceLocation(ModConstants.MODID, name));
	}

	public void postInit() {
	}

}
