package geoactivity.api.screen.builder;

import geoactivity.client.gui.screen.handler.GAScreenHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;

public final class PlayerSlotBuilder {

    private final GAScreenHandler parent;
    private final PlayerInventory playerInventory;

    public PlayerSlotBuilder(final GAScreenHandler parent) {
        this.parent = parent;
        this.playerInventory = parent.getPlayerInventory();
    }

    public GAScreenHandler setup() {
        this.hotbarSlots().mainSlots();
        return this.parent;
    }
    
    public PlayerSlotBuilder mainSlots() {
        return this.mainSlots(8, 84);
    }

    public PlayerSlotBuilder mainSlots(final int posX, final int posY) {
        final int index = this.parent.slots.size();
        
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.parent.addSlot(new Slot(this.playerInventory, j + i * 9 + 9, posX + j * 18, posY + i * 18));
            }
        }
        return this;
    }

    public PlayerSlotBuilder hotbarSlots() {
        return this.addHotbarSlots(8, 142);
    }

    public PlayerSlotBuilder addHotbarSlots(final int posX, final int posY) {
        final int index = this.parent.slots.size();
        for (int i = 0; i < 9; i++) {
            this.parent.addSlot(new Slot(this.playerInventory, i, posX + i * 18, posY));
        }
        return this;
    }
}
