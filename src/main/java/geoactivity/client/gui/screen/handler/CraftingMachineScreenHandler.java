package geoactivity.client.gui.screen.handler;

import geoactivity.api.gui.handler.ScreenHandlerBase;
import geoactivity.api.recipe.IMachineRecipe;
import geoactivity.common.registry.GARecipeTypes;
import geoactivity.common.registry.GAScreenHandlerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Optional;

public class CraftingMachineScreenHandler extends ScreenHandlerBase {

    private final CraftingInventory craftingInventory;
    private final CraftingResultInventory craftingOutputSlot;
    private final ScreenHandlerContext context;

    public CraftingMachineScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3), ScreenHandlerContext.EMPTY);
    }

    public CraftingMachineScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, final ScreenHandlerContext context) {
        super(GAScreenHandlerTypes.CRAFTING_MACHINE, syncId, playerInventory, inventory);
        this.craftingInventory = new CraftingInventory(this, 3, 3);
        this.craftingOutputSlot = new CraftingResultInventory();
        this.context = context;
        this.builder()
                .playerSetup()
                .container3x3(this.craftingInventory, 9, 17)
                .slot(0, 71, 53)
                .slot(1, 111, 53)
                .slot(2, 91, 17)
                .craftingOutput(craftingInventory, this.craftingOutputSlot, 0, 147,35)
                .build();
    }

    @Override
    public void close(PlayerEntity player) {
        super.close(player);
        this.context.run((contextWorld, pos) -> this.dropInventory(player, this.craftingInventory));
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack slotStackCopy = ItemStack.EMPTY;
        Slot slot = this.getSlot(index);
        if (slot != null && slot.hasStack()) {
            ItemStack stackInSlot = slot.getStack();
            slotStackCopy = stackInSlot.copy();

        }
        return super.transferSlot(player, index);
    }

    private static void checkContainer3x3Recipe(final World world, final PlayerEntity player) {
        if (world.isClient) {
            return;
        }
       final ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
        Optional<IMachineRecipe> recipe = Objects.requireNonNull(world.getServer())
                .getRecipeManager()
                .listAllOfType(GARecipeTypes.MACHINE_CRAFTING_RECIPE)
                .stream()
                .findFirst();
       // Optional<CraftingRecipe> optional = world.getServer().getRecipeManager().getFirstMatch(RecipeType.CRAFTING, craftingInventory, world);


    }
}
