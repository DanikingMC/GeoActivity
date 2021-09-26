package geoactivity.client.gui.screen.handler.slot;

import geoactivity.common.block.entity.GABlockEntityBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class GAOutputSlot extends Slot {

    private final Inventory inventory;
    private final PlayerEntity player;
    private final World world;
    private final Predicate<ItemStack> dropExperience;
    private int amount;

    public GAOutputSlot(PlayerEntity player, Inventory inventory, int index, int x, int y) {
        this(player, inventory, index, x, y, always -> false);
    }


    public GAOutputSlot(PlayerEntity player, Inventory inventory, int index, int x, int y, final Predicate<ItemStack> dropExperience) {
        super(inventory, index, x, y);
        this.inventory = inventory;
        this.player = player;
        this.world = player.getEntityWorld();
        this.dropExperience = dropExperience;
    }

    @Override
    public ItemStack takeStack(int amount) {
        if (this.hasStack()) {
            this.amount += Math.min(amount, this.getStack().getCount());
        }

        return super.takeStack(amount);
    }
    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        this.onCrafted(stack);
        super.onTakeItem(player, stack);
    }

    @Override
    protected void onCrafted(ItemStack stack, int amount) {
        this.amount += amount;
        this.onCrafted(stack);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    public void onCrafted(ItemStack stack) {
        stack.onCraft(this.player.world, this.player, this.amount);

        if (this.dropExperience.test(stack)) {
            if (this.world instanceof ServerWorld world && this.inventory instanceof GABlockEntityBase entity) {
                entity.dropExperience(world, player.getPos(), amount);
            }
        }
        this.amount = 0;
    }
}
