package geoactivity.common.recipe;

import com.google.gson.JsonObject;
import geoactivity.common.registry.GARecipeTypes;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

public class RefinementRecipe implements Recipe<Inventory> {

    private final Identifier identifier;
    private final Ingredient input;
    private final ItemStack output;
    private final float experience;
    private final int time;

    public RefinementRecipe(Identifier identifier, Ingredient input, ItemStack output, float experience, int time) {
        this.identifier = identifier;
        this.input = input;
        this.output = output;
        this.experience = experience;
        this.time = time;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return false;
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return ItemStack.EMPTY;
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
        return this.identifier;
    }

    public float getExperience() {
        return experience;
    }

    public int getTime() {
        return time;
    }

    public Ingredient getInput() {
        return input;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return GARecipeTypes.REFINEMENT_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return GARecipeTypes.REFINEMENT_RECIPE_TYPE;
    }

    public static class Serializer implements RecipeSerializer<RefinementRecipe> {

        @Override
        public RefinementRecipe read(Identifier id, JsonObject json) {
            return new RefinementRecipe(id, Ingredient.fromJson(JsonHelper.getObject(json, "ingredient")),  ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result")), JsonHelper.getFloat(json, "experience"), JsonHelper.getInt(json, "time"));
        }

        @Override
        public RefinementRecipe read(Identifier id, PacketByteBuf buf) {
            return new RefinementRecipe(id, Ingredient.fromPacket(buf), buf.readItemStack(), buf.readFloat(), buf.readInt());
        }

        @Override
        public void write(PacketByteBuf buf, RefinementRecipe recipe) {
            recipe.input.write(buf);
            buf.writeItemStack(recipe.output);
            buf.writeFloat(recipe.getExperience());
            buf.writeInt(recipe.getTime());
        }
    }
}

