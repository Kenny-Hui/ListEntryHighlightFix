package com.lx862.listentryhighlightfix.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntryListWidget.class)
public abstract class EntryListWidgetMixin extends ClickableWidget {

    @Shadow public abstract int getRowLeft();

    @Shadow public abstract int getRowRight();
    public EntryListWidgetMixin(int x, int y, int width, int height, Text message) {
        super(x, y, width, height, message);
    }

    @Inject(method = "drawSelectionHighlight", at = @At("HEAD"), cancellable = true)
    protected void drawSelectionHighlight(DrawContext context, int y, int entryWidth, int entryHeight, int borderColor, int fillColor, CallbackInfo ci) {
        // getRowLeft() and getRowRight() returns a more appropriate value, probably.
        int i = getRowLeft() - 2;
        int j = getRowRight() - 2;
        context.fill(i, y - 2, j, y + entryHeight + 2, borderColor);
        context.fill(i + 1, y - 1, j - 1, y + entryHeight + 1, fillColor);
        ci.cancel();
    }
}
