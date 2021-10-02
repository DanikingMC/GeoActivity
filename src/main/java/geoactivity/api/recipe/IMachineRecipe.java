package geoactivity.api.recipe;

import geoactivity.common.registry.GARecipeTypes;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeType;

public interface IMachineRecipe extends CraftingRecipe {

    @Override
    default RecipeType<?> getType() {
        return GARecipeTypes.MACHINE_CRAFTING_RECIPE;
    }
}
