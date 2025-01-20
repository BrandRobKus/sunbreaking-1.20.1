package com.brandrobkus.sunbreaking.mixin;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreen.class)
public abstract class AnvilScreenMixin {

    @Inject(
            method = "onRenamed",
            at = @At("HEAD")
    )
    private void onNameChange(String name, CallbackInfo ci) {
        // Access the handler using the accessor
        AnvilScreenHandler handler = (AnvilScreenHandler) ((HandledScreenAccessor) this).getHandler();

        // Access input slots
        ItemStack inputStack1 = handler.getSlot(0).getStack();
        ItemStack inputStack2 = handler.getSlot(1).getStack();

        System.out.println("Name Changed To: " + name);
        System.out.println("Input Slot 1: " + inputStack1);
        System.out.println("Input Slot 2: " + inputStack2);
    }
}
