package geoactivity.common.block;

import geoactivity.common.block.entity.CraftingMachineBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class CraftingMachineBlock extends GABlockContainer {

    public CraftingMachineBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CraftingMachineBlockEntity(pos, state);
    }
}
