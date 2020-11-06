package com.harleyoconnor.dynamictreesvampirism;

import com.ferreusveritas.dynamictrees.ModConstants;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.api.WorldGenRegistry.BiomeDataBasePopulatorRegistryEvent;
import com.ferreusveritas.dynamictrees.api.treedata.ILeavesProperties;
import com.ferreusveritas.dynamictrees.blocks.LeavesPaging;
import com.ferreusveritas.dynamictrees.blocks.LeavesProperties;
import com.ferreusveritas.dynamictrees.systems.DirtHelper;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.harleyoconnor.dynamictreesvampirism.trees.SpeciesSpruceOak;
import com.harleyoconnor.dynamictreesvampirism.worldgen.BiomeDataBasePopulator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = AddonConstants.MOD_ID)
public class AddonContent {

	public static Species spruceOakSpecies;

	@SubscribeEvent
	public static void registerDataBasePopulators(final BiomeDataBasePopulatorRegistryEvent event) {
		event.register(new BiomeDataBasePopulator());
	}

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();

		final ILeavesProperties oakLeaves = setUpLeaves(Blocks.LEAVES, "deciduous");

		LeavesPaging.getLeavesBlockForSequence(AddonConstants.MOD_ID, 0, oakLeaves);

		spruceOakSpecies = new SpeciesSpruceOak(TreeRegistry.findSpecies(new ResourceLocation(ModConstants.MODID, "spruce")).getFamily(), oakLeaves);

		Species.REGISTRY.register(spruceOakSpecies);

		DirtHelper.registerSoil(Block.getBlockFromName(AddonConstants.VAMPIRISM_MOD_ID + ":cursed_earth"), DirtHelper.DIRTLIKE);

		LeavesPaging.getLeavesMapForModId(AddonConstants.MOD_ID).values().forEach(registry::register);
	}

	public static ILeavesProperties setUpLeaves (Block primitiveLeavesBlock, String cellKit) {
		return new LeavesProperties(
				primitiveLeavesBlock.getDefaultState(),
				new ItemStack(primitiveLeavesBlock, 1, 0),
				TreeRegistry.findCellKit(cellKit)) {
			@Override
			public ItemStack getPrimitiveLeavesItemStack() {
				return new ItemStack(primitiveLeavesBlock, 1, 0);
			}

			@Override
			public int getLightRequirement() {
				return 1;
			}
		};
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels (final ModelRegistryEvent event) {
		LeavesPaging.getLeavesMapForModId(AddonConstants.MOD_ID).forEach((key, leaves) -> ModelLoader.setCustomStateMapper(leaves, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build()));
	}

}
