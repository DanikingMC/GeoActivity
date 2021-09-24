package geoactivity.common.block;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Base Block Entity container for machines
 */
public abstract class GABlockContainer extends BlockWithEntity {

    protected GABlockContainer(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    //Requests a screen on right click.
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        
        final BlockEntity blockEntity  = world.getBlockEntity(pos);
        
        if (blockEntity == null) {
            return ActionResult.PASS;
        }
        if (player.isSneaking()) {
            return ActionResult.PASS;
        }

        if (blockEntity instanceof NamedScreenHandlerFactory factory) {
            player.openHandledScreen(factory);
            return ActionResult.SUCCESS;

        }
        return ActionResult.PASS;
    }
    
}
