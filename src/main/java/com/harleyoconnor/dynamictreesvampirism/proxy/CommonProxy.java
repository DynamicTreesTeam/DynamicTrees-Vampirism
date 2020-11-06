package com.harleyoconnor.dynamictreesvampirism.proxy;

import com.ferreusveritas.dynamictrees.trees.Species;
import com.harleyoconnor.dynamictreesvampirism.AddonContent;
import de.teamlapen.vampirism.core.ModBiomes;

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



//		ModBiomes.vampireForest = new BiomeGenVampireForest() {
//			@Override
//			public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
//				return new WorldGenTrees(false) {
//					@Override
//					public boolean generate(World worldIn, Random rand, BlockPos position) {
//						return true;
//					}
//				};
//			}
//		};
	}

	public void postInit() {
	}

}
