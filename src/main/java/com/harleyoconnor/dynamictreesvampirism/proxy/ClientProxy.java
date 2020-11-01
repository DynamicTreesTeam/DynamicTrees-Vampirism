package com.harleyoconnor.dynamictreesvampirism.proxy;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.api.client.ModelHelper;
import com.ferreusveritas.dynamictrees.blocks.BlockDynamicLeaves;
import com.ferreusveritas.dynamictrees.blocks.LeavesPaging;
import com.harleyoconnor.dynamictreesvampirism.AddonConstants;
import net.minecraft.block.Block;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Client proxy, holds client-side only events.
 *
 * @author Harley O'Connor
 */
@SideOnly(Side.CLIENT)
public final class ClientProxy extends CommonProxy {

	@Override
	public void init() {
		super.init();

		this.registerColorHandlers(); // Register colour handlers for client.
	}

	public void registerColorHandlers() {
		for (BlockDynamicLeaves leaves: LeavesPaging.getLeavesMapForModId(AddonConstants.MOD_ID).values()) {
			ModelHelper.regColorHandler(leaves, (state, worldIn, pos, tintIndex) -> {
				Block block = state.getBlock();

				if (TreeHelper.isLeaves(block)) return ((BlockDynamicLeaves) block).getProperties(state).foliageColorMultiplier(state, worldIn, pos);
				return 0x00FF00FF; //Magenta
			});
		}
	}

}
