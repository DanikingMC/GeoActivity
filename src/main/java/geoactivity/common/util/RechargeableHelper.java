package geoactivity.common.util;

import geoactivity.api.item.Rechargeable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.List;

public class RechargeableHelper {

    public static final String DESTROYED_KEY = Rechargeable.DESTROYED_NBT_KEY;

    //Used on deserialize from json
    public static void onCraft(final ItemStack stack) {
        setDestroyed(stack);
    }

    public static boolean onEntityHit(final ItemStack stack, final PlayerEntity player) {
        if (tryToSetDestroyed(stack)) {
            return false;
        }
        if (!isDestroyed(stack)) {
            stack.damage(2, player, ((e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND)));
        }
        return !isDestroyed(stack);
    }

    /**
     * Tries to set destroyed nbt
     * to the rechargeable stack
     * @param stack ItemStack
     * @return whether it was destroyed or not.
     */
    public static boolean tryToSetDestroyed(final ItemStack stack) {
        if (isAlmostBroken(stack) && !isDestroyed(stack)) {
            setDestroyed(stack);
            return true;
        }
        return false;
    }
    //Used on item tooltip event
    public static void loadTooltips(final ItemStack stack, final List<Text> tooltipList) {
        int i = NbtHelper.getKeys(stack).size();

        if (i == 1) {
            return;
        }
        int index = 1;
        if (i >= 2) {
            //placed after tool durability
            if (!Screen.hasShiftDown()) {
                tooltipList.add(index, new TranslatableText("geoactivity.tooltip.more_info", new TranslatableText("geoactivity.tooltip.shift").formatted(Formatting.GREEN)).formatted(Formatting.GRAY));
                return;
            }
        }
        if (isDestroyed(stack)) {
            tooltipList.add(index, new TranslatableText("geoactivity.tooltip.destroyed").formatted(Formatting.RED));
        }
    }

    public static void setDestroyed(final ItemStack stack) {
        NbtHelper.putBool(stack, DESTROYED_KEY, true);
    }

    public static boolean isDestroyed(final ItemStack stack) {
        return NbtHelper.getBool(stack, DESTROYED_KEY);
    }

    public static boolean isAlmostBroken(final ItemStack stack) {
        return stack.getDamage() >= stack.getMaxDamage() - 2;
    }
}
