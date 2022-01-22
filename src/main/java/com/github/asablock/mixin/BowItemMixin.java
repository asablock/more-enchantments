package com.github.asablock.mixin;

import com.github.asablock.UsefulEnchantmentsMod;
import com.github.asablock.enchantments.DEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BowItem.class)
public class BowItemMixin {
    @Inject(method = "onStoppedUsing", at = @At(value = "INVOKE", shift = At.Shift.BEFORE,
            target = "Lnet/minecraft/item/ItemStack;decrement(I)V"), cancellable = true)
    private void onStoppedUsing(ItemStack stack, World world, LivingEntity entity, int rut, CallbackInfo info) {
        if (!DEnchantment.isDisabled(UsefulEnchantmentsMod.ARROW_RECYCLING_ENCHANTMENT)) {
            int level = EnchantmentHelper.getLevel(UsefulEnchantmentsMod.ARROW_RECYCLING_ENCHANTMENT,
                    stack);
            LogManager.getLogger().info(level);
            if (entity.getRandom().nextInt(100) <= level * 20) {
                Stats.USED.getOrCreateStat((BowItem) (Object) this);
                info.cancel();
            }
        }
    }
}
