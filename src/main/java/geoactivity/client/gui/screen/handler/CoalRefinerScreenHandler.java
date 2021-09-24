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
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;

public class CoalRefinerScreenHandler extends GAScreenHandler {

    private final PropertyDelegate propertyDelegate;

    public CoalRefinerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(4));
    }

    public CoalRefinerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(GAScreenHandlerTypes.COAL_REFINER, syncId, playerInventory, inventory);
        this.propertyDelegate = propertyDelegate;
        this.addProperties(this.propertyDelegate);
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

    public int getProgressScaled(final int scale) {
        if (this.propertyDelegate.get(3) > 0) {
            return this.propertyDelegate.get(2) * scale / this.propertyDelegate.get(3);
        }
        return 0;
    }

    public int getBurnTimeRemainingScaled(final int scale) {
        if (this.propertyDelegate.get(1) == 0) {
            return 0;
        }
        return this.propertyDelegate.get(0) * scale / this.propertyDelegate.get(1);
    }
}
