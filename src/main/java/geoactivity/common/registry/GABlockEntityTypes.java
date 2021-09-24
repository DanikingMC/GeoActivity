package geoactivity.common.registry;

import geoactivity.common.GeoActivity;
import geoactivity.common.block.entity.CoalRefinerBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class GABlockEntityTypes {

    public static final BlockEntityType<?> COAL_REFINER = FabricBlockEntityTypeBuilder.create(CoalRefinerBlockEntity::new, GAObjects.COAL_REFINER).build();

    public static  void register(String id, BlockEntityType<? extends BlockEntity> blockEntity) {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(GeoActivity.MODID, id), blockEntity);
    }

    public static void init() {
        register("coal_refiner", COAL_REFINER);
    }


}
