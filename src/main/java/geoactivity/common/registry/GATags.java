package geoactivity.common.registry;

import geoactivity.common.GeoActivity;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class GATags {

    public static final Tag<Block> MINER_MINEABLE = TagFactory.BLOCK.create(new Identifier(GeoActivity.MODID, "mineable/miner"));
}
