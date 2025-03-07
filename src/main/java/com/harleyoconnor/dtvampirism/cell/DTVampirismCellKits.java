package com.harleyoconnor.dtvampirism.cell;

import com.ferreusveritas.dynamictrees.api.cell.Cell;
import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.cell.CellNull;
import com.ferreusveritas.dynamictrees.api.cell.CellSolver;
import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.cell.CellKits;
import com.ferreusveritas.dynamictrees.cell.MetadataCell;
import com.ferreusveritas.dynamictrees.cell.NormalCell;
import com.ferreusveritas.dynamictrees.util.SimpleVoxmap;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import com.harleyoconnor.dtvampirism.DTVampirism;

public class DTVampirismCellKits {

    public static final CellKit MAGIC = new CellKit(new ResourceLocation(DTVampirism.MOD_ID, "magic")) {

        private final Cell magicBranch = new MagicBranchCell();
        private final Cell magicTopBranch = new MagicTopBranchCell();
        private final Cell magicUpperTrunk = new NormalCell(4);

        private final Cell[] magicLeaves = new Cell[]{
                CellNull.NULL_CELL,
                new MagicLeafCell(1),
                new MagicLeafCell(2),
                new MagicLeafCell(3),
                new MagicLeafCell(4),
                new MagicLeafCell(5),
                new MagicLeafCell(6),
                new MagicLeafCell(7),
        };

        private final CellSolver solver = new CellKits.BasicSolver(new short[]{
                0x0412, 0x0311, 0x0211
        });

        @Override
        public Cell getCellForLeaves(int hydro) {
            return magicLeaves[hydro];
        }

        @Override
        public Cell getCellForBranch(int radius, int meta) {
            if (meta == MetadataCell.TOP_BRANCH) return magicTopBranch;
            if (radius == 1) return magicBranch;
            if (radius < 4) return magicUpperTrunk;
            return CellNull.NULL_CELL;
        }

        @Override
        public SimpleVoxmap getLeafCluster() {
            return DTVampirismLeafClusters.MAGIC;
        }

        @Override
        public CellSolver getCellSolver() {
            return solver;
        }

        @Override
        public int getDefaultHydration() {
            return 4;
        }

    };

    public static void register(final Registry<CellKit> registry) {
        registry.registerAll(MAGIC);
    }

}
