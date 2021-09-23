package geoactivity.common.item;

import geoactivity.common.registry.GAObjects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class GACoalItem extends Item {

    public GACoalItem(Settings settings) {
        super(settings);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        if (stack.getItem() == GAObjects.ANTHRACITE_COAL) {
            return Rarity.UNCOMMON;
        }
        return super.getRarity(stack);
    }
}
