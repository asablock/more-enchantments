package com.asablock.enchantments;

import com.asablock.MoreEnchantmentsMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

public class CreatureReleasingCurseEnchantment extends DEnchantment {
    public CreatureReleasingCurseEnchantment(Rarity weight, EquipmentSlot[] slotTypes) {
        super(weight, EnchantmentTarget.WEAPON, slotTypes);
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (isDisabled()) return;
        if (target instanceof LivingEntity) {
            target.world.spawnEntity(randomEntityAndSetPos(user.getRandom(), target.world, target.getPos()));
        }
    }

    private Entity randomEntityAndSetPos(Random random, World world, Vec3d pos) {
        Supplier<?>[] entities = new Supplier<?>[]{() -> new CowEntity(EntityType.COW, world),
                () -> new SheepEntity(EntityType.SHEEP, world),
                () -> new HuskEntity(EntityType.HUSK, world),
                () -> new ZombieEntity(EntityType.ZOMBIE, world),
                () -> {
                    System.out.println("No no no! Wither! Wither at " + pos.toString() + "!");
                    Objects.requireNonNull(MinecraftClient.getInstance().player).sendMessage(Text.of(
                            "A wither will be summoned at " + pos + "!"), false);
                    MinecraftClient.getInstance().player.sendMessage(Text.of("Ready for a fight!"), false);
                    return new WitherEntity(EntityType.WITHER, world);
        }};

        Entity entity = get(entities, random);
        entity.setPos(pos.x, pos.y, pos.z);
        return entity;
    }

    private Entity get(Supplier<?>[] entities, Random random) {
        Entity entity = (Entity) entities[random.nextInt(entities.length - 1)].get();
        if (entity instanceof WitherEntity && MoreEnchantmentsMod.CONFIG.getOrDefault("canCreatureReleasingSpawnWither", true)) {
            return get(entities, random);
        } else return entity;
    }
}
