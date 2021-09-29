package geoactivity.client.gui.screen;

import geoactivity.client.gui.screen.handler.ReinforcedMinerScreenHandler;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class ReinforcedMinerScreen extends GAHandledScreen<ReinforcedMinerScreenHandler> {

    public ReinforcedMinerScreen(ReinforcedMinerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        super.drawBackground(matrices, delta, mouseX, mouseY);
    }
}
