package geoactivity.common.registry;

import geoactivity.common.GeoActivity;
import geoactivity.common.recipe.ConversionRecipe;
import geoactivity.common.recipe.CraftingExtendedSerializer;
import geoactivity.api.recipe.IMachineRecipe;
import geoactivity.common.recipe.RefinementRecipe;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;

public class GARecipeTypes {

    private static final Map<RecipeSerializer<?>, Identifier> RECIPE_SERIALIZERS = new HashMap<>();
    private static final Map<RecipeType<?>, Identifier> RECIPE_TYPES = new HashMap<>();

    public static final RecipeSerializer<RefinementRecipe> REFINEMENT_RECIPE_SERIALIZER = create("refinement", new  RefinementRecipe.Serializer());
    public static final RecipeType<RefinementRecipe> REFINEMENT_RECIPE_TYPE = create("refinement");

    public static final RecipeSerializer<ShapedRecipe> CRAFTING_EXTENDED_RECIPE_SERIALIZER = create("crafting_extended", new CraftingExtendedSerializer());
    public static final RecipeType<IMachineRecipe> MACHINE_CRAFTING_RECIPE = create("machine_crafting");

    public static final RecipeType<ConversionRecipe> CONVERSION_RECIPE_TYPE = create("conversion");
    public static final RecipeSerializer<ConversionRecipe> CONVERSION_RECIPE_SERIALIZER = create("conversion", new ConversionRecipe.Serializer());

    private static <T extends Recipe<?>> RecipeSerializer<T> create(String name, RecipeSerializer<T> serializer) {
        RECIPE_SERIALIZERS.put(serializer, new Identifier(GeoActivity.MODID, name));
        return serializer;
    }

    private static <T extends Recipe<?>> RecipeType<T> create(String name) {
        final RecipeType<T> type = new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        };
        RECIPE_TYPES.put(type, new Identifier(GeoActivity.MODID, name));
        return type;
    }

    public static void init() {
        RECIPE_SERIALIZERS.keySet().forEach(recipeSerializer -> Registry.register(Registry.RECIPE_SERIALIZER, RECIPE_SERIALIZERS.get(recipeSerializer), recipeSerializer));
        RECIPE_TYPES.keySet().forEach(recipeType -> Registry.register(Registry.RECIPE_TYPE, RECIPE_TYPES.get(recipeType), recipeType));
    }
}

