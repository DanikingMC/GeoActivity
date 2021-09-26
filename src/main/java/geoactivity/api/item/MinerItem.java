package geoactivity.api.item;

import geoactivity.common.registry.GATags;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;

public class MinerItem extends MiningToolItem {

    public MinerItem(float attackDamage, float attackSpeed, ToolMaterial material, Settings settings) {
        super(attackDamage, attackSpeed, material, GATags.MINER_MINEABLE, settings);
    }
}
