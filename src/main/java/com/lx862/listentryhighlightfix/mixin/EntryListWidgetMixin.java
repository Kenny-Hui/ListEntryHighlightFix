package com.lx862.listentryhighlightfix.mixin;

import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntryListWidget.class)
public abstract class EntryListWidgetMixin extends AbstractParentElement {
    @Shadow public abstract int getRowLeft();

    @Shadow public abstract int getRowRight();

    public EntryListWidgetMixin() {
        super();
    }

    @Inject(method = "drawSelectionHighlight", at = @At("HEAD"), cancellable = true)
    protected void drawSelectionHighlight(MatrixStack matrices, int y, int entryWidth, int entryHeight, int borderColor, int fillColor, CallbackInfo ci) {
        // getRowLeft() and getRowRight() returns a more appropriate value, probably.
        int i = getRowLeft() - 2;
        int j = getRowRight() - 2;
        fill(matrices, i, y - 2, j, y + entryHeight + 2, borderColor);
        fill(matrices, i + 1, y - 1, j - 1, y + entryHeight + 1, fillColor);
        ci.cancel();
    }
}
