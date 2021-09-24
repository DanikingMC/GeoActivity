package geoactivity.client.gui.screen;

import geoactivity.client.gui.screen.handler.CoalRefinerScreenHandler;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class CoalRefinerScreen extends GAHandledScreen<CoalRefinerScreenHandler> {

    public CoalRefinerScreen(CoalRefinerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        super.drawBackground(matrices, delta, mouseX, mouseY);
        final int slotPosY = this.top() + 35;
        this.builder().drawSlot(matrices, this.left() + 30, slotPosY);
        this.builder().drawSlot(matrices, this.left() + 70, slotPosY);
        this.builder().drawOutputSlot(matrices, this.left() + 123, slotPosY - 4);
        this.builder().drawBurningProgress(matrices, this.left() + 51, slotPosY + 2, (100), mouseX, mouseY);
        this.builder().drawProgress(matrices, this.left() + 93, slotPosY + 1, 0, mouseX, mouseY);

    }
}
