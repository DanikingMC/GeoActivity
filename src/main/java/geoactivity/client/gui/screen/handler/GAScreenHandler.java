package geoactivity.client.gui.screen.handler;

import geoactivity.api.screen.builder.ContainerSlotBuilder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

/**
 * Base screen handler object for all containers of the mod
 */
public abstract class GAScreenHandler extends ScreenHandler {

    private final PlayerInventory playerInventory;
    private final Inventory inventory;
    private int lastIndex;

    protected GAScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(type, syncId);
        this.playerInventory = playerInventory;
        this.inventory = inventory;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack stackCopy = ItemStack.EMPTY;
        final Slot slot = this.getSlot(index);
        if (slot != null && slot.hasStack()) {
            ItemStack stackInSlot = slot.getStack();
            stackCopy = stackInSlot.copy();
            //from block entity to the player inventory
            if (index < this.inventory.size()) {
                for (int i = this.inventory.size(); i < this.slots.size() - 1; i++) {
                    if (this.getSlot(i).canInsert(stackInSlot)) {
                        if (!this.insertItem(stackInSlot, this.inventory.size(), this.slots.size(), true)) {
                            return ItemStack.EMPTY;
                        }
                    } else {
                        return ItemStack.EMPTY;
                    }
                }
            } else {
                //from player inventory to the block entity
                for (int i = 0; i < this.inventory.size() - 1; i++) {
                    if (this.getSlot(i).canInsert(stackInSlot)) {
                        if (!this.insertItem(stackInSlot, 0, this.inventory.size(), false)) {
                            return ItemStack.EMPTY;
                        }
                    } else {
                        return ItemStack.EMPTY;
                    }
                }
            }
            if (stackInSlot.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return stackCopy;
    }

    public void mainSlots() {
         this.mainSlots(8, 84);
    }

    public void mainSlots(final int posX, final int posY) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(this.playerInventory, j + i * 9 + 9, posX + j * 18, posY + i * 18));
            }
        }
    }

    public void drawPlayerSlots() {
         this.mainSlots();
         this.hotbarSlots();
    }

    public void hotbarSlots() {
         this.addHotbarSlots(8, 142);
    }

    public void addHotbarSlots(final int posX, final int posY) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(this.playerInventory, i, posX + i * 18, posY));
        }
    }
    protected ContainerSlotBuilder builder() {
        return new ContainerSlotBuilder(this);
    }
    @Override
    public Slot addSlot(Slot slot) {
        return super.addSlot(slot);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

}
