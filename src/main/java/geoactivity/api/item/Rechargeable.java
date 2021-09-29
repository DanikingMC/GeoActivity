package geoactivity.api.item;

import geoactivity.common.util.GAInventory;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;

public interface Rechargeable {

    /**
     * Nbt key of destroyed boolean
     * for rechargeable stuff
     */
    String DESTROYED_NBT_KEY = "destroyed";

    /**
     * The charge slot index for all
     * rechargeable items is zero.
     */
    int CHARGE_SLOT_INDEX = 0;

    /**
     * Gets the gui of the item.
     * @return ScreenHandler.
     */
    ScreenHandler getGui(final int syncId, final PlayerInventory playerInventory, final GAInventory inventory);


}
