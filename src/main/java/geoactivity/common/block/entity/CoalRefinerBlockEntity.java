package geoactivity.common.block.entity;

import geoactivity.client.gui.screen.handler.CoalRefinerScreenHandler;
import geoactivity.common.block.CoalRefinerBlock;
import geoactivity.common.recipe.RefinementRecipe;
import geoactivity.common.registry.GABlockEntityTypes;
import geoactivity.common.registry.GARecipeTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CoalRefinerBlockEntity extends GABlockEntityBase {
    
    private final PropertyDelegate propertyDelegate;
    private final int fuelSlot = 0;
    private final int inputSlot = 1;
    private final int outputSlot = 2;

    private int burnTime;
    private int fuelTime;
    private int cookTime;
    private int cookTimeTotal;

    public CoalRefinerBlockEntity(BlockPos pos, BlockState state) {
        super(GABlockEntityTypes.COAL_REFINER, 3, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CoalRefinerBlockEntity.this.burnTime;
                    case 1 -> CoalRefinerBlockEntity.this.fuelTime;
                    case 2 -> CoalRefinerBlockEntity.this.cookTime;
                    case 3 -> CoalRefinerBlockEntity.this.cookTimeTotal;
                    default -> 0;
                };
            }
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CoalRefinerBlockEntity.this.burnTime = value;
                    case 1 -> CoalRefinerBlockEntity.this.fuelTime = value;
                    case 2 -> CoalRefinerBlockEntity.this.cookTime = value;
                    case 3 -> CoalRefinerBlockEntity.this.cookTimeTotal = value;
                }
            }
            @Override
            public int size() {
                return 4;
            }
        };
    }

    protected int getItemBurnTime(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        return AbstractFurnaceBlockEntity.createFuelTimeMap().getOrDefault(stack.getItem(), 0);
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.burnTime = nbt.getShort("BurnTime");
        this.cookTime = nbt.getShort("CookTime");
        this.cookTimeTotal = nbt.getShort("CookTimeTotal");
        this.fuelTime = this.getItemBurnTime(this.getStack(fuelSlot));
    }

    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putShort("BurnTime", (short)this.burnTime);
        nbt.putShort("CookTime", (short)this.cookTime);
        nbt.putShort("CookTimeTotal", (short)this.cookTimeTotal);
        return nbt;
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    public static void tick(World world, BlockPos pos, BlockState state, CoalRefinerBlockEntity blockEntity) {
        if (world == null) {
            return;
        }
        if (!world.isClient) {
            final boolean isBurning = blockEntity.isBurning();
            boolean dirty = false;
            if (isBurning) {
                --blockEntity.burnTime;
            } else {
                //if no fuel remaining, decrement progress
                if (blockEntity.cookTime > 0) {
                    blockEntity.cookTime = MathHelper.clamp((blockEntity.cookTime - 2), 0, blockEntity.cookTimeTotal);
                }
            }
            final RefinementRecipe recipe = world.getRecipeManager()
                    .listAllOfType(GARecipeTypes.REFINEMENT_RECIPE_TYPE)
                    .stream()
                    .filter(refinementRecipe -> refinementRecipe.getInput().test(blockEntity.getStack(blockEntity.inputSlot)))
                    .findFirst()
                    .orElse(null);

            if (recipe != null) {
                blockEntity.cookTimeTotal = 40;
                if (!isBurning && canSmelt(blockEntity, recipe)) {
                    blockEntity.burnTime = blockEntity.getItemBurnTime(blockEntity.getStack(blockEntity.fuelSlot));
                    blockEntity.fuelTime = blockEntity.burnTime;
                    if (blockEntity.isBurning()) {
                        dirty = true;
                        final ItemStack fuelStack = blockEntity.getStack(blockEntity.fuelSlot);
                        if(fuelStack.getItem().hasRecipeRemainder()) {
                            blockEntity.setStack(blockEntity.fuelSlot, new ItemStack(fuelStack.getItem().getRecipeRemainder()));
                        } else if (fuelStack.getCount() > 1) {
                            fuelStack.decrement(1);
                        } else if (fuelStack.getCount() == 1) {
                            blockEntity.setStack(blockEntity.fuelSlot, ItemStack.EMPTY);
                        }
                    }
                }
                if (blockEntity.isBurning() && canSmelt(blockEntity, recipe)) {
                    ++blockEntity.cookTime;
                    if (blockEntity.cookTime == blockEntity.cookTimeTotal) {
                        blockEntity.cookTime = 0;
                        blockEntity.cookTimeTotal = recipe.getTime();
                        smeltItem(blockEntity, recipe);
                        dirty = true;
                    }
                } else if (!canSmelt(blockEntity, recipe)) {
                    blockEntity.cookTime = 0;
                }
                if (isBurning != blockEntity.isBurning()) {
                    world.setBlockState(pos, state.with(CoalRefinerBlock.LIT, blockEntity.isBurning()), Block.NOTIFY_ALL);
                }
                if (dirty) {
                    blockEntity.markDirty();
                }
            }

        }
    }

    private static void smeltItem(final CoalRefinerBlockEntity blockEntity, final RefinementRecipe recipe) {
        if (recipe == null) {
            return;
        }
        if (!canSmelt(blockEntity, recipe)) {
            return;
        }
        final ItemStack recipeOutput = recipe.getOutput().copy();
        final ItemStack outputStack = blockEntity.getStack(blockEntity.outputSlot);
        if (outputStack.isEmpty()) {
            blockEntity.setStack(blockEntity.outputSlot, recipeOutput);
        } else if (outputStack.isOf(recipeOutput.getItem())) {
            outputStack.increment(recipeOutput.getCount());
        }
        blockEntity.getStack(blockEntity.inputSlot).decrement(1);

    }

    private static boolean canSmelt(final CoalRefinerBlockEntity blockEntity, final RefinementRecipe recipe) {
        if (recipe == null) {
            return false;
        }
        if (blockEntity.getStack(blockEntity.inputSlot).isEmpty()) {
            return false;
        }
        final ItemStack outputStack = blockEntity.getStack(blockEntity.outputSlot);
        if (outputStack.isEmpty()) {
            return true;
        }
        final ItemStack recipeOutput = recipe.getOutput();
        if (!outputStack.isItemEqualIgnoreDamage(recipeOutput)) {
            return false;
        }
        int nextCount = outputStack.getCount() + recipeOutput.getCount();
        return (nextCount < blockEntity.getMaxCountPerStack() && nextCount < recipeOutput.getMaxCount());
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new CoalRefinerScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        return null;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction dir) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }

}
