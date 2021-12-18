package com.asablock;

import com.asablock.block.UnstableObsidianBlock;
import com.asablock.enchantments.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.MixinEnvironment;

import static net.minecraft.enchantment.Enchantment.Rarity;

public class MoreEnchantmentsMod implements ModInitializer {
	private static final EquipmentSlot[] MAINHAND = {EquipmentSlot.MAINHAND};
	private static final EquipmentSlot[] ALLHAND = {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};

	public static final Enchantment VAMPIRIC_ENCHANTMENT = new VampiricEnchantment(Enchantment.Rarity.RARE,
			MAINHAND);
	public static final Enchantment SPIKES_ENCHANTMENT = new SpikesEnchantment(Enchantment.Rarity.COMMON,
			MAINHAND);
	public static final Enchantment FREEZING_ENCHANTMENT = new FreezingEnchantment(Enchantment.Rarity.VERY_RARE,
			MAINHAND);
	public static final Enchantment POWER_OF_SHULKER_ENCHANTMENT =
			new PowerOfShulkerEnchantment(Enchantment.Rarity.VERY_RARE, MAINHAND);
	public static final Enchantment TOXIC_ENCHANTMENT = new ToxicEnchantment(Enchantment.Rarity.RARE,
			MAINHAND);
	public static final Enchantment WITHERING_ENCHANTMENT = new WitheringEnchantment(Enchantment.Rarity.RARE,
			MAINHAND);
	public static final Enchantment AUTOMUTILATION_CURSE_ENCHANTMENT = new AutomutilationCurseEnchantment(
			Enchantment.Rarity.RARE, MAINHAND);
	public static final Enchantment ARROW_RECYCLING_ENCHANTMENT = new ArrowRecyclingEnchantment(
			Rarity.VERY_RARE, ALLHAND);
	public static final Enchantment CREATURE_RELEASING_CURSE_ENCHANTMENT = new CreatureReleasingCurseEnchantment(
			Rarity.RARE, ALLHAND);
	public static final Enchantment DISARMING_ENCHANTMENT = new DisarmingEnchantment(Rarity.VERY_RARE,
			MAINHAND);
	public static final Enchantment OBSIDIAN_WALKER_ENCHANTMENT = new ObsidianWalkerEnchantment(
			Rarity.VERY_RARE, new EquipmentSlot[]{EquipmentSlot.FEET});
	public static final Enchantment THUNDER_POWER_ENCHANTMENT = new ThunderPowerEnchantment(
			Rarity.VERY_RARE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
	public static final Enchantment EXPLODING_ENCHANTMENT = new ExplodingEnchantment(
			Rarity.RARE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
	public static final Enchantment BLUNT_ENCHANTMENT = new BluntEnchantment(
			Rarity.COMMON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});

	public static final Block UNSTABLE_OBSIDIAN_BLOCK = new UnstableObsidianBlock(
			FabricBlockSettings.copyOf(Blocks.OBSIDIAN));

	public static SimpleConfig CONFIG;
	public static String[] DISABLED_ENCHANTMENTS;
	public static MoreEnchantmentsMod INSTANCE;


	@Override
	public void onInitialize() {
		CONFIG = SimpleConfig.of("more-enchantments").provider(x -> DEFAULT_CONFIG_STRING).request();
		DISABLED_ENCHANTMENTS = CONFIG.getOrDefault("disableEnch", "").split(",");

		register(VAMPIRIC_ENCHANTMENT, "vampiric");
		register(SPIKES_ENCHANTMENT, "spikes");
		register(FREEZING_ENCHANTMENT, "freezing");
		register(POWER_OF_SHULKER_ENCHANTMENT, "power_of_shulker");
		register(TOXIC_ENCHANTMENT, "toxic");
		register(WITHERING_ENCHANTMENT, "withering");
		register(AUTOMUTILATION_CURSE_ENCHANTMENT, "automutilation_curse");
		register(ARROW_RECYCLING_ENCHANTMENT, "arrow_recycling");
		register(new E3813ejdfafjea(Rarity.COMMON, ALLHAND), "e3813ejdfafjea"); // Inline Enchantment~
		// Registry.ENCHANTMENT.get(new Identifier("mechants", "e3813ejdfafjea")) to get me~
		// Want to get DEnchantment instance? Use cast: (DEnchantment) obj
		register(CREATURE_RELEASING_CURSE_ENCHANTMENT, "creature_releasing_curse");
		register(DISARMING_ENCHANTMENT, "disarming");
		register(OBSIDIAN_WALKER_ENCHANTMENT, "obsidian_walker");
		register(THUNDER_POWER_ENCHANTMENT, "thunder_power");
		register(EXPLODING_ENCHANTMENT, "exploding");
		register(BLUNT_ENCHANTMENT, "blunt");

		Registry.register(Registry.BLOCK, new Identifier("mechants",
				"unstable_obsidian"), UNSTABLE_OBSIDIAN_BLOCK);

		LogManager.getLogger(MoreEnchantmentsMod.class).info("Successfully initialized More Enchantments");
	}

	private void register(Enchantment enchantment, String path) {
		Registry.register(Registry.ENCHANTMENT, new Identifier("mechants", path),
				enchantment);
	}

	public MoreEnchantmentsMod() {
		INSTANCE = this; // Instance Getter
	}

	private static final String DEFAULT_CONFIG_STRING = "disableEnch =\n" +
			"doIcePlacingOnFreezing = true\n" +
			"doWitherCreatureHealingOnWithering = true\n" +
			"doWitherRosePlacingOnWithering = true\n" +
			"canCreatureReleasingSpawnWither = true\n" +
			"doObsidianWalkerPlacingUnstableObsidian = true";
}
