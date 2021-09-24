package geoactivity.api.screen.builder;

import com.mojang.blaze3d.systems.RenderSystem;
import geoactivity.common.GeoActivity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public final class ScreenBuilder {

    private final Screen parent;
    private static final Identifier GUI_ELEMENTS = new Identifier(GeoActivity.MODID, "textures/gui/elements.png");

    public ScreenBuilder(final Screen parent) {
        this.parent = parent;
    }

    public void drawContainer(final MatrixStack stack, final int left, final int top, final int width, final int height) {
        this.bindTexture();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        this.parent.drawTexture(stack, left, top, 0, 0, width / 2, height / 2);
        this.parent.drawTexture(stack, left + width / 2, top, 150 - width / 2, 0, width / 2, height / 2);
        this.parent.drawTexture(stack, left, top + height / 2, 0, 150 - height / 2, width / 2, height / 2);
        this.parent.drawTexture(stack, left + width / 2, top + height / 2, 150 - width / 2, 150 - height / 2, width / 2, height / 2);
    }

    public void drawPlayerSlots(final MatrixStack matrixStack, final int left, final int top) {
        this.bindTexture();

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 9; y++) {
                this.parent.drawTexture(matrixStack, left + y * 18, top + x * 18, 238, 0, 18, 18);
            }
        }
        final int offsetY = 58;
        for (int x = 0; x < 9; x++) {
            this.parent.drawTexture(matrixStack, left + x * 18, top + offsetY, 238, 0, 18, 18);
        }
    }

    public void drawSlot(final MatrixStack matrixStack, final int posX, final int posY) {
        this.bindTexture();
        this.parent.drawTexture(matrixStack, posX, posY, 238, 0, 18, 18);
    }

    public void drawOutputSlot(final MatrixStack matrixStack, final int posX, final int posY) {
        this.bindTexture();
        this.parent.drawTexture(matrixStack, posX, posY, 150, 0, 26,26);
    }

    public void drawProgress(MatrixStack matrixStack, int posX, int posY, int progress, int mouseX, int mouseY) {
        this.bindTexture();
        this.parent.drawTexture(matrixStack, posX, posY, 150, 26, 22, 15);
        if (progress > 0) {
            //placed on top
            this.parent.drawTexture(matrixStack, posX, posY, 150, 41, progress + 1, 16);
        }
    }

    public void drawBurningProgress(final MatrixStack matrixStack, final int posX, final int posY, int progress, final int mouseX, final int mouseY) {
        this.bindTexture();
        final int maxProgress = 100;
        this.parent.drawTexture(matrixStack, posX, posY, 240, 33, 14,14);
        int j = 13 - (int) ((double) progress / (double) maxProgress * 13);
        if (j > 0) {
            this.parent.drawTexture(matrixStack, posX, posY + j, 240, 18 + j, 14,14 - j);
        }
    }

    private void bindTexture() {
        this.bindTexture(GUI_ELEMENTS);
    }

    public void bindTexture(final Identifier texture) {
        RenderSystem.setShaderTexture(0, texture);
    }
}
