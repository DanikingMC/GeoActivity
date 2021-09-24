package geoactivity.api.screen.builder;

import geoactivity.client.gui.screen.handler.GAScreenHandler;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

import java.util.function.Predicate;

public final class ContainerSlotBuilder {

    private final GAScreenHandler parent;
    private final Inventory container;

    public ContainerSlotBuilder(final GAScreenHandler parent) {
        this.parent = parent;
        this.container = parent.getInventory();
    }

    public ContainerSlotBuilder slot(final int index, final int posX, final int posY) {
        return this.slot(index, posX, posY, always -> true);
    }

    public ContainerSlotBuilder slot(final int index, final int posX, final int posY, final Predicate<ItemStack> canInsert) {
        this.parent.addSlot(new GAInputSlot(this.container, index, posX, posY, canInsert));
        return this;
    }

    public ContainerSlotBuilder output(final int index, final int posX, final int posY) {
        this.parent.addSlot(new GAOutputSlot(this.container, index, posX, posY));
        return this;
    }

    public GAScreenHandler parent() {
        return parent;
    }

    static class GAInputSlot extends Slot {

        private final Predicate<ItemStack> canInsert;

        public GAInputSlot(Inventory inventory, int index, int x, int y, Predicate<ItemStack> canInsert) {
            super(inventory, index, x, y);
            this.canInsert = canInsert;
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return this.canInsert.test(stack);
        }
    }

    static class GAOutputSlot extends Slot {

        public GAOutputSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return false;
        }
    }
}
