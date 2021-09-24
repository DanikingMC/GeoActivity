package geoactivity.client.gui.screen.handler;

import geoactivity.api.screen.builder.ContainerSlotBuilder;
import geoactivity.api.screen.builder.PlayerSlotBuilder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

public abstract class GAScreenHandler extends ScreenHandler {

    private final PlayerInventory playerInventory;
    private final Inventory inventory;

    protected GAScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(type, syncId);
        this.playerInventory = playerInventory;
        this.inventory = inventory;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        return ItemStack.EMPTY;
    }

    protected ContainerSlotBuilder containerBuilder() {
        return new ContainerSlotBuilder(this);
    }

    protected PlayerSlotBuilder playerBuilder() {
        return new PlayerSlotBuilder(this);
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
