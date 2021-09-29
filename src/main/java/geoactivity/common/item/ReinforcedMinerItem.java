package geoactivity.common.item;

import geoactivity.api.item.MinerItem;
import geoactivity.client.gui.screen.handler.ReinforcedMinerScreenHandler;
import geoactivity.common.util.GAInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ReinforcedMinerItem extends MinerItem {

    public ReinforcedMinerItem(float attackDamage, float attackSpeed, ToolMaterial material, Settings settings) {
        super(attackDamage, attackSpeed, material, settings);
    }

    @Override
    public ScreenHandler getGui(int syncId, PlayerInventory playerInventory, GAInventory inventory) {
        return new ReinforcedMinerScreenHandler(syncId, playerInventory);
    }
}
