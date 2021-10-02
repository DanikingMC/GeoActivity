package geoactivity.common.recipe;

import geoactivity.api.recipe.IMachineRecipe;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class CraftingMachineRecipe implements IMachineRecipe {

    private final int width;
    private final int height;
    private final DefaultedList<Ingredient> input;
    private final ItemStack output;
    private final Identifier id;

    public CraftingMachineRecipe(int width, int height, DefaultedList<Ingredient> input, ItemStack output, Identifier id) {
        this.width = width;
        this.height = height;
        this.input = input;
        this.output = output;
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public DefaultedList<Ingredient> getInput() {
        return input;
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        return false;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }
}
