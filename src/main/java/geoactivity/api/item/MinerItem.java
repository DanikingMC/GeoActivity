package geoactivity.api.item;

import com.google.common.collect.Multimap;
import geoactivity.common.registry.GATags;
import geoactivity.common.util.RechargeableHelper;
import net.fabricmc.fabric.api.tool.attribute.v1.DynamicAttributeTool;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class MinerItem extends MiningToolItem implements Rechargeable, DynamicAttributeTool {

    public MinerItem(float attackDamage, float attackSpeed, ToolMaterial material, Settings settings) {
        super(attackDamage, attackSpeed, material, GATags.MINER_MINEABLE, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return RechargeableHelper.onEntityHit(stack, (PlayerEntity) attacker);
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !RechargeableHelper.isDestroyed(miner.getMainHandStack());
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return EMPTY;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getDynamicModifiers(EquipmentSlot slot, ItemStack stack, @Nullable LivingEntity user) {
        if (RechargeableHelper.isDestroyed(stack)) {
            return EMPTY;
        }
        return slot == EquipmentSlot.MAINHAND ? super.getAttributeModifiers(slot) : EMPTY;
    }
}
