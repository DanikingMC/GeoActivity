package geoactivity.api.item;

import net.minecraft.item.Item;

/**
 * Base class for Block Builders.
 */
public abstract class BuilderItem extends Item implements Rechargeable{

    public BuilderItem(Settings settings) {
        super(settings);
    }
}
