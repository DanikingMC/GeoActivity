package geoactivity.common.block.entity;

import geoactivity.client.gui.screen.handler.CraftingMachineScreenHandler;
import geoactivity.common.registry.GABlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class CraftingMachineBlockEntity extends GABlockEntityBase {

    private final PropertyDelegate propertyDelegate;

    public CraftingMachineBlockEntity(BlockPos pos, BlockState state) {
        super(GABlockEntityTypes.CRAFTING_MACHINE, 3, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return 0;
            }

            @Override
            public void set(int index, int value) {

            }

            @Override
            public int size() {
                return 0;
            }
        };
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CraftingMachineScreenHandler(syncId, inv, this, ScreenHandlerContext.create(this.world, this.pos));
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return new int[0];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }


}
