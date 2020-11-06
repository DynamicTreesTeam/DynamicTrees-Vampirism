package com.harleyoconnor.dynamictreesvampirism.trees;

import com.ferreusveritas.dynamictrees.api.treedata.ILeavesProperties;
import com.ferreusveritas.dynamictrees.items.Seed;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import com.harleyoconnor.dynamictreesvampirism.AddonConstants;
import de.teamlapen.vampirism.core.ModBiomes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

/**
 * @author Harley O'Connor
 */
public final class SpeciesSpruceOak extends Species {

    public SpeciesSpruceOak (final TreeFamily spruceTree, final ILeavesProperties leavesProperties) {
        super(new ResourceLocation(AddonConstants.MOD_ID, "spruce_oak"), spruceTree, leavesProperties);

        this.setBasicGrowingParameters(0.3F, 12.0F, this.upProbability, this.lowestBranchHeight, 0.8F);

        leavesProperties.setTree(this.getFamily());

        this.setRequiresTileEntity(true);
        this.setupStandardSeedDropping();
    }

    @Override
    public boolean isBiomePerfect(Biome biome) {
        return biome.equals(ModBiomes.vampireForest);
    }

    @Override
    public ItemStack getSeedStack(int qty) {
        return this.getFamily().getCommonSpecies().getSeedStack(qty);
    }

    @Override
    public Seed getSeed() {
        return this.getFamily().getCommonSpecies().getSeed();
    }

}
