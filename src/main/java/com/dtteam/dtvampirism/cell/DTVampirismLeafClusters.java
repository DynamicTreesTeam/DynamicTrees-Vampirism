package com.dtteam.dtvampirism.cell;

import com.dtteam.dynamictrees.api.voxmap.SimpleVoxmap;
import net.minecraft.core.BlockPos;

public class DTVampirismLeafClusters {

    public static final SimpleVoxmap MAGIC = new SimpleVoxmap(3, 4, 3, new byte[] {
            0, 0, 0,
            0, 1, 0,
            0, 0, 0,

            0, 2, 0,
            2, 0, 2,
            0, 2, 0,

            0, 1, 0,
            1, 2, 1,
            0, 1, 0,

            0, 0, 0,
            0, 1, 0,
            0, 0, 0,
    }).setCenter(new BlockPos(1, 1, 1));

    public static final SimpleVoxmap MAGIC_TOP = new SimpleVoxmap(3, 4, 3, new byte[] {
            0, 1, 0,
            1, 0, 1,
            0, 1, 0,

            0, 0, 0,
            0, 2, 0,
            0, 0, 0,

            0, 0, 0,
            0, 1, 0,
            0, 0, 0,
    }).setCenter(new BlockPos(1, 0, 1));

}
