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
	}

}
