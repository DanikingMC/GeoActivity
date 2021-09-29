package geoactivity.common.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import geoactivity.api.item.Rechargeable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

public class RecipeUtil {

    public static ItemStack deserializeItemFromJson(final JsonObject object) {
        final Identifier location = new Identifier(JsonHelper.getString(object, "item"));
        final Item item = Registry.ITEM.get(location);
        if (item == Items.AIR) {
            throw new JsonSyntaxException("Invalid item: " + item);
        }
        final int i = JsonHelper.getInt(object, "count", 1);
        if (i < 1) {
            throw new JsonSyntaxException("Invalid count: " + i);
        }
        final ItemStack jsonStack = new ItemStack(item);
        if (item instanceof Rechargeable) {
            jsonStack.setDamage(jsonStack.getMaxDamage() - 2);
            RechargeableHelper.onCraft(jsonStack);
        }
        return jsonStack;
    }

}
