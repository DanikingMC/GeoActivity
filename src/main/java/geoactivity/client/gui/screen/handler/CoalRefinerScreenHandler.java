package geoactivity.client.gui.screen.handler;

import geoactivity.common.item.GACoalItem;
import geoactivity.common.registry.GAScreenHandlerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CoalRefinerScreenHandler extends GAScreenHandler {

    public CoalRefinerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3));
    }

    public CoalRefinerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(GAScreenHandlerTypes.COAL_REFINER, syncId, playerInventory, inventory);
        this.builder()
                .slot(0, 31, 36, CoalRefinerScreenHandler::matchesFuel)
                .slot(1, 71, 36, CoalRefinerScreenHandler::matchesRecipe)
                .output(2, 128, 36)
                .parent().drawPlayerSlots();
    }

    public static boolean matchesFuel(final ItemStack stack) {
        final Item item = stack.getItem();
        return item instanceof GACoalItem || item == Items.COAL || item == Items.CHARCOAL;
    }

    public static boolean matchesRecipe(final ItemStack stack) {
        final Item item = stack.getItem();
        return item instanceof GACoalItem;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        return super.transferSlot(player, index);
    }
}
