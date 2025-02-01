package com.brandrobkus.sunbreaking.mixin;

import com.brandrobkus.sunbreaking.enchantment.ModEnchantments;
import java.util.List;
import java.util.Map;

import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.enchantment.EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

    @Inject(
            method = "getPossibleEntries",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void RemoveHammerEnchants(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        if (stack.getItem() instanceof TridentItem) {
            List<EnchantmentLevelEntry> possibleEntries = cir.getReturnValue();

            boolean isHammer = stack.getItem().getClass().getSimpleName().equals("HammerItem");
            boolean isIronHammer = stack.getItem().getClass().getSimpleName().equals("IronHammerItem");

            for (int i = possibleEntries.size() - 1; i >= 0; i--) {
                Enchantment enchantment = possibleEntries.get(i).enchantment;

                if (isHammer) {
                    if (enchantment == Enchantments.CHANNELING
                            || enchantment == Enchantments.IMPALING
                            || enchantment == Enchantments.RIPTIDE) {
                        possibleEntries.remove(i);
                    }

                } else if (isIronHammer) {
                    if (enchantment == Enchantments.CHANNELING
                            || enchantment == Enchantments.IMPALING
                            || enchantment == Enchantments.RIPTIDE
                            || enchantment == ModEnchantments.ASHEN
                            || enchantment == ModEnchantments.CHAR
                            || enchantment == ModEnchantments.ERUPTION) {
                        possibleEntries.remove(i);
                    }

                } else {
                    if (enchantment == ModEnchantments.BULK
                            || enchantment == ModEnchantments.ASHEN
                            || enchantment == ModEnchantments.CHAR
                            || enchantment == ModEnchantments.ERUPTION) {
                        possibleEntries.remove(i);
                    }
                }
            }
            cir.setReturnValue(possibleEntries);
        }
    }

    @Inject(
            method = "fromNbt",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void filterIncompatibleEnchantedBooks(NbtList list, CallbackInfoReturnable<Map<Enchantment, Integer>> cir) {
        if (net.minecraft.client.MinecraftClient.getInstance() == null
                || net.minecraft.client.MinecraftClient.getInstance().player == null) {
            return; // Safeguard against null player or client
        }

        Map<Enchantment, Integer> enchantments = cir.getReturnValue();

        if (MinecraftClient.getInstance().player.currentScreenHandler instanceof AnvilScreenHandler anvilHandler) {
            ItemStack targetStack = anvilHandler.getSlot(0).getStack();
            ItemStack bookStack = anvilHandler.getSlot(1).getStack();

            if (bookStack.getItem() instanceof EnchantedBookItem) {
                boolean isHammer = targetStack.getItem().getClass().getSimpleName().equals("HammerItem");
                boolean isIronHammer = targetStack.getItem().getClass().getSimpleName().equals("IronHammerItem");

                enchantments.entrySet().removeIf(entry -> {
                    Enchantment enchantment = entry.getKey();

                    if (isHammer) {
                        return enchantment == Enchantments.CHANNELING
                                || enchantment == Enchantments.IMPALING
                                || enchantment == Enchantments.RIPTIDE;
                    } else if (isIronHammer) {
                        return enchantment == Enchantments.CHANNELING
                                || enchantment == Enchantments.IMPALING
                                || enchantment == Enchantments.RIPTIDE
                                || enchantment == ModEnchantments.ASHEN
                                || enchantment == ModEnchantments.CHAR
                                || enchantment == ModEnchantments.ERUPTION;
                    } else {
                        return enchantment == ModEnchantments.BULK
                                || enchantment == ModEnchantments.ASHEN
                                || enchantment == ModEnchantments.CHAR
                                || enchantment == ModEnchantments.ERUPTION;
                    }
                });

                cir.setReturnValue(enchantments);
            }
        }
    }

}
