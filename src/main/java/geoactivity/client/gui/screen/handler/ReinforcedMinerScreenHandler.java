package geoactivity.client.gui.screen.handler;

import geoactivity.common.registry.GAScreenHandlerTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;

public class ReinforcedMinerScreenHandler extends GAScreenHandler{

    public ReinforcedMinerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(1));
    }

    public ReinforcedMinerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(GAScreenHandlerTypes.REINFORCED_MINER, syncId, playerInventory, inventory);
    }
}
