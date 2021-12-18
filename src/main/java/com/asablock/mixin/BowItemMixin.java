package com.asablock.mixin;

import com.asablock.MoreEnchantmentsMod;
import com.asablock.enchantments.DEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BowItem.class)
public class BowItemMixin {
    @Inject(method = "onStoppedUsing(Lnet/minecraft/item/ItemStack;" +
            "Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)V", at = @At("HEAD"))
    private void onStoppedUsing(ItemStack stack, World world, LivingEntity entity, int rut, CallbackInfo info) {
        if (entity instanceof PlayerEntity &&
                !((DEnchantment) MoreEnchantmentsMod.ARROW_RECYCLING_ENCHANTMENT).isDisabled()) {
            PlayerEntity user = (PlayerEntity) entity;
            ItemStack itemStack = user.getArrowType(stack);
            int level = EnchantmentHelper.getLevel(MoreEnchantmentsMod.ARROW_RECYCLING_ENCHANTMENT, stack);
            if (!(user.abilities.creativeMode || itemStack.isEmpty()) &&
                    user.getRandom().nextInt(100) < level * 20)
                user.giveItemStack(new ItemStack(Items.ARROW));
        }
    }
}
