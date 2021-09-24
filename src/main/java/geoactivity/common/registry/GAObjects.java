package geoactivity.common.registry;

import java.util.LinkedHashMap;
import java.util.Map;

import geoactivity.common.GeoActivity;
import geoactivity.common.block.CoalRefinerBlock;
import geoactivity.common.block.GAOreBlock;
import geoactivity.common.item.GACoalItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;

/*
 * Contains all the objects (items and blocks) of "GeoActivity".
 */
public final class GAObjects {
    
    /*
     * Done linked maps to mantain insertion order.
     */
    private static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();
    private static final Map<Identifier, Block> BLOCKS = new LinkedHashMap<>();

    public static final Item LIGNITE_COAL = register("lignite_coal", new GACoalItem(settings()));
    public static final Item BITUMINOUS_COAL = register("bituminous_coal", new GACoalItem(settings()));
    public static final Item ANTHRACITE_COAL = register("anthracite_coal", new GACoalItem(settings()));
    public static final Item GRAPHITE = register("graphite", new Item(settings()));

    public static final Block LIGNITE_ORE = register("lignite_ore", new GAOreBlock(settings(Material.STONE, 3.0F, 15.0F).requiresTool(), UniformIntProvider.create(1, 3)));
    public static final Block BITUMINOUS_ORE = register("bituminous_ore", new GAOreBlock(settings(Material.STONE, 3.0F, 15.0F).requiresTool(), UniformIntProvider.create(2, 5)));
    public static final Block ANTHRACITE_ORE = register("anthracite_ore", new GAOreBlock(settings(Material.STONE, 3.0F, 15.0F).requiresTool(), UniformIntProvider.create(3, 7)));
    public static final Block COAL_REFINER = register("coal_refiner", new CoalRefinerBlock(settings(Material.METAL, 3.5F, 15.0F).requiresTool().sounds(BlockSoundGroup.STONE)));
    /**
     * Inserts the item (value) into the map which its identifier (key).
     * @param <T> Item.
     * @param id String containing the name of the item.
     * @param item Item instance.
     * @return the item.
     */
    private static <T extends Item> T register (final String id, final T item) {
        ITEMS.put(new Identifier(GeoActivity.MODID, id), item);
    
        return item;
    }

    /**
     * Inserts the block (value) into the map which its identifier (key).
     * @param <T> Block.
     * @param id String containing the name of the block.
     * @param block Block instance.
     * @return the block.
     */
    private static <T extends Block> T register (final String id, final T block) {
        final Identifier path = new Identifier(GeoActivity.MODID, id);
        BLOCKS.put(path, block);
        ITEMS.put(path, new BlockItem(block, settings()));
        return block;
    }

    /**
     * Creates the settings for your item.
     * @param <T> Item
     * @return the item settings
     */
    private static <T extends Item> Item.Settings settings() {
        return new Item.Settings().group(GeoActivity.GEOACTIVITY_GROUP);
    }

    /**
     * Creates the settings for your block.
     * @param <T> Block
     * @param material Block material
     * @param hardness how hard it is to break.
     * @param resistance how hard it is to blow up within a explosion.
     * @return the block settings
     */
    private static <T extends Block> AbstractBlock.Settings settings(final Material material, final float hardness, final float resistance) {
        return AbstractBlock.Settings.of(material).strength(hardness, resistance);
    }

    /**
     * Registers all the objects
     */
    public static void init() {
        ITEMS.keySet().forEach(id -> Registry.register(Registry.ITEM, id, ITEMS.get(id)));
        BLOCKS.keySet().forEach(id -> Registry.register(Registry.BLOCK, id, BLOCKS.get(id)));
    }
}
