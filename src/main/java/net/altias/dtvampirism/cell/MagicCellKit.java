package net.altias.dtvampirism.cell;

import com.dtteam.dynamictrees.api.cell.Cell;
import com.dtteam.dynamictrees.api.cell.CellKit;
import com.dtteam.dynamictrees.api.cell.CellNull;
import com.dtteam.dynamictrees.api.cell.CellSolver;
import com.dtteam.dynamictrees.api.voxmap.SimpleVoxmap;
import com.dtteam.dynamictrees.systems.cell.CellKits;
import com.dtteam.dynamictrees.systems.cell.MetadataCell;
import com.dtteam.dynamictrees.systems.cell.NormalCell;
import net.altias.dtvampirism.DTVampirism;
import net.minecraft.resources.ResourceLocation;

public class MagicCellKit extends CellKit {

    public MagicCellKit() {
        super(DTVampirism.location("magic"));
    }

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
}
