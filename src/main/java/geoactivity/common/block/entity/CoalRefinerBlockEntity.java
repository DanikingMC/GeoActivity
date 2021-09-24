package geoactivity.common.block.entity;

import geoactivity.client.gui.screen.handler.CoalRefinerScreenHandler;
import geoactivity.common.registry.GABlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class CoalRefinerBlockEntity extends GABlockEntityBase {

    public CoalRefinerBlockEntity(BlockPos pos, BlockState state) {
        super(GABlockEntityTypes.COAL_REFINER, 3, pos, state);
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, GABlockEntityBase blockEntity) {
        
        if (world.isClient) {
            return;
        }
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CoalRefinerScreenHandler(syncId, inv, this);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return null;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction dir) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }
    
}
