package net.altias.dtvampirism.cell;


import com.dtteam.dynamictrees.api.cell.CellKit;
import com.dtteam.dynamictrees.api.registry.Registry;
import net.altias.dtvampirism.DTVampirism;

public class DTVampirismCellKits {

    public static final MagicCellKit MAGIC = new MagicCellKit();

    public static void register(final Registry<CellKit> registry) {
        registry.registerAll(MAGIC);
    }
}
